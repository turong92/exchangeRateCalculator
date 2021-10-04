package com.exchangeRateCalculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exchangeRateCalculator.exchangeRateDTO.ExchangeRateDTO;
import com.exchangeRateCalculator.service.CurrencyLayerAPIService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class ExchangeRateCalculatorApplicationTests {
	
	@Autowired
	CurrencyLayerAPIService currencyLayerAPIService;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void testOfgetExchangeRate() {
		//api 동작 테스트
		ExchangeRateDTO result = currencyLayerAPIService.getExchangeRate();
		
		try {
			String json = new ObjectMapper().writeValueAsString(result);
			System.out.println("=======================");
			System.out.println(json);
			System.out.println("=======================");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}

}
