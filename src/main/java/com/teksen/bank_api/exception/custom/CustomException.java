package com.teksen.bank_api.exception.custom;

import org.springframework.http.HttpStatusCode;

import com.teksen.bank_api.DTOs.AccountDetails;


public class CustomException extends RuntimeException{

    private final HttpStatusCode statusCode;    
    private final AccountDetails accountDetails;

    public CustomException(String message, HttpStatusCode statusCode, AccountDetails accountDetails) {
        super(message);
        this.statusCode = statusCode;
        this.accountDetails = accountDetails;
    }

    public HttpStatusCode getStatusCode() {
        return this.statusCode;
    }    

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }


    @Override
    public String toString() {
        return "{" +
            " message='" + getMessage() + "'" +
            ", statusCode='" + getStatusCode() + "'" +
            ", accountDetails='" + (accountDetails != null ? accountDetails.toString() : "null") + "'" +
            "}";
    }

    

    
    

}
