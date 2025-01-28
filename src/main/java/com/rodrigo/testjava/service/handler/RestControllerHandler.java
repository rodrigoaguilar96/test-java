package com.rodrigo.testjava.service.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rodrigo.testjava.domain.exceptions.PriceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestControllerHandler {

   @ExceptionHandler(PriceException.class)
   public ResponseEntity<String> handlePriceException(PriceException ex) {
      log.error("PriceException occurred: {}", ex.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing price request: " + ex.getPriceErrors());
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<String> handleGenericException(Exception ex) {
      log.error("An unexpected error occurred: {}", ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred. Please try again later.");
   }
}
