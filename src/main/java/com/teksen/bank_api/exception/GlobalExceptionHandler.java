package com.teksen.bank_api.exception;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teksen.bank_api.exception.custom.CustomException;
import com.teksen.bank_api.exception.response.DefaultResponse;

import io.opentelemetry.api.trace.Span;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    private final ObjectMapper objectMapper;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<DefaultResponse> handleCustomException(CustomException e){
        DefaultResponse defaultResponse = createDefaultResponseFromException(e, e.getStatusCode().value());
        addResponseToSpan(defaultResponse);
        return new ResponseEntity<>(defaultResponse, e.getStatusCode());
    }

    private static DefaultResponse createDefaultResponseFromException(CustomException e, int status) {
        String exceptionType = e.getClass().getSimpleName();
        DefaultResponse defaultResponse = new DefaultResponse(status, exceptionType, e.getMessage(), new Date(), e.getAccountDetails() );
        return defaultResponse;
    }

    private void addResponseToSpan(DefaultResponse defaultResponse) {
        Span currentSpan = Span.current();
        if (currentSpan != null) {
            try {
                String responseJson = objectMapper.writeValueAsString(defaultResponse);
                currentSpan.setAttribute("http.response.body", responseJson);
            } catch (JsonProcessingException ex) {
                currentSpan.setAttribute("http.response.body.error", "Failed to convert response to JSON");
            }
        }
    }
}
