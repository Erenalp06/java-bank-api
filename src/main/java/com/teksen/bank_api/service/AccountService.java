package com.teksen.bank_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teksen.bank_api.DTOs.AccountDetails;
import com.teksen.bank_api.DTOs.BankDetails;
import com.teksen.bank_api.DTOs.UserDetails;
import com.teksen.bank_api.entity.Account;
import com.teksen.bank_api.entity.Bank;
import com.teksen.bank_api.entity.User;
import com.teksen.bank_api.exception.custom.AccountInactiveException;
import com.teksen.bank_api.exception.custom.AccountNotFoundException;
import com.teksen.bank_api.repository.AccountRepository;

@Service
public class AccountService {    

    private final AccountRepository accountRepository;


    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id, null));
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public Account findAccountByNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
        .orElseThrow(() -> new AccountNotFoundException("Account not found with account number: " + accountNumber, null) );

        if (!account.isActive()) {
            throw new AccountInactiveException("Account is inactive with account number: " + accountNumber, mapToAccountDetails(account));
        }
        return account;        
    }    

     public AccountDetails mapToAccountDetails(Account account) {
        AccountDetails details = new AccountDetails();
        details.setAccountId(account.getId());
        details.setAccountNumber(account.getAccountNumber());
        details.setBalance(account.getBalance());
        details.setUser(mapToUserDetails(account.getUser()));
        details.setBank(mapToBankDetails(account.getBank()));
        return details;
    }

    private UserDetails mapToUserDetails(User user) {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(user.getId());
        userDetails.setName(user.getName());
        userDetails.setEmail(user.getEmail());
        return userDetails;
    }

    private BankDetails mapToBankDetails(Bank bank) {
        BankDetails bankDetails = new BankDetails();
        bankDetails.setBankId(bank.getId());
        bankDetails.setName(bank.getName());
        bankDetails.setAddress(bank.getAddress());
        return bankDetails;
    }
}
