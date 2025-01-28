package com.rodrigo.testjava.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rodrigo.testjava.domain.dto.PriceDto;
import com.rodrigo.testjava.domain.dto.request.PriceRequestDto;
import com.rodrigo.testjava.domain.entity.BrandEntity;
import com.rodrigo.testjava.domain.entity.PricesEntity;
import com.rodrigo.testjava.domain.enums.PriceErrors;
import com.rodrigo.testjava.domain.exceptions.PriceException;
import com.rodrigo.testjava.domain.repository.BrandRepository;
import com.rodrigo.testjava.domain.repository.PricesRepository;
import com.rodrigo.testjava.domain.util.PricesUtils;
import com.rodrigo.testjava.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {

   private final PricesRepository pricesRepository;

   private final BrandRepository brandRepository;

   public PriceServiceImpl(PricesRepository pricesRepository, BrandRepository brandRepository) {
      this.pricesRepository = pricesRepository;
      this.brandRepository = brandRepository;
   }

   @Override
   public PriceDto findPricesByProduct(PriceRequestDto priceRequestDto) {
      BrandEntity brandEntity = brandRepository
            .findByBrandName(priceRequestDto.getBrandName())
            .orElseThrow(() -> new PriceException(PriceErrors.BRAND_NOT_FOUND.name() + priceRequestDto.getBrandName(), PriceErrors.BRAND_NOT_FOUND));

      Optional<PricesEntity> optionalPricesEntity = pricesRepository.findPricesByProductByBrandAndRequestDate(priceRequestDto.getProductId(),
            brandEntity.getBrandId(), priceRequestDto.getDateTime());
      return optionalPricesEntity
            .map(PricesUtils::mapPriceEntity)
            .orElseThrow(
                  () -> new PriceException("Price not available for product: " + priceRequestDto.getProductId(), PriceErrors.PRICE_NOT_AVAILABLE));
   }
}
