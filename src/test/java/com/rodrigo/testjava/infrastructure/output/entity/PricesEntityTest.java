package com.rodrigo.testjava.infrastructure.output.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import org.junit.jupiter.api.Test;

class PricesEntityTest {

   @Test
   void shouldCreatePricesEntityWithSetters() {
      PricesEntity price = new PricesEntity();
      BrandEntity brand = new BrandEntity();
      brand.setBrandId(1L);
      brand.setBrandName("BrandB");

      ProductEntity product = new ProductEntity();

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
      assertEquals(1L, price.getBrandId().getBrandId());
      assertEquals("BrandB", price.getBrandId().getBrandName());
      assertEquals(BigDecimal.valueOf(35.50), price.getPrice());
      assertEquals(Currency.getInstance("EUR"), price.getCurr());
   }

   @Test
   void shouldTestEqualsAndHashCode() {
      PricesEntity price1 = new PricesEntity();
      price1.setPriceId(1L);
      price1.setBrandId(new BrandEntity(1L, "BrandB"));
      price1.setProductId(new ProductEntity());
      price1.setPriority(1L);

      PricesEntity price2 = new PricesEntity();
      price2.setPriceId(1L);
      price2.setBrandId(new BrandEntity(1L, "BrandB"));
      price2.setProductId(new ProductEntity());
      price2.setPriority(1L);

      PricesEntity price3 = new PricesEntity();
      price3.setPriceId(2L);
      price3.setBrandId(new BrandEntity(2L, "BrandC"));
      price3.setProductId(new ProductEntity());
      price3.setPriority(2L);

      assertEquals(price1, price2);
      assertEquals(price1.hashCode(), price2.hashCode());

      assertNotEquals(price1, price3);
   }

}
