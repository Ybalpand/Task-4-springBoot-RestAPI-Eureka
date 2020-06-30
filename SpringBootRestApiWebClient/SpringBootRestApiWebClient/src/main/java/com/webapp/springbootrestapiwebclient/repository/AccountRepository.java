package com.webapp.springbootrestapiwebclient.repository;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webapp.springbootrestapiwebclient.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {


	@Modifying
	@Transactional
	@Query(value=" SELECT account_id , account_name , balance , account_open_date , customer_id , is_active "
			+ "FROM account "
			+ "where customer_id =:customerId " , nativeQuery = true)
	public List<Account> getAccountByCustomerId(Integer customerId) ;
	
	@Modifying
	@Transactional
	@Query(value=" UPDATE account SET is_active = 0 WHERE account_id =:accountId " , nativeQuery = true)
	public void deActivateAccount(Integer accountId) ;
	
	@Modifying
	@Transactional
	@Query(value=" UPDATE account SET is_active = 1 WHERE account_id =:accountId " , nativeQuery = true)
	public void activateAccount(Integer accountId) ;
	
	
}
