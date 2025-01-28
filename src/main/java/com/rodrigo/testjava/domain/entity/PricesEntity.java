package com.rodrigo.testjava.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import com.rodrigo.testjava.domain.converter.CurrencyConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "prices")
@Data
public class PricesEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long priceId;

   @ManyToOne
   @JoinColumn(name = "brand_id", referencedColumnName = "brandId")
   private BrandEntity brandId;

   private LocalDateTime startDate;

   private LocalDateTime endDate;

   @ManyToOne
   @JoinColumn(name = "product_id", referencedColumnName = "productId")
   private ProductEntity productId;

   private Long priority;

   private BigDecimal price;

   @Convert(converter = CurrencyConverter.class)
   private Currency curr;
}
