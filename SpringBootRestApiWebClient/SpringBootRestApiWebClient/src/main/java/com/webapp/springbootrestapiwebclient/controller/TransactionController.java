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

import com.webapp.springbootrestapiwebclient.entity.Transaction;
import com.webapp.springbootrestapiwebclient.service.TransactionService;

@RequestMapping("/springbootrestapiwebclient/transaction")
@RestController

public class TransactionController {

	@Autowired
	TransactionService transactionService;
	

	// 1 get All Transaction List
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Transaction> getAllTransaction() {
		return transactionService.getAllTransactionList();
	}

	// 2 Get Transaction by Id
	@GetMapping("/{transactionId}")
	public ResponseEntity<Transaction> getTranactionById(@PathVariable("transactionId") Integer transactionId) {
		Transaction transaction = transactionService.getTransactionById(transactionId);
		if (transactionId <= 0) {
			return new ResponseEntity<Transaction>(transaction, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}

	// 3 create account
	@PostMapping
	public Transaction createTransaction(@RequestBody Transaction transaction) {
		System.out.println("create New Transaction");
		return  transactionService.createTransaction(transaction);
	}

	// 4 update account
	@PutMapping
	public Transaction updateTransaction(@RequestBody Transaction transaction) {
		return transactionService.updateTransaction(transaction);
	}

	// 5 delete account
	@DeleteMapping("/{transactionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void deleteTransaction(@PathVariable("transactionId") Integer transactionId) {
		transactionService.deleteTransaction(transactionId);
	}

}
