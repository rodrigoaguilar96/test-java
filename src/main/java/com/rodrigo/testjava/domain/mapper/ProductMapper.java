package com.rodrigo.testjava.domain.mapper;

import com.rodrigo.testjava.domain.model.Product;
import com.rodrigo.testjava.infrastructure.output.entity.ProductEntity;

public class ProductMapper {

   private ProductMapper() {
   }

   public static Product toDomain(ProductEntity productEntity) {
      return Product
            .builder()
            .productId(productEntity.getProductId())
            .productName(productEntity.getProductName())
            .brand(BrandMapper.toDomain(productEntity.getBrand()))
            .build();
   }
}
