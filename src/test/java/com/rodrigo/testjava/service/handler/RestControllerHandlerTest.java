package com.rodrigo.testjava.service.handler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rodrigo.testjava.application.handler.RestControllerHandler;
import com.rodrigo.testjava.domain.enums.PriceErrors;
import com.rodrigo.testjava.domain.exceptions.PriceException;

@ExtendWith(MockitoExtension.class)
public class RestControllerHandlerTest {
   private RestControllerHandler restControllerHandler;

   @BeforeEach
   void setUp() {
      restControllerHandler = new RestControllerHandler();
   }

   @Test
   void handlePriceException_ShouldReturnNotFound() {
      PriceException priceException = new PriceException("Not Found", PriceErrors.PRICE_NOT_AVAILABLE);

      ResponseEntity<String> response = restControllerHandler.handlePriceException(priceException);

      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      assertThat(response.getBody()).contains("PRICE_NOT_AVAILABLE");
   }

   @Test
   void handleGenericException_ShouldReturnInternalServerError() {
      Exception genericException = new Exception("Something went wrong");

      ResponseEntity<String> response = restControllerHandler.handleGenericException(genericException);

      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
      assertThat(response.getBody()).contains("An unexpected error occurred");
   }
}
