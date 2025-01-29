package com.rodrigo.testjava.infrastructure.output.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@Table(name = "brand")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BrandEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long brandId;

   @Column(name = "brand_name")
   private String brandName;
}
