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
        return WebClient.builder().baseUrl(countryInfoUrl).build();
    }

    public String getCountryInfo(String name){
        return webClient()
                .get()
                .uri("/name/{countryName}",name)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
