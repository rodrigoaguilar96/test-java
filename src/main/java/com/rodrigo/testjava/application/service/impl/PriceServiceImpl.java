package com.rodrigo.testjava.application.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rodrigo.testjava.application.service.PriceService;
import com.rodrigo.testjava.domain.dto.PriceDto;
import com.rodrigo.testjava.domain.dto.request.PriceRequestDto;
import com.rodrigo.testjava.domain.enums.PriceErrors;
import com.rodrigo.testjava.domain.exceptions.PriceException;
import com.rodrigo.testjava.domain.mapper.PricesMapper;
import com.rodrigo.testjava.domain.model.Brand;
import com.rodrigo.testjava.domain.model.Prices;
import com.rodrigo.testjava.infrastructure.output.adapter.BrandPersistenceAdapter;
import com.rodrigo.testjava.infrastructure.output.adapter.PricesPersistenceAdapter;

@Service
public class PriceServiceImpl implements PriceService {

   private final PricesPersistenceAdapter pricesPersistenceAdapter;

   private final BrandPersistenceAdapter brandPersistenceAdapter;

   public PriceServiceImpl(PricesPersistenceAdapter pricesPersistenceAdapter, BrandPersistenceAdapter brandPersistenceAdapter) {
      this.pricesPersistenceAdapter = pricesPersistenceAdapter;
      this.brandPersistenceAdapter = brandPersistenceAdapter;
   }

   @Override
   public PriceDto findPricesByProduct(PriceRequestDto priceRequestDto) {
      Brand brandEntity = brandPersistenceAdapter
            .findByBrandName(priceRequestDto.getBrandName())
            .orElseThrow(() -> new PriceException(PriceErrors.BRAND_NOT_FOUND.name() + priceRequestDto.getBrandName(), PriceErrors.BRAND_NOT_FOUND));

      Optional<Prices> optionalPricesEntity = pricesPersistenceAdapter.findPricesByProductByBrandAndRequestDate(priceRequestDto.getProductId(),
            brandEntity.getBrandId(), priceRequestDto.getDateTime());
      return optionalPricesEntity
            .map(PricesMapper::mapPriceToDto)
            .orElseThrow(
                  () -> new PriceException("Price not available for product: " + priceRequestDto.getProductId(), PriceErrors.PRICE_NOT_AVAILABLE));
   }
}
