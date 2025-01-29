package com.rodrigo.testjava.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prices {

   private Long priceId;

   private Brand brandId;

   private LocalDateTime startDate;

   private LocalDateTime endDate;

   private Product productId;

   private Long priority;

   private BigDecimal price;

   private Currency curr;
}
