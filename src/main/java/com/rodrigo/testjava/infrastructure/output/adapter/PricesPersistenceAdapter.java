package com.rodrigo.testjava.infrastructure.output.adapter;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.rodrigo.testjava.domain.mapper.PricesMapper;
import com.rodrigo.testjava.domain.model.Prices;
import com.rodrigo.testjava.domain.repository.PricesDomainRepository;
import com.rodrigo.testjava.infrastructure.output.repository.PricesRepository;

@Repository
public class PricesPersistenceAdapter implements PricesDomainRepository {

   private final PricesRepository pricesRepository;

   public PricesPersistenceAdapter(PricesRepository pricesRepository) {
      this.pricesRepository = pricesRepository;
   }

   @Override
   public Optional<Prices> findPricesByProductByBrandAndRequestDate(Long productId, Long brandId, LocalDateTime requestDate) {
      return pricesRepository
            .findPricesByProductByBrandAndRequestDate(productId, brandId, requestDate)
            .flatMap(entity -> Optional.ofNullable(PricesMapper.toDomain(entity)));
   }
}
