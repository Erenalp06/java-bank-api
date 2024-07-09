package com.teksen.bank_api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teksen.bank_api.DTOs.AccountDetails;
import com.teksen.bank_api.DTOs.TransactionDTO;
import com.teksen.bank_api.DTOs.TransactionRequest;
import com.teksen.bank_api.entity.Transaction;
import com.teksen.bank_api.service.AccountService;
import com.teksen.bank_api.service.TransactionService;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final AccountService accountService;

    public TransactionController(TransactionService transactionService, AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }
    

    @PostMapping("/transfer")
    public ResponseEntity<TransactionDTO> transferMoney(@RequestBody TransactionRequest request) throws Exception{
    Transaction transaction = transactionService.createTransaction(request.getSourceAccountNumber(), request.getDestinationAccountNumber(), request.getAmount());
    AccountDetails sourceDetails = accountService.mapToAccountDetails(transaction.getSourceAccount());
    AccountDetails destinationDetails = accountService.mapToAccountDetails(transaction.getDestinationAccount());
    TransactionDTO transactionDTO = TransactionDTO.toDTO(transaction, sourceDetails, destinationDetails);

    return new ResponseEntity<>(transactionDTO, HttpStatus.CREATED);
}


@GetMapping("/")
public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
    List<Transaction> transactions = transactionService.getAllTransactions();
    List<TransactionDTO> transactionDTOs = transactions.stream().map(transaction -> {
        AccountDetails sourceDetails = accountService.mapToAccountDetails(transaction.getSourceAccount());
        AccountDetails destinationDetails = accountService.mapToAccountDetails(transaction.getDestinationAccount());
        return TransactionDTO.toDTO(transaction, sourceDetails, destinationDetails);
    }).collect(Collectors.toList());
    return ResponseEntity.ok(transactionDTOs);
}

    


}
