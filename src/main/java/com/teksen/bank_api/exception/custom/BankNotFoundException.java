package com.teksen.bank_api.exception.custom;

import org.springframework.http.HttpStatus;

public class BankNotFoundException extends CustomException{

    public BankNotFoundException(String message) {
        super(message, HttpStatus.CREATED, null);
        //TODO Auto-generated constructor stub
    }

}
