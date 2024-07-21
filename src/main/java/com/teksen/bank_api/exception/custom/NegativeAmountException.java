package com.teksen.bank_api.exception.custom;

import org.springframework.http.HttpStatus;

import com.teksen.bank_api.DTOs.AccountDetails;


public class NegativeAmountException extends CustomException{

    public NegativeAmountException(String message, AccountDetails accountDetails) {
        super(message, HttpStatus.CREATED, accountDetails);
        //TODO Auto-generated constructor stub
    }

    

}
