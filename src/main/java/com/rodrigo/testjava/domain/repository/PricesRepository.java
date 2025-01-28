package com.rodrigo.testjava.domain.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rodrigo.testjava.domain.entity.PricesEntity;

@Repository
public interface PricesRepository extends JpaRepository<PricesEntity, Long> {

   @Query("SELECT p FROM PricesEntity p WHERE p.productId.productId = :productId AND p.brandId.brandId = :brandId AND :requestDate BETWEEN p"
         + ".startDate AND p.endDate order by p.priority desc limit 1")
   Optional<PricesEntity> findPricesByProductByBrandAndRequestDate(Long productId, Long brandId, LocalDateTime requestDate);
}
