package com.exchangeRateCalculator.service;

import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exchangeRateCalculator.exchangeRateDTO.ExchangeRateDTO;


@Service
public class CurrencyLayerAPIServiceImpl implements CurrencyLayerAPIService{
	@Value("${currencylayer.accesskey}")
	private String accessKey;
	@Value("${currencylayer.url}")
	private String url;
	@Value("${currencylayer.currencies}")
	private String currencies;
	@Value("${currencylayer.source}")
	private String source;
	
	static CloseableHttpClient httpClient = HttpClients.createDefault();
	
	@Override
	public ExchangeRateDTO getExchangeRate() {
		//currencylayer API로 환율 받아와서 사용할 dto에 넣어서 리턴
		
		ExchangeRateDTO exchangeRateDTO = new ExchangeRateDTO();
		
		HttpGet get = new HttpGet(url + "?access_key=" + accessKey + "&currencies=" + currencies + "&source=" + source);
		//http://apilayer.net/api/live?access_key={ACCESS_KEY}&currencies=KRW,JPY,PHP&source=USD
		
		try {
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			
			JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));

			exchangeRateDTO.setSource(exchangeRates.getString("source"));
			exchangeRateDTO.setTimestamp(exchangeRates.getLong("timestamp"));
			exchangeRateDTO.setSuccess(exchangeRates.getBoolean("success"));
			exchangeRateDTO.setQuotes(exchangeRates.getJSONObject("quotes").toMap());
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
//		ExchangeRateDTO exchangeRateDTO = new ExchangeRateDTO();
//		
//		JSONObject exchangeRates = new JSONObject("{\"terms\":\"https://currencylayer.com/terms\",\"success\":true,\"privacy\":\"https://currencylayer.com/privacy\",\"source\":\"USD\",\"timestamp\":1633248544,\"quotes\":{\"USDJPY\":111.076504,\"USDKRW\":1182.620384,\"USDPHP\":50.740785}}");
//		System.out.println(exchangeRates);
//		exchangeRateDTO.setSource(exchangeRates.getString("source"));
//		exchangeRateDTO.setTimestamp(exchangeRates.getLong("timestamp"));
//		exchangeRateDTO.setSuccess(exchangeRates.getBoolean("success"));
//		exchangeRateDTO.setQuotes(exchangeRates.getJSONObject("quotes").toMap());
		
		return exchangeRateDTO;
	}
}
