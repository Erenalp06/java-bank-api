package com.teksen.bank_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teksen.bank_api.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Long>{

}
