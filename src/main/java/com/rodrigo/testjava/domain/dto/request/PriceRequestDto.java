package com.rodrigo.testjava.domain.dto.request;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PriceRequestDto {

   private LocalDateTime dateTime;

   private Long productId;

   private String brandName;
}
