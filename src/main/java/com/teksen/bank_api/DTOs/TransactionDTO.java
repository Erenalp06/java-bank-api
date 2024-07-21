package com.teksen.bank_api.DTOs;

import java.math.BigDecimal;
import java.util.Date;

import com.teksen.bank_api.entity.Transaction;
import com.teksen.bank_api.entity.TransactionType;

public class TransactionDTO {
    private Long id;
    private BigDecimal amount;
    private Date transactionDate;
    private AccountDetails sourceAccount;
    private AccountDetails destinationAccount;
    private TransactionType transactionType;


    public TransactionDTO() {
    }

    public static TransactionDTO toDTO(Transaction transaction, AccountDetails sourceDetails, AccountDetails destinationDetails) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setTransactionDate(transaction.getTransactionDate());
        dto.setSourceAccount(sourceDetails);
        dto.setDestinationAccount(destinationDetails);
        dto.setTransactionType(transaction.getTransactionType());
        return dto;
    }


    public TransactionDTO(Long id, BigDecimal amount, Date transactionDate, AccountDetails sourceAccount, AccountDetails destinationAccount, TransactionType transactionType) {
        this.id = id;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.transactionType = transactionType;
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

    public AccountDetails getSourceAccount() {
        return this.sourceAccount;
    }

    public void setSourceAccount(AccountDetails sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public AccountDetails getDestinationAccount() {
        return this.destinationAccount;
    }

    public void setDestinationAccount(AccountDetails destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public TransactionDTO id(Long id) {
        setId(id);
        return this;
    }

    public TransactionDTO amount(BigDecimal amount) {
        setAmount(amount);
        return this;
    }

    public TransactionDTO transactionDate(Date transactionDate) {
        setTransactionDate(transactionDate);
        return this;
    }

    public TransactionDTO sourceAccount(AccountDetails sourceAccount) {
        setSourceAccount(sourceAccount);
        return this;
    }

    public TransactionDTO destinationAccount(AccountDetails destinationAccount) {
        setDestinationAccount(destinationAccount);
        return this;
    }    


    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
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
