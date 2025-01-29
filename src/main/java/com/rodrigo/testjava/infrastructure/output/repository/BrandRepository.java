package com.rodrigo.testjava.infrastructure.output.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigo.testjava.infrastructure.output.entity.BrandEntity;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

   Optional<BrandEntity> findByBrandName(String brandName);
}
