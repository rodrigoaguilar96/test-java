package com.rodrigo.testjava.domain.mapper;

import java.util.Objects;

import com.rodrigo.testjava.domain.dto.PriceDto;
import com.rodrigo.testjava.domain.model.Prices;
import com.rodrigo.testjava.infrastructure.output.entity.PricesEntity;

public class PricesMapper {

   private PricesMapper() {
   }

   public static PriceDto mapPriceToDto(Prices prices) {
      if (Objects.nonNull(prices) && Objects.nonNull(prices.getBrandId())) {
         return PriceDto
               .builder()
               .brandName(prices.getBrandId().getBrandName())
               .productId(prices.getProductId().getProductId())
               .price(prices.getPrice())
               .curr(prices.getCurr())
               .build();
      } else {
         return null;
      }

   }

   public static Prices toDomain(PricesEntity pricesEntity) {
      if (Objects.nonNull(pricesEntity)) {
         return Prices
               .builder()
               .priceId(pricesEntity.getPriceId())
               .brandId(BrandMapper.toDomain(pricesEntity.getBrandId()))
               .startDate(pricesEntity.getStartDate())
               .endDate(pricesEntity.getEndDate())
               .productId(ProductMapper.toDomain(pricesEntity.getProductId()))
               .priority(pricesEntity.getPriority())
               .price(pricesEntity.getPrice())
               .curr(pricesEntity.getCurr())
               .build();
      } else {
         return null;
      }
   }
}
