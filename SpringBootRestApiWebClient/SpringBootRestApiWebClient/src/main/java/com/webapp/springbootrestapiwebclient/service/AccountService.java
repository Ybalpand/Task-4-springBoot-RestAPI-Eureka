package com.webapp.springbootrestapiwebclient.service;

import java.util.List;


import com.webapp.springbootrestapiwebclient.entity.Account;
import com.webapp.springbootrestapiwebclient.entity.Customer;

public interface AccountService {

	List<Account> getAllAccountList();
	
	Account getAccountById(Integer accountId);
	
	Account updateAccount(Account account);
	
	void deleteAccount(Integer accountId);

	Boolean updateDeActiveAccount(Integer accountId);

	Boolean updateActiveAccount(Integer accountId);

	
	//webclient communication method
	List<Account> getAccountByCustomerId(Integer customerId);

	List<Customer> getAllCustomerWithAccount();
	
	Account createAccount(Account account);

	
	
	
	
	
}
