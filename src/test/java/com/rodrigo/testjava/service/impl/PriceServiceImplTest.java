package com.rodrigo.testjava.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rodrigo.testjava.application.service.impl.PriceServiceImpl;
import com.rodrigo.testjava.domain.dto.PriceDto;
import com.rodrigo.testjava.domain.dto.request.PriceRequestDto;
import com.rodrigo.testjava.domain.enums.PriceErrors;
import com.rodrigo.testjava.domain.exceptions.PriceException;
import com.rodrigo.testjava.domain.model.Brand;
import com.rodrigo.testjava.domain.model.Prices;
import com.rodrigo.testjava.domain.model.Product;
import com.rodrigo.testjava.infrastructure.output.adapter.BrandPersistenceAdapter;
import com.rodrigo.testjava.infrastructure.output.adapter.PricesPersistenceAdapter;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

   @Mock
   private PricesPersistenceAdapter pricesPersistenceAdapter;

   @Mock
   private BrandPersistenceAdapter brandPersistenceAdapter;

   @InjectMocks
   private PriceServiceImpl priceService;

   private PriceRequestDto priceRequestDto;

   private Brand brand;

   private Prices prices;

   @BeforeEach
   void setUp() {
      brand = new Brand();
      brand.setBrandId(1L);
      brand.setBrandName("BrandB");

      prices = new Prices();
      prices.setPriceId(1L);
      prices.setBrandId(brand);
      prices.setProductId(new Product());
      prices.setPriority(1L);
      prices.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
      prices.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));
      prices.setPrice(BigDecimal.valueOf(35.50));
      prices.setCurr(Currency.getInstance("EUR"));

      priceRequestDto = PriceRequestDto.builder().brandName("BrandB").productId(35455L).dateTime(LocalDateTime.of(2020, 6, 14, 10, 0)).build();
   }

   @Test
   void findPricesByProduct_ShouldReturnPriceDto_WhenPriceExists() {
      Mockito.when(brandPersistenceAdapter.findByBrandName(priceRequestDto.getBrandName())).thenReturn(Optional.of(brand));

      Mockito
            .when(pricesPersistenceAdapter.findPricesByProductByBrandAndRequestDate(priceRequestDto.getProductId(), brand.getBrandId(),
                  priceRequestDto.getDateTime()))
            .thenReturn(Optional.of(prices));

      PriceDto result = priceService.findPricesByProduct(priceRequestDto);

      assertNotNull(result);
      assertEquals(prices.getPrice(), result.getPrice());
      assertEquals(prices.getCurr(), result.getCurr());
   }

   @Test
   void findPricesByProduct_ShouldThrowPriceException_WhenBrandNotFound() {
      Mockito.when(brandPersistenceAdapter.findByBrandName(priceRequestDto.getBrandName())).thenReturn(Optional.empty());

      PriceException exception = assertThrows(PriceException.class, () -> priceService.findPricesByProduct(priceRequestDto));

      assertEquals(PriceErrors.BRAND_NOT_FOUND.name() + priceRequestDto.getBrandName(), exception.getMessage());
      assertEquals(PriceErrors.BRAND_NOT_FOUND, exception.getPriceErrors());
   }

   @Test
   void findPricesByProduct_ShouldThrowPriceException_WhenPriceNotAvailable() {
      Mockito.when(brandPersistenceAdapter.findByBrandName(priceRequestDto.getBrandName())).thenReturn(Optional.of(brand));

      Mockito
            .when(pricesPersistenceAdapter.findPricesByProductByBrandAndRequestDate(priceRequestDto.getProductId(), brand.getBrandId(),
                  priceRequestDto.getDateTime()))
            .thenReturn(Optional.empty());

      PriceException exception = assertThrows(PriceException.class, () -> priceService.findPricesByProduct(priceRequestDto));

      assertEquals("Price not available for product: " + priceRequestDto.getProductId(), exception.getMessage());
      assertEquals(PriceErrors.PRICE_NOT_AVAILABLE, exception.getPriceErrors());
   }
}
