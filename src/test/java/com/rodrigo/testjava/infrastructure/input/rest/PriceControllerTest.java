package com.rodrigo.testjava.infrastructure.input.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Test
   void testFindPriceAt10AMOn14th() throws Exception {
      mockMvc
            .perform(get("/v1/price").param("dateTime", "2020-06-14T10:00:00").param("productId", "35455").param("brandName", "BrandB"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(35.50));
   }

   @Test
   void testFindPriceAt4PMOn14th() throws Exception {
      mockMvc
            .perform(get("/v1/price").param("dateTime", "2020-06-14T16:00:00").param("productId", "35455").param("brandName", "BrandB"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(25.45));
   }

   @Test
   void testFindPriceAt9PMOn14th() throws Exception {
      mockMvc
            .perform(get("/v1/price").param("dateTime", "2020-06-14T21:00:00").param("productId", "35455").param("brandName", "BrandB"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(35.50));
   }

   @Test
   void testFindPriceAt10AMOn15th() throws Exception {
      mockMvc
            .perform(get("/v1/price").param("dateTime", "2020-06-15T10:00:00").param("productId", "35455").param("brandName", "BrandB"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(30.50));
   }

   @Test
   void testFindPriceAt9PMOn16th() throws Exception {
      mockMvc
            .perform(get("/v1/price").param("dateTime", "2020-06-16T21:00:00").param("productId", "35455").param("brandName", "BrandB"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(38.95));
   }
}

