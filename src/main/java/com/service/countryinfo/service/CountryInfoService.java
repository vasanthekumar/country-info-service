package com.service.countryinfo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CountryInfoService {

    @Value("${country.info.url}")
    private String countryInfoUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.create(countryInfoUrl);
    }

    public String getCountryInfo(String name, String fields) {
        return webClient()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/name/{countryName}")
                        .queryParam("fields", fields)
                        .build(name))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
