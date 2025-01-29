package com.rodrigo.testjava.infrastructure.output.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.rodrigo.testjava.domain.mapper.BrandMapper;
import com.rodrigo.testjava.domain.model.Brand;
import com.rodrigo.testjava.domain.repository.BrandDomainRepository;
import com.rodrigo.testjava.infrastructure.output.repository.BrandRepository;

@Repository
public class BrandPersistenceAdapter implements BrandDomainRepository {

   private final BrandRepository brandRepository;

   public BrandPersistenceAdapter(BrandRepository brandRepository) {
      this.brandRepository = brandRepository;
   }

   @Override
   public Optional<Brand> findByBrandName(String brandName) {
      return brandRepository.findByBrandName(brandName).flatMap(entity -> Optional.ofNullable(BrandMapper.toDomain(entity)));
   }
}
