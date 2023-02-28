package com.api.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

			// URI
			String url = "http://localhost:8888/api/v1/ping";

			// Headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			// Body
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("campo1", "valor1");
			requestBody.put("campo2", "valor2");

			// Set body and headers
			HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

			// Get template
			//String response = restTemplate.getForObject(url, String.class);

			// Post template
			Map response = restTemplate.postForObject(url,entity, Map.class);

			// Get data
			Map data = (Map) response.get("data");

			// Data manipulation
			String version = (String) data.get("version");

			System.out.println(version);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return "Success";
	}

}
