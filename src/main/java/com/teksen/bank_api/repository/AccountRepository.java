package com.teksen.bank_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teksen.bank_api.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    Optional<Account> findByAccountNumber(String accountNumber);
    Optional<Account> findByAccountNumberAndUserId(String accountNumber, Long userId);
}
