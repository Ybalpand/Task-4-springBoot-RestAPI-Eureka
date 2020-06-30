package com.webapp.springbootrestapiwebclient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.webapp.springbootrestapiwebclient.entity.Account;
import com.webapp.springbootrestapiwebclient.entity.Customer;
import com.webapp.springbootrestapiwebclient.service.AccountService;

@RequestMapping("/springbootrestapiwebclient/account")
@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;
	

	// 1 get All Account List
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Account> getAllAccount() {
		return accountService.getAllAccountList();
	}

	// 2 Get Account by Id
	@GetMapping("/{accountId}")
	public ResponseEntity<Account> getAccountById(@PathVariable("accountId") Integer accountId) {
		Account acc = accountService.getAccountById(accountId);
		if (accountId <= 0) {
			return new ResponseEntity<Account>(acc, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Account>(acc, HttpStatus.OK);
	}

	// 3 create account
	@PostMapping
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {

		Integer customerId = account.getCustomerId();
		if (customerId >= 0) {
			System.out.println("customerId : " + customerId);
			try {

				Customer customerInfo = WebClientController.getCustomerInfoById(customerId);

				if (customerInfo == null) {
					return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);

				} else {

					Account accountCreated = accountService.createAccount(account);
					System.out.println("newAccountcreated ::" + accountCreated);
					if (accountCreated == null) {
						System.out.println("new Account Created ");
						return new ResponseEntity<Account>(accountCreated, HttpStatus.CONFLICT);
					} else {
						return new ResponseEntity<Account>(accountCreated, HttpStatus.CREATED);
					}
				}
			} catch (Exception e) {
				System.out.println("Customer Not Found ...");
				// return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found", e);
			}
		} else {
			System.out.println("Invalid  Customer");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid  Customer");
		}

	}

	// 4 update account
	@PutMapping
	public Account updateAccount(@RequestBody Account account) {
		return accountService.updateAccount(account);
	}

	// 5 delete account
	@DeleteMapping("/{accountId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void deleteAccount(@PathVariable("accountId") Integer accountId) {
		accountService.deleteAccount(accountId);
	}

	// 6 update deactive Account
	@GetMapping(value = "/deactiveAccount")
	public Boolean updateDeActiveAccount(@RequestParam(name = "accountId", required = false) Integer accountId) {
		return accountService.updateDeActiveAccount(accountId);
	}

	// 6 update Active Account
	@GetMapping(value = "/activeAccount")
	public Boolean updateActiveAccount(@RequestParam(name = "accountId", required = false) Integer accountId) {
		return accountService.updateActiveAccount(accountId);
	}

}
