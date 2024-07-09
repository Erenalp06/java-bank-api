package com.teksen.bank_api.DTOs;

import java.math.BigDecimal;
import java.util.Objects;

public class TransactionRequest {
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private BigDecimal amount;


    public TransactionRequest() {
    }

    public TransactionRequest(String sourceAccountNumber, String destinationAccountNumber, BigDecimal amount) {
        this.sourceAccountNumber = sourceAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
        this.amount = amount;
    }

    public String getSourceAccountNumber() {
        return this.sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return this.destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionRequest sourceAccountNumber(String sourceAccountNumber) {
        setSourceAccountNumber(sourceAccountNumber);
        return this;
    }

    public TransactionRequest destinationAccountNumber(String destinationAccountNumber) {
        setDestinationAccountNumber(destinationAccountNumber);
        return this;
    }

    public TransactionRequest amount(BigDecimal amount) {
        setAmount(amount);
        return this;
    }        

    @Override
    public String toString() {
        return "{" +
            " sourceAccountNumber='" + getSourceAccountNumber() + "'" +
            ", destinationAccountNumber='" + getDestinationAccountNumber() + "'" +
            ", amount='" + getAmount() + "'" +
            "}";
    }

    
    

}
