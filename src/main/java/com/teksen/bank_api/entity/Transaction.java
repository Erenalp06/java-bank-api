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

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private Date transactionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id", nullable = false)
    private Account sourceAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_account_id", nullable = false)
    private Account destinationAccount;


    public Transaction() {
    }

    public Transaction(Long id, BigDecimal amount, Date transactionDate, Account sourceAccount, Account destinationAccount) {
        this.id = id;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Account getSourceAccount() {
        return this.sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getDestinationAccount() {
        return this.destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public Transaction id(Long id) {
        setId(id);
        return this;
    }

    public Transaction amount(BigDecimal amount) {
        setAmount(amount);
        return this;
    }

    public Transaction transactionDate(Date transactionDate) {
        setTransactionDate(transactionDate);
        return this;
    }

    public Transaction sourceAccount(Account sourceAccount) {
        setSourceAccount(sourceAccount);
        return this;
    }

    public Transaction destinationAccount(Account destinationAccount) {
        setDestinationAccount(destinationAccount);
        return this;
    }  

    
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", amount='" + getAmount() + "'" +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", sourceAccount='" + getSourceAccount() + "'" +
            ", destinationAccount='" + getDestinationAccount() + "'" +
            "}";
    }
    


}
