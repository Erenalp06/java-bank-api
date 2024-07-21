package com.teksen.bank_api.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;


    public Account() {
    }

    public Account(Long id, String accountNumber, BigDecimal balance, boolean active, Date creationDate, User user, Bank bank) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.active = active;
        this.creationDate = creationDate;
        this.user = user;
        this.bank = bank;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Account id(Long id) {
        setId(id);
        return this;
    }

    public Account accountNumber(String accountNumber) {
        setAccountNumber(accountNumber);
        return this;
    }

    public Account balance(BigDecimal balance) {
        setBalance(balance);
        return this;
    }

    public Account user(User user) {
        setUser(user);
        return this;
    }

    public Account bank(Bank bank) {
        setBank(bank);
        return this;
    }


    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate() {
        this.creationDate = new Date();
    }



    public boolean isActive() {
        return this.active;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", balance='" + getBalance() + "'" +
            ", user='" + getUser() + "'" +
            ", bank='" + getBank() + "'" +
            "}";
    }
    

}
