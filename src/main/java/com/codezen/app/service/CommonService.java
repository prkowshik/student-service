package com.codezen.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codezen.app.feignclients.AddressFeignClient;
import com.codezen.app.model.Address;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CommonService {
	
	@Autowired
	private AddressFeignClient addressFeignClient;
	
	long count=1;
	
//	@Autowired
//	private RestTemplate restTemplate;
	
//	@Autowired
//	private WebClient webClient;
	

	
	@CircuitBreaker(name="addressService", 
			fallbackMethod = "fallbackGetAddressById")
	public Address getAddressById(Integer addressId) {
		Address address = new Address();
		
		// Before spring 2.5, we were using Rest template
		
		/*HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		address = restTemplate.exchange("http://localhost:8080/api/address/getById/" +addressId, HttpMethod.GET, entity, Address.class).getBody();*/
		
		//After spring 2.5 webClient is introduced as resttemplate is gonna deprecate
		
//		address = webClient.get().uri("getById/" + addressId).retrieve().bodyToMono(Address.class).block();
		
		System.out.println("Count..."+count);
		count++;
		
		address = addressFeignClient.getAddressById(addressId).getBody();
		
		
		return address;
	}
	
	//method signature should be same as method with @circuitbreaker enabled
	public Address fallbackGetAddressById(Integer addressId, Throwable exceptionMsg) {
		System.out.println("Error is..."+exceptionMsg);
		return new Address();
	}


}
