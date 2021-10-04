package com.exchangeRateCalculator.service;

import com.exchangeRateCalculator.exchangeRateDTO.ExchangeRateDTO;

public interface CurrencyLayerAPIService {
	ExchangeRateDTO getExchangeRate();
}
