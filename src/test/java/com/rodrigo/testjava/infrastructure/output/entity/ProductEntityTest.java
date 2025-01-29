package com.rodrigo.testjava.infrastructure.output.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ProductEntityTest {
   @Test
   void shouldCreateProductEntityWithSetters() {
      ProductEntity product = new ProductEntity();
      BrandEntity brand = new BrandEntity();

      brand.setBrandId(1L);
      brand.setBrandName("BrandB");

      product.setProductId(100L);
      product.setBrand(brand);
      product.setProductName("Test Product");

      assertNotNull(product);
      assertEquals(100L, product.getProductId());
      assertEquals(1L, product.getBrand().getBrandId());
      assertEquals("BrandB", product.getBrand().getBrandName());
      assertEquals("Test Product", product.getProductName());
   }

   @Test
   void shouldTestEqualsAndHashCode() {
      ProductEntity product1 = new ProductEntity();
      product1.setProductId(100L);
      product1.setBrand(new BrandEntity(1L, "BrandB"));
      product1.setProductName("Test Product");

      ProductEntity product2 = new ProductEntity();
      product2.setProductId(100L);
      product2.setBrand(new BrandEntity(1L, "BrandB"));
      product2.setProductName("Test Product");

      ProductEntity product3 = new ProductEntity();
      product3.setProductId(200L);
      product3.setBrand(new BrandEntity(2L, "BrandC"));
      product3.setProductName("Another Product");

      assertEquals(product1, product2);
      assertEquals(product1.hashCode(), product2.hashCode());

      assertNotEquals(product1, product3);
   }
}
