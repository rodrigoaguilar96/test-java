package com.rodrigo.testjava.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class BrandTest {

   @Test
   void shouldCreateBrandWithSetters() {
      Brand brand = new Brand();
      brand.setBrandId(1L);
      brand.setBrandName("BrandB");

      assertNotNull(brand);
      assertEquals(1L, brand.getBrandId());
      assertEquals("BrandB", brand.getBrandName());
   }

   @Test
   void shouldTestEqualsAndHashCode() {
      Brand brand1 = new Brand(1L, "BrandB");
      Brand brand2 = new Brand(1L, "BrandB");
      Brand brand3 = new Brand(2L, "BrandC");

      assertEquals(brand1, brand2);
      assertEquals(brand1.hashCode(), brand2.hashCode());

      assertNotEquals(brand1, brand3);
   }
}
