package com.rodrigo.testjava.application.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rodrigo.testjava.domain.enums.PriceErrors;
import com.rodrigo.testjava.domain.exceptions.PriceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestControllerHandler {

   @ExceptionHandler(PriceException.class)
   public ResponseEntity<String> handlePriceException(PriceException ex) {
      log.error("PriceException occurred: {}", ex.getMessage());

      HttpStatus status = mapErrorToHttpStatus(ex.getPriceErrors());

      return ResponseEntity.status(status).body("Error: " + ex.getPriceErrors());
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<String> handleGenericException(Exception ex) {
      log.error("An unexpected error occurred: {}", ex.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred. Please try again later.");
   }
   private HttpStatus mapErrorToHttpStatus(PriceErrors error) {
      return switch (error) {
         case BRAND_NOT_FOUND, PRICE_NOT_AVAILABLE, PRODUCT_NOT_FOUND -> HttpStatus.NOT_FOUND;
         default -> HttpStatus.BAD_REQUEST;
      };
   }
}
