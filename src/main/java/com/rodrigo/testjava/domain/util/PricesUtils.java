package com.rodrigo.testjava.domain.util;

import com.rodrigo.testjava.domain.dto.PriceDto;
import com.rodrigo.testjava.domain.entity.PricesEntity;

public class PricesUtils {

   public static PriceDto mapPriceEntity(PricesEntity pricesEntity) {
      return PriceDto
            .builder()
            .brandName(pricesEntity.getBrandId().getBrandName())
            .productId(pricesEntity.getProductId().getProductId())
            .price(pricesEntity.getPrice())
            .curr(pricesEntity.getCurr())
            .build();
   }
}
