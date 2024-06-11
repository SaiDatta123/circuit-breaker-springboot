package com.wipro.batch2circuitbreaker.controller;

import com.wipro.batch2circuitbreaker.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class HomeController {
	
	@Autowired
	RestTemplate restTemplate;


	@CircuitBreaker(name = "countriesCircuitBreaker", fallbackMethod = "showServiceDown")
	@GetMapping("/product/{id}")
	String getProduct(@PathVariable int id)
	{
		String url= "http://PRODUCT/product/"+id;
		Product product =  restTemplate.getForObject(url, Product.class);

		return product.toString();

	}

	
	String showServiceDown(Throwable throwable)
	{
		
		System.out.println("System is down");
		
		return "System is down";
	}
}
