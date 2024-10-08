package com.teksen.bank_api.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teksen.bank_api.DTOs.AccountDetails;
import com.teksen.bank_api.DTOs.TransactionDTO;
import com.teksen.bank_api.DTOs.TransactionRequest;
import com.teksen.bank_api.entity.Transaction;
import com.teksen.bank_api.service.AccountService;
import com.teksen.bank_api.service.TransactionService;

import io.opentelemetry.api.trace.Span;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;
    private final AccountService accountService;
    private final ObjectMapper objectMapper;   

    public TransactionController(TransactionService transactionService, AccountService accountService, ObjectMapper objectMapper) {
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionDTO> transferMoney(@RequestBody TransactionRequest request) throws Exception {
        logger.info("Transfer request received for source account: {} to destination account: {}", request.getSourceAccountNumber(), request.getDestinationAccountNumber());

        Transaction transaction = transactionService.createTransaction(request.getSourceAccountNumber(), request.getDestinationAccountNumber(), request.getAmount());
        AccountDetails sourceDetails = accountService.mapToAccountDetails(transaction.getSourceAccount());
        AccountDetails destinationDetails = accountService.mapToAccountDetails(transaction.getDestinationAccount());
        TransactionDTO transactionDTO = TransactionDTO.toDTO(transaction, sourceDetails, destinationDetails);

        addSpanAttributes(request, transactionDTO);

        logger.info("Transaction completed: {}", transactionDTO);

        return new ResponseEntity<>(transactionDTO, HttpStatus.CREATED);
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDTO> depositMoney(HttpServletRequest httpRequest, @RequestBody TransactionRequest request) throws Exception {
        addAuthorizationAttribute(httpRequest);
        logger.info("Deposit request received for account number: {}", request.getDestinationAccountNumber());

        Transaction transaction = transactionService.deposit(request.getDestinationAccountNumber(), request.getAmount());
        AccountDetails accountDetails = accountService.mapToAccountDetails(transaction.getDestinationAccount());
        TransactionDTO transactionDTO = TransactionDTO.toDTO(transaction, null, accountDetails);

        addSpanAttributes(request, transactionDTO);

        logger.info("Transaction completed: {}", transactionDTO);

        return new ResponseEntity<>(transactionDTO, HttpStatus.CREATED);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDTO> withdrawMoney(HttpServletRequest httpRequest, @RequestBody TransactionRequest request) throws Exception {
        addAuthorizationAttribute(httpRequest);
        logger.info("Withdraw request received for account number: {}", request.getSourceAccountNumber());

        Transaction transaction = transactionService.withdraw(request.getSourceAccountNumber(), request.getAmount());
        AccountDetails sourceDetails = accountService.mapToAccountDetails(transaction.getSourceAccount());
        TransactionDTO transactionDTO = TransactionDTO.toDTO(transaction, sourceDetails, null);

        addSpanAttributes(request, transactionDTO);

        logger.info("Transaction completed: {}", transactionDTO);

        return new ResponseEntity<>(transactionDTO, HttpStatus.CREATED);
    }

    @PostMapping("/payment")
    public ResponseEntity<TransactionDTO> payment(@RequestBody TransactionRequest request) throws Exception {
        logger.info("Payment request received for account number: {}", request.getSourceAccountNumber());

        Transaction transaction = transactionService.payment(request.getSourceAccountNumber(), request.getAmount());
        AccountDetails sourceDetails = accountService.mapToAccountDetails(transaction.getSourceAccount());
        TransactionDTO transactionDTO = TransactionDTO.toDTO(transaction, sourceDetails, null);

        addSpanAttributes(request, transactionDTO);

        logger.info("Transaction completed: {}", transactionDTO);

        return new ResponseEntity<>(transactionDTO, HttpStatus.CREATED);
    }

    @PostMapping("/refund")
    public ResponseEntity<TransactionDTO> refund(@RequestBody TransactionRequest request) throws Exception {
        logger.info("Refund request received for account number: {}", request.getDestinationAccountNumber());

        Transaction transaction = transactionService.refund(request.getDestinationAccountNumber(), request.getAmount());
        AccountDetails destinationDetails = accountService.mapToAccountDetails(transaction.getDestinationAccount());
        TransactionDTO transactionDTO = TransactionDTO.toDTO(transaction, null, destinationDetails);

        addSpanAttributes(request, transactionDTO);

        logger.info("Transaction completed: {}", transactionDTO);

        return new ResponseEntity<>(transactionDTO, HttpStatus.CREATED);
    }

    @PostMapping("/fee")
    public ResponseEntity<TransactionDTO> fee(@RequestBody TransactionRequest request) throws Exception {
        logger.info("Fee request received for account number: {}", request.getSourceAccountNumber());

        Transaction transaction = transactionService.fee(request.getSourceAccountNumber(), request.getAmount());
        AccountDetails sourceDetails = accountService.mapToAccountDetails(transaction.getSourceAccount());
        TransactionDTO transactionDTO = TransactionDTO.toDTO(transaction, sourceDetails, null);

        addSpanAttributes(request, transactionDTO);

        logger.info("Transaction completed: {}", transactionDTO);

        return new ResponseEntity<>(transactionDTO, HttpStatus.CREATED);
    }

    @PostMapping("/interest")
    public ResponseEntity<TransactionDTO> interest(@RequestBody TransactionRequest request) throws Exception {
        logger.info("Interest request received for account number: {}", request.getDestinationAccountNumber());

        Transaction transaction = transactionService.interest(request.getDestinationAccountNumber(), request.getAmount());
        AccountDetails destinationDetails = accountService.mapToAccountDetails(transaction.getDestinationAccount());
        TransactionDTO transactionDTO = TransactionDTO.toDTO(transaction, null, destinationDetails);

        addSpanAttributes(request, transactionDTO);

        logger.info("Transaction completed: {}", transactionDTO);

        return new ResponseEntity<>(transactionDTO, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        logger.info("Fetching all transactions");

        List<Transaction> transactions = transactionService.getAllTransactions();
        List<TransactionDTO> transactionDTOs = transactions.stream().map(transaction -> {
            AccountDetails sourceDetails = accountService.mapToAccountDetails(transaction.getSourceAccount());
            AccountDetails destinationDetails = accountService.mapToAccountDetails(transaction.getDestinationAccount());
            return TransactionDTO.toDTO(transaction, sourceDetails, destinationDetails);
        }).collect(Collectors.toList());

        logger.info("Found {} transactions", transactionDTOs.size());

        return ResponseEntity.ok(transactionDTOs);
    }

    private void addSpanAttributes(TransactionRequest request, TransactionDTO transactionDTO) {
        Span currentSpan = Span.current();
        if (currentSpan != null) {
            try {
                String transactionJson = objectMapper.writeValueAsString(request);
                String transactionDTOJson = objectMapper.writeValueAsString(transactionDTO);
                currentSpan.setAttribute("http.request.body", transactionJson);
                currentSpan.setAttribute("http.response.body", transactionDTOJson);
            } catch (JsonProcessingException e) {
                currentSpan.setAttribute("http.request.body.error or http.response.body.error", "Failed to convert to JSON");
            }
        }
    }

    private void addAuthorizationAttribute(HttpServletRequest request) {
        Span currentSpan = Span.current();
        if (currentSpan != null) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Basic ")) {
                try {
                    // Basic kimlik doğrulamasını çözme
                    String base64Credentials = authHeader.substring("Basic".length()).trim();
                    byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
                    String credentials = new String(credDecoded, StandardCharsets.UTF_8);
                    // credentials = username:password
                    String username = credentials.split(":", 2)[0];
                    currentSpan.setAttribute("http.auth.user", username);
                } catch (IllegalArgumentException e) {
                    currentSpan.setAttribute("http.auth.error", "Failed to decode credentials");
                }
            }
        }
    }
}
