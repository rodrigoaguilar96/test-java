package com.rodrigo.testjava.infrastructure.output.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class BrandEntityTest {

   @Test
   void shouldCreateBrandEntity() {
      BrandEntity brand = new BrandEntity();
      brand.setBrandId(1L);
      brand.setBrandName("BrandB");

      assertNotNull(brand);
      assertEquals(1L, brand.getBrandId());
      assertEquals("BrandB", brand.getBrandName());
   }

   @Test
   void shouldCreateBrandEntityUsingConstructor() {
      BrandEntity brand = new BrandEntity(1L, "BrandB");

      assertEquals(1L, brand.getBrandId());
      assertEquals("BrandB", brand.getBrandName());
   }

   @Test
   void shouldTestEqualsAndHashCode() {
      BrandEntity brand1 = new BrandEntity();
      brand1.setBrandId(1L);
      brand1.setBrandName("BrandB");

      BrandEntity brand2 = new BrandEntity();
      brand2.setBrandId(1L);
      brand2.setBrandName("BrandB");

      BrandEntity brand3 = new BrandEntity();
      brand3.setBrandId(2L);
      brand3.setBrandName("BrandC");

      assertEquals(brand1, brand2);
      assertEquals(brand1.hashCode(), brand2.hashCode());

      assertNotEquals(brand1, brand3);
   }
}
