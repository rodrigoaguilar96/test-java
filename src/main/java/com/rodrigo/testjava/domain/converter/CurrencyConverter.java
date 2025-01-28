package com.rodrigo.testjava.domain.converter;

import java.util.Currency;
import java.util.Objects;

import org.apache.logging.log4j.util.Strings;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CurrencyConverter implements AttributeConverter<Currency, String> {

   @Override
   public String convertToDatabaseColumn(Currency currency) {
      if (Objects.nonNull(currency)) {
         return currency.getCurrencyCode();
      } else {
         return null;
      }
   }

   @Override
   public Currency convertToEntityAttribute(String currency) {
      if (Strings.isNotBlank(currency)) {
         return Currency.getInstance(currency);
      } else {
         return null;
      }
   }
}
