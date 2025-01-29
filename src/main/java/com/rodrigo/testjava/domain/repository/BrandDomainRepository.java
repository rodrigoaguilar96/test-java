package com.rodrigo.testjava.domain.repository;

import java.util.Optional;

import com.rodrigo.testjava.domain.model.Brand;

public interface BrandDomainRepository {

   Optional<Brand> findByBrandName(String brandName);
}
