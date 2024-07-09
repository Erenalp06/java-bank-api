package com.teksen.bank_api.DTOs;

import java.math.BigDecimal;

public class AccountDetails {
    private Long accountId;
    private String accountNumber;
    private BigDecimal balance;
    private UserDetails user;
    private BankDetails bank;


    public AccountDetails() {
    }

    public AccountDetails(Long accountId, String accountNumber, BigDecimal balance, UserDetails user, BankDetails bank) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.user = user;
        this.bank = bank;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public UserDetails getUser() {
        return this.user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    public BankDetails getBank() {
        return this.bank;
    }

    public void setBank(BankDetails bank) {
        this.bank = bank;
    }

    public AccountDetails accountId(Long accountId) {
        setAccountId(accountId);
        return this;
    }

    public AccountDetails accountNumber(String accountNumber) {
        setAccountNumber(accountNumber);
        return this;
    }

    public AccountDetails balance(BigDecimal balance) {
        setBalance(balance);
        return this;
    }

    public AccountDetails user(UserDetails user) {
        setUser(user);
        return this;
    }

    public AccountDetails bank(BankDetails bank) {
        setBank(bank);
        return this;
    }    

    @Override
    public String toString() {
        return "{" +
            " accountId='" + getAccountId() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", balance='" + getBalance() + "'" +
            ", user='" + getUser() + "'" +
            ", bank='" + getBank() + "'" +
            "}";
    }
    


}
