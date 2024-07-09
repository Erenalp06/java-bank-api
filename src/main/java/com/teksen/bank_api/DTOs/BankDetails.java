package com.teksen.bank_api.DTOs;
import java.util.Objects;

public class BankDetails {
    private Long bankId;
    private String name;
    private String address;


    public BankDetails() {
    }

    public BankDetails(Long bankId, String name, String address) {
        this.bankId = bankId;
        this.name = name;
        this.address = address;
    }

    public Long getBankId() {
        return this.bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BankDetails bankId(Long bankId) {
        setBankId(bankId);
        return this;
    }

    public BankDetails name(String name) {
        setName(name);
        return this;
    }

    public BankDetails address(String address) {
        setAddress(address);
        return this;
    }    

    @Override
    public String toString() {
        return "{" +
            " bankId='" + getBankId() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
    
}
