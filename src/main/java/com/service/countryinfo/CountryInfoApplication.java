package com.service.countryinfo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * @author kvasanthakumar
 * @version 0.0.1
 * Date: April 4,2024
 */
@SpringBootApplication
public class CountryInfoApplication {

    /**
     * Launches the application
     * @param args - Application startup arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CountryInfoApplication.class, args);
    }

    //Country info external URL
    @Value("${country.info.url}")
    private String countryInfoURL;

    //Webclient bean definition.
    @Bean
    WebClient webClient() {
        return WebClient.create(countryInfoURL);
    }
}
