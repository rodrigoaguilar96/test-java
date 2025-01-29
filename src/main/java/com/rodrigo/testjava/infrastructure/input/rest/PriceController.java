package com.rodrigo.testjava.infrastructure.input.rest;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.testjava.application.service.PriceService;
import com.rodrigo.testjava.domain.dto.PriceDto;
import com.rodrigo.testjava.domain.dto.request.PriceRequestDto;

@RestController
@RequestMapping("/v1/price")
public class PriceController {

   private final PriceService priceService;

   public PriceController(PriceService priceService) {
      this.priceService = priceService;
   }

   @GetMapping
   public ResponseEntity<PriceDto> findPriceByProduct(@RequestParam LocalDateTime dateTime, @RequestParam Long productId,
         @RequestParam String brandName) {
      PriceRequestDto priceRequestDto = PriceRequestDto.builder().dateTime(dateTime).productId(productId).brandName(brandName).build();
      return new ResponseEntity<>(priceService.findPricesByProduct(priceRequestDto), HttpStatus.OK);
   }
}
