package com.rodrigo.testjava.domain.dto;

import java.math.BigDecimal;
import java.util.Currency;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PriceDto {

   private String brandName;

   private Long productId;

   private BigDecimal price;

   private Currency curr;
}
