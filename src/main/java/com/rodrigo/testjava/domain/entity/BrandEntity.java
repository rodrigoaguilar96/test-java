package com.rodrigo.testjava.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Setter
@Table(name = "brand")
public class BrandEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long brandId;

   @Column(name = "brand_name")
   private String brandName;
}
