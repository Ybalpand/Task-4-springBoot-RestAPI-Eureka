package com.webapp.springbootrestapiwebclient.service;

import java.util.List;

import com.webapp.springbootrestapiwebclient.entity.Transaction;
import com.webapp.springbootrestapiwebclient.exception.TransactionNotFoundException;


public interface TransactionService {

	List<Transaction> getAllTransactionList();
	
	Transaction getTransactionById(Integer transactionId);
	
	Transaction createTransaction(Transaction transaction);
	
	Transaction updateTransaction(Transaction transaction);
	
	void deleteTransaction(Integer transactionId);

}
