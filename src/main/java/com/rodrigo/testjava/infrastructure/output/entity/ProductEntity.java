package com.rodrigo.testjava.infrastructure.output.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class ProductEntity {

   @Id
   private Long productId;

   @ManyToOne
   @JoinColumn(name = "brand_id", referencedColumnName = "brandId")
   private BrandEntity brand;

   private String productName;
}
