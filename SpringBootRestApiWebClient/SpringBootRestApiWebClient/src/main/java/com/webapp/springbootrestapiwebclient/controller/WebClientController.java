package com.webapp.springbootrestapiwebclient.controller;

import java.util.List;



import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.webapp.springbootrestapiwebclient.entity.Customer;
import com.webapp.springbootrestapiwebclient.exception.CustomerNotFoundException;

public class WebClientController {

	private static WebClient webClient;

	public static Customer getCustomerInfoById(Integer customerId) {

		try {
			webClient = WebClient.builder().baseUrl("http://localhost:8080/springbootrestapi")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
		Customer cust = webClient.get().uri("/customers/" + customerId).retrieve().bodyToMono(Customer.class).block();  //mono -single
		
		return cust;
		}catch (Exception e) {
			throw new CustomerNotFoundException("Customer Not Exist In First API Call ... ");
		}
	}

	public static List<Customer> retrieveAllCustomer() {
		webClient = WebClient.builder().baseUrl("http://localhost:8080/springbootrestapi")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
		List<Customer> cust = webClient.get().uri("/customers/").retrieve().bodyToFlux(Customer.class) // flux means many
																										
				.collectList().block();
		return cust;

	}

}
