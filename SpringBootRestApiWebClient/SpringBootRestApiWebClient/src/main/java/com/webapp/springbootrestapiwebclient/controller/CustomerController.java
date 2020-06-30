package com.webapp.springbootrestapiwebclient.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.springbootrestapiwebclient.entity.Account;
import com.webapp.springbootrestapiwebclient.entity.Customer;
import com.webapp.springbootrestapiwebclient.exception.CustomerNotFoundException;
import com.webapp.springbootrestapiwebclient.service.AccountService;

@RequestMapping("/springbootrestapiwebclient/customers")
@RestController
public class CustomerController {

	@Autowired
	private AccountService accountService;
	
		// 1 get List of Customers with Account
		@GetMapping
		@ResponseStatus(HttpStatus.OK)
		public List<Customer> getAllCustomerWithAccount() {
			return accountService.getAllCustomerWithAccount();
		}
	
		// 2  pass the CustomerId as requestparam it will get the account info with customer info
		@GetMapping("/getAccountByCustomerId")
		@ResponseStatus(HttpStatus.OK)
		public Customer getAccountByCustomerId(@RequestParam(name = "customerId", required = false) Integer customerId)
				throws CustomerNotFoundException {
			List<Account> accounts = accountService.getAccountByCustomerId(customerId);
			Customer customerInfo = WebClientController.getCustomerInfoById(customerId);
			customerInfo.setAccount(accounts);
			return customerInfo;
		}
		
		
		// 3 getCustomerInfoByAccountId Get All the customer Info and Account Info
		@GetMapping("getCustomerByAccountId")
		@ResponseStatus(HttpStatus.OK)
		public Customer getCustomerByAccountId(@RequestParam(name = "accountId", required = false) Integer accountId) {

			Account account = accountService.getAccountById(accountId);
			Customer customers = WebClientController.getCustomerInfoById(account.getCustomerId());
			ArrayList<Account> acclist = new ArrayList<Account>();
			acclist.add(account);
			customers.setAccount(acclist);
			return customers;

		}
}
