package com.rodrigo.testjava.domain.exceptions;

import com.rodrigo.testjava.domain.enums.PriceErrors;

import lombok.Getter;

@Getter
public class PriceException extends RuntimeException {

   private final PriceErrors priceErrors;

   public PriceException(PriceErrors priceErrors) {
      super();
      this.priceErrors = priceErrors;
   }

   public PriceException(String message, PriceErrors priceErrors) {
      super(message);
      this.priceErrors = priceErrors;
   }

   public PriceException(String message, Throwable cause, PriceErrors priceErrors) {
      super(message, cause);
      this.priceErrors = priceErrors;
   }

   public PriceException(Throwable cause, PriceErrors priceErrors) {
      super(cause);
      this.priceErrors = priceErrors;
   }
}
