package com.rodrigo.testjava.domain.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.rodrigo.testjava.domain.model.Prices;

public interface PricesDomainRepository {

   Optional<Prices> findPricesByProductByBrandAndRequestDate(Long productId, Long brandId, LocalDateTime requestDate);
}
