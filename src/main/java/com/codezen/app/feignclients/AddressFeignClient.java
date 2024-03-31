package com.codezen.app.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codezen.app.model.Address;

@FeignClient(value = "api-gateway")
public interface AddressFeignClient {
	
	@GetMapping("/address-service/api/address/getById/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") int id);
}