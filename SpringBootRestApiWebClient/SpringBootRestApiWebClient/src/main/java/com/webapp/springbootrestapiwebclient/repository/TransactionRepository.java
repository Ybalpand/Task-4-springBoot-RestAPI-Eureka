package com.webapp.springbootrestapiwebclient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.springbootrestapiwebclient.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
