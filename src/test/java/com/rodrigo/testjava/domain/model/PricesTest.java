package com.rodrigo.testjava.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import org.junit.jupiter.api.Test;

class PricesTest {

   @Test
   void shouldCreatePricesWithSetters() {
      Prices price = new Prices();
      Brand brand = new Brand();
      Product product = new Product();
      price.setPriceId(1L);
      price.setBrandId(brand);
      price.setProductId(product);
      price.setPriority(1L);
      price.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
      price.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));
      price.setPrice(BigDecimal.valueOf(35.50));
      price.setCurr(Currency.getInstance("EUR"));

      assertNotNull(price);
      assertEquals(1L, price.getPriceId());
      assertEquals(brand, price.getBrandId());
      assertEquals(product, price.getProductId());
      assertEquals(BigDecimal.valueOf(35.50), price.getPrice());
      assertEquals(Currency.getInstance("EUR"), price.getCurr());
   }

   @Test
   void shouldTestEqualsAndHashCode() {
      Prices price1 = new Prices();
      Prices price2 = new Prices();
      Brand brand = new Brand();
      Product product = new Product();
      price1.setPriceId(1L);
      price1.setBrandId(brand);
      price1.setProductId(product);
      price1.setPriority(1L);
      price1.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
      price1.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));
      price1.setPrice(BigDecimal.valueOf(35.50));
      price1.setCurr(Currency.getInstance("EUR"));

      price2.setPriceId(1L);
      price2.setBrandId(brand);
      price2.setProductId(product);
      price2.setPriority(1L);
      price2.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
      price2.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));
      price2.setPrice(BigDecimal.valueOf(35.50));
      price2.setCurr(Currency.getInstance("EUR"));

      assertEquals(price1, price2);
      assertEquals(price1.hashCode(), price2.hashCode());

      price2.setPriceId(2L);
      assertNotEquals(price1, price2);
   }
}
