package com.rodrigo.testjava.application.service;

import com.rodrigo.testjava.domain.dto.PriceDto;
import com.rodrigo.testjava.domain.dto.request.PriceRequestDto;

public interface PriceService {

   PriceDto findPricesByProduct(PriceRequestDto priceRequestDto);
}
