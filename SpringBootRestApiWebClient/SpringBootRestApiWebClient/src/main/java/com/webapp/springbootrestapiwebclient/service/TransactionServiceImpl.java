package com.webapp.springbootrestapiwebclient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.springbootrestapiwebclient.entity.Account;
import com.webapp.springbootrestapiwebclient.entity.Transaction;
import com.webapp.springbootrestapiwebclient.exception.TransactionNotFoundException;
import com.webapp.springbootrestapiwebclient.repository.AccountRepository;
import com.webapp.springbootrestapiwebclient.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	AccountRepository accountRepository;
	
	/*  CRUD operation of transaction */
	
	// getting all Transaction record by using the method findaAll() of transacionRepository
	@Override
	public List<Transaction> getAllTransactionList() {
		return transactionRepository.findAll();
	}

	// getting a specific record by using the method findById() of transacionRepository
	@Override
	public Transaction getTransactionById(Integer transactionId) {
		return transactionRepository.findById(transactionId)
				.orElseThrow(() -> new TransactionNotFoundException("Transaction Is Not found : " +transactionId ));
	}

	// saving a specific record by using the method save() of transacionRepository
	@Override
	public Transaction  createTransaction(Transaction transaction) 
	{
		Optional<Account> accountId=  accountRepository.findById(transaction.getAccountId());
		
				
		  if(accountId.isPresent())
		  { 
			  System.out.println("Account present in account db , good to add trnx ");
			  return transactionRepository.save(transaction); 
		  }else {
			  System.out.println("return 0"); 
			  throw new TransactionNotFoundException("Unable to add transaction for invalid account and customer");
		  }
		 
	}
		
	
	// updating a record
	@Override
	public Transaction updateTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	// deleting a specific record by using the method deleteById() of transactionRepository
	@Override
	public void deleteTransaction(Integer transactionId) {
		transactionRepository.findById(transactionId)
		.orElseThrow(() -> new TransactionNotFoundException("NOT EXIST IN DATABASE, YOU CAN NOT DELETE  :: " + transactionId));
		transactionRepository.deleteById(transactionId);
		
	
	}



}
