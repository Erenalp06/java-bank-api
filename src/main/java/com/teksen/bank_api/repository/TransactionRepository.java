package com.teksen.bank_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teksen.bank_api.entity.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, Long>{

}
