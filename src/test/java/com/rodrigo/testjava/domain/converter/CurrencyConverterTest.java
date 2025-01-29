package com.rodrigo.testjava.domain.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Currency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CurrencyConverterTest {

   private CurrencyConverter currencyConverter;

   @BeforeEach
   void setUp() {
      currencyConverter = new CurrencyConverter();
   }

   @Test
   void testConvertToDatabaseColumn_withValidCurrency() {
      Currency currency = Currency.getInstance("EUR");
      String result = currencyConverter.convertToDatabaseColumn(currency);
      assertEquals("EUR", result, "The currency code should be 'EUR'");
   }

   @Test
   void testConvertToDatabaseColumn_withNullCurrency() {
      String result = currencyConverter.convertToDatabaseColumn(null);
      assertNull(result, "The result should be null when the currency is null");
   }

   @Test
   void testConvertToEntityAttribute_withValidCurrencyCode() {
      Currency result = currencyConverter.convertToEntityAttribute("EUR");
      assertNotNull(result, "The currency should not be null");
      assertEquals("EUR", result.getCurrencyCode(), "The currency code should be 'EUR'");
   }

   @Test
   void testConvertToEntityAttribute_withEmptyCurrencyCode() {
      Currency result = currencyConverter.convertToEntityAttribute("");
      assertNull(result, "The result should be null when the currency code is empty");
   }

   @Test
   void testConvertToEntityAttribute_withNullCurrencyCode() {
      Currency result = currencyConverter.convertToEntityAttribute(null);
      assertNull(result, "The result should be null when the currency code is null");
   }
}
