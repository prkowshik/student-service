package com.codezen.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class StudentConfiguration {
	
	@Value("${address.base.url}")
	private String addressBaseUrl;
	
	
//	  @Bean
//	   public RestTemplate getRestTemplate() {
//	      return new RestTemplate();
//	   }
//	@Bean
//	public WebClient webClient() {
//	  return WebClient.builder().baseUrl(addressBaseUrl).build();
//	}




}
