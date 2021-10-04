package com.exchangeRateCalculator.exchangeRateDTO;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateDTO {
	private boolean success;
	private Long timestamp;
	private String source;
	private Map<String, Object> quotes;
}
