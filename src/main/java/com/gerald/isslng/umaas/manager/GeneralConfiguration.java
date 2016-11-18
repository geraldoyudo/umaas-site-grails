package com.gerald.isslng.umaas.manager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GeneralConfiguration {
	private static final int TIMEOUT = 10000;

	@Bean
	public RestTemplate restTemplate (){
		RestTemplate rt =  new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(TIMEOUT);
		requestFactory.setReadTimeout(TIMEOUT);
		rt.setRequestFactory(requestFactory);
		return rt;
	}
}
