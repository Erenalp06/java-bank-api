package com.teksen.bank_api.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.teksen.bank_api.entity.Account;
import com.teksen.bank_api.entity.Transaction;
import com.teksen.bank_api.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    public Transaction createTransaction(String sourceAccountNumber, String destinationAccountNumber, BigDecimal amount) throws Exception {
        Account source = accountService.findAccountByNumber(sourceAccountNumber);
        Account destination = accountService.findAccountByNumber(destinationAccountNumber);

        if (source.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient funds");
        }

        source.setBalance(source.getBalance().subtract(amount));
        destination.setBalance(destination.getBalance().add(amount));

        Transaction transaction = new Transaction();
        transaction.setSourceAccount(source);
        transaction.setDestinationAccount(destination);
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());

        transactionRepository.save(transaction);
        accountService.saveAccount(source);
        accountService.saveAccount(destination);

        return transaction;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    } 



    

}
