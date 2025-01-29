package com.rodrigo.testjava.domain.mapper;

import com.rodrigo.testjava.domain.model.Brand;
import com.rodrigo.testjava.infrastructure.output.entity.BrandEntity;

public class BrandMapper {

   private BrandMapper() {}

   public static Brand toDomain(BrandEntity entity) {
      return Brand.builder().brandId(entity.getBrandId()).brandName(entity.getBrandName()).build();
   }

   public static BrandEntity toEntity(Brand domain) {
      return BrandEntity.builder().brandId(domain.getBrandId()).brandName(domain.getBrandName()).build();
   }
}
