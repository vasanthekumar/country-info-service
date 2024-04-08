package com.service.countryinfo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class CountryInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountryInfoApplication.class, args);
    }

    @Value("${country.info.url}")
    private String countryInfoURL;

    @Bean
    WebClient webClient() {
        return WebClient.create(countryInfoURL);
    }
}
