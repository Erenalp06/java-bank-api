package com.teksen.bank_api.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

import com.teksen.bank_api.entity.Account;
import com.teksen.bank_api.entity.Transaction;
import com.teksen.bank_api.entity.TransactionType;
import com.teksen.bank_api.exception.custom.InsufficientFundsException;
import com.teksen.bank_api.exception.custom.NegativeAmountException;
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
            throw new InsufficientFundsException("Insufficient funds in source account", accountService.mapToAccountDetails(source));
        }        

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegativeAmountException("Transaction amount must be greater than zero", accountService.mapToAccountDetails(source));
        }

        source.setBalance(source.getBalance().subtract(amount));
        destination.setBalance(destination.getBalance().add(amount));

        Transaction transaction = new Transaction();
        transaction.setSourceAccount(source);
        transaction.setDestinationAccount(destination);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setTransactionDate(new Date());

        transactionRepository.save(transaction);
        accountService.saveAccount(source);
        accountService.saveAccount(destination);

        return transaction;
    }

    public Transaction deposit(String accountNumber, BigDecimal amount) throws Exception{
        Account account = accountService.findAccountByNumber(accountNumber);
        account.setBalance(account.getBalance().add(amount));                

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegativeAmountException("Deposit amount must be greater than zero", accountService.mapToAccountDetails(account));
        }

        Transaction transaction = new Transaction();
        transaction.setDestinationAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTransactionDate(new Date());

        transactionRepository.save(transaction);
        accountService.saveAccount(account);

        return transaction;
    }

    public Transaction withdraw(String accountNumber, BigDecimal amount) throws Exception{
        Account account = accountService.findAccountByNumber(accountNumber);
        if(account.getBalance().compareTo(amount) < 0){
            throw new Exception("Insufficient funds");
        }        

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds in account", accountService.mapToAccountDetails(account));
        }        

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegativeAmountException("Withdraw amount must be greater than zero", accountService.mapToAccountDetails(account));
        }

        account.setBalance(account.getBalance().subtract(amount));

        Transaction transaction = new Transaction();
        transaction.setSourceAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setTransactionDate(new Date());

        transactionRepository.save(transaction);
        accountService.saveAccount(account);

        return transaction;
    }

    public Transaction payment(String accountNumber, BigDecimal amount) throws Exception {
        Account account = accountService.findAccountByNumber(accountNumber);     
       
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds in account", accountService.mapToAccountDetails(account));
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegativeAmountException("Payment amount must be greater than zero", accountService.mapToAccountDetails(account));
        }
    
        account.setBalance(account.getBalance().subtract(amount));
    
        Transaction transaction = new Transaction();
        transaction.setSourceAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.PAYMENT);
        transaction.setTransactionDate(new Date());
        
    
        transactionRepository.save(transaction);
        accountService.saveAccount(account);
    
        return transaction;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    } 

    public Transaction refund(String accountNumber, BigDecimal amount) throws Exception {
        Account account = accountService.findAccountByNumber(accountNumber);        

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegativeAmountException("Refund amount must be greater than zero", accountService.mapToAccountDetails(account));
        }

        account.setBalance(account.getBalance().add(amount));

        Transaction transaction = new Transaction();
        transaction.setDestinationAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.REFUND);
        transaction.setTransactionDate(new Date());

        transactionRepository.save(transaction);
        accountService.saveAccount(account);

        return transaction;
    }

    public Transaction fee(String accountNumber, BigDecimal amount) throws Exception {
        Account account = accountService.findAccountByNumber(accountNumber);
        
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds in account", accountService.mapToAccountDetails(account));
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegativeAmountException("Fee amount must be greater than zero", accountService.mapToAccountDetails(account));
        }

        if (account.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient funds");
        }

        account.setBalance(account.getBalance().subtract(amount));

        Transaction transaction = new Transaction();
        transaction.setSourceAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.FEE);
        transaction.setTransactionDate(new Date());

        transactionRepository.save(transaction);
        accountService.saveAccount(account);

        return transaction;
    }

    public Transaction interest(String accountNumber, BigDecimal amount) throws Exception {
        Account account = accountService.findAccountByNumber(accountNumber);
        
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegativeAmountException("Interest amount must be greater than zero", accountService.mapToAccountDetails(account));
        }

        account.setBalance(account.getBalance().add(amount));

        Transaction transaction = new Transaction();
        transaction.setDestinationAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.INTEREST);
        transaction.setTransactionDate(new Date());

        transactionRepository.save(transaction);
        accountService.saveAccount(account);

        return transaction;
    }



    

}
