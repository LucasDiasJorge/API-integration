package com.api.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class IntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrationApplication.class, args);
	}

	@GetMapping("/")
	public String index(){

		try{

			RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:8888/api/v1/ping";
			String response = restTemplate.getForObject(url, String.class);
			System.out.println(response);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return "Success";
	}

}
