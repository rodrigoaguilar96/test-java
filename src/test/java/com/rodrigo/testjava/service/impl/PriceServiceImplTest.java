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

import com.rodrigo.testjava.domain.dto.PriceDto;
import com.rodrigo.testjava.domain.dto.request.PriceRequestDto;
import com.rodrigo.testjava.domain.entity.BrandEntity;
import com.rodrigo.testjava.domain.entity.PricesEntity;
import com.rodrigo.testjava.domain.entity.ProductEntity;
import com.rodrigo.testjava.domain.enums.PriceErrors;
import com.rodrigo.testjava.domain.exceptions.PriceException;
import com.rodrigo.testjava.domain.repository.BrandRepository;
import com.rodrigo.testjava.domain.repository.PricesRepository;

@ExtendWith(MockitoExtension.class)
public class PriceServiceImplTest {

   @Mock
   private PricesRepository pricesRepository;

   @Mock
   private BrandRepository brandRepository;

   @InjectMocks
   private PriceServiceImpl priceService;

   private PriceRequestDto priceRequestDto;

   private BrandEntity brandEntity;

   private PricesEntity pricesEntity;

   @BeforeEach
   void setUp() {
      brandEntity = new BrandEntity();
      brandEntity.setBrandId(1L);
      brandEntity.setBrandName("BrandB");

      pricesEntity = new PricesEntity();
      pricesEntity.setPriceId(1L);
      pricesEntity.setBrandId(brandEntity);
      pricesEntity.setProductId(new ProductEntity());
      pricesEntity.setPriority(1L);
      pricesEntity.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
      pricesEntity.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));
      pricesEntity.setPrice(BigDecimal.valueOf(35.50));
      pricesEntity.setCurr(Currency.getInstance("EUR"));

      priceRequestDto = PriceRequestDto.builder().brandName("BrandB").productId(35455L).dateTime(LocalDateTime.of(2020, 6, 14, 10, 0)).build();
   }

   @Test
   void findPricesByProduct_ShouldReturnPriceDto_WhenPriceExists() {
      Mockito.when(brandRepository.findByBrandName(priceRequestDto.getBrandName())).thenReturn(Optional.of(brandEntity));

      Mockito
            .when(pricesRepository.findPricesByProductByBrandAndRequestDate(priceRequestDto.getProductId(), brandEntity.getBrandId(),
                  priceRequestDto.getDateTime()))
            .thenReturn(Optional.of(pricesEntity));

      PriceDto result = priceService.findPricesByProduct(priceRequestDto);

      assertNotNull(result);
      assertEquals(pricesEntity.getPrice(), result.getPrice());
      assertEquals(pricesEntity.getCurr(), result.getCurr());
   }

   @Test
   void findPricesByProduct_ShouldThrowPriceException_WhenBrandNotFound() {
      Mockito.when(brandRepository.findByBrandName(priceRequestDto.getBrandName())).thenReturn(Optional.empty());

      PriceException exception = assertThrows(PriceException.class, () -> priceService.findPricesByProduct(priceRequestDto));

      assertEquals(PriceErrors.BRAND_NOT_FOUND.name() + priceRequestDto.getBrandName(), exception.getMessage());
      assertEquals(PriceErrors.BRAND_NOT_FOUND, exception.getPriceErrors());
   }

   @Test
   void findPricesByProduct_ShouldThrowPriceException_WhenPriceNotAvailable() {
      Mockito.when(brandRepository.findByBrandName(priceRequestDto.getBrandName())).thenReturn(Optional.of(brandEntity));

      Mockito
            .when(pricesRepository.findPricesByProductByBrandAndRequestDate(priceRequestDto.getProductId(), brandEntity.getBrandId(),
                  priceRequestDto.getDateTime()))
            .thenReturn(Optional.empty());

      PriceException exception = assertThrows(PriceException.class, () -> priceService.findPricesByProduct(priceRequestDto));

      assertEquals("Price not available for product: " + priceRequestDto.getProductId(), exception.getMessage());
      assertEquals(PriceErrors.PRICE_NOT_AVAILABLE, exception.getPriceErrors());
   }
}
