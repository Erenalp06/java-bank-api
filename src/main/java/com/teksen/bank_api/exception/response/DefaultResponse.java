package com.teksen.bank_api.exception.response;
import java.util.Date;

import com.teksen.bank_api.DTOs.AccountDetails;

public class DefaultResponse {
    private int status;
    private String exceptionType;
    private String message;
    private Date timestamp;  
    private AccountDetails accountDetails;


    public DefaultResponse() {
    }


    public DefaultResponse(int status, String exceptionType, String message, Date timestamp, AccountDetails accountDetails) {
        this.status = status;
        this.exceptionType = exceptionType;
        this.message = message;
        this.timestamp = timestamp;
        this.accountDetails = accountDetails;
    }
    

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getExceptionType() {
        return this.exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public DefaultResponse status(int status) {
        setStatus(status);
        return this;
    }

    public DefaultResponse exceptionType(String exceptionType) {
        setExceptionType(exceptionType);
        return this;
    }

    public DefaultResponse message(String message) {
        setMessage(message);
        return this;
    }

    public DefaultResponse timestamp(Date timestamp) {
        setTimestamp(timestamp);
        return this;
    }


    public AccountDetails getAccountDetails() {
        return this.accountDetails;
    }

    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }


    @Override
    public String toString() {
        return "{" +
            " status='" + getStatus() + "'" +
            ", exceptionType='" + getExceptionType() + "'" +
            ", message='" + getMessage() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", accountDetails='" + getAccountDetails() + "'" +
            "}";
    }
    

    
    

}
