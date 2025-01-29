package com.rodrigo.testjava.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ProductTest {

   @Test
   void shouldCreateProductWithSetters() {
      Product product = new Product();
      Brand brand = new Brand();
      product.setProductId(100L);
      product.setBrand(brand);
      product.setProductName("Test Product");

      assertNotNull(product);
      assertEquals(100L, product.getProductId());
      assertEquals(brand, product.getBrand());
      assertEquals("Test Product", product.getProductName());
   }

   @Test
   void shouldTestEqualsAndHashCode() {
      Product product1 = new Product();
      Product product2 = new Product();
      Brand brand = new Brand();

      product1.setProductId(100L);
      product1.setBrand(brand);
      product1.setProductName("Test Product");

      product2.setProductId(100L);
      product2.setBrand(brand);
      product2.setProductName("Test Product");

      assertEquals(product1, product2);
      assertEquals(product1.hashCode(), product2.hashCode());

      product2.setProductId(200L);
      assertNotEquals(product1, product2);
   }
}
