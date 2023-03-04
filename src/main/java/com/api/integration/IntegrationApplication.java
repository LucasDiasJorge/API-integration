package com.api.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
	String integration() throws IOException, InterruptedException {

		HttpRequest requestGet = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create("http://localhost:8888/api/v1/ping"))
				//.setHeader("Authorization", "Bearer " + token)
				.build();


		HttpRequest requestPost = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString("{\"some body text\"}"))
				.setHeader("Content-Type", "application/json")
				.uri(URI.create("http://localhost:8888/api/v1/ping"))
				//.setHeader("Authorization", "Bearer " + token)
				.build();

		HttpClient client = HttpClient.newBuilder().build();

		HttpResponse<String> response = client.send(requestPost,
				HttpResponse.BodyHandlers.ofString());

        /*Gson json = new Gson();

        Map map = json.fromJson(response.body(), Map.class);
        */

		return response.body();
	}

}
