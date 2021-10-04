package com.exchangeRateCalculator.controller;

import java.text.DecimalFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exchangeRateCalculator.exchangeRateDTO.ExchangeRateDTO;
import com.exchangeRateCalculator.service.CurrencyLayerAPIService;

@Controller
public class ExchangeRateCalculatorController {
	@Autowired
	private CurrencyLayerAPIService currencyLayerAPIService;
	
	private DecimalFormat decimalFormat = new DecimalFormat("0.00");
	
	@RequestMapping(value = "/index.html")
	public String openIndex(Model model) {
		//페이지를 호출 할 때 최초 1회 환율을 받아서 front에 저장
		ExchangeRateDTO exchangeRateDTO = currencyLayerAPIService.getExchangeRate();
		
		Map<String, Object> exchangeRates = exchangeRateDTO.getQuotes();
		String from = exchangeRateDTO.getSource();
		
		model.addAttribute("KRW", format(exchangeRates.get(from + "KRW")));
		model.addAttribute("JPY", format(exchangeRates.get(from + "JPY")));
		model.addAttribute("PHP", format(exchangeRates.get(from + "PHP")));

		return "index";
	}
	
	public String format(Object number) {
		return decimalFormat.format(number);
	}
}
