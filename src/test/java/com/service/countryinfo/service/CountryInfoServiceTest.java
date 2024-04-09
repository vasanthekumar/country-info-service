package com.service.countryinfo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.service.countryinfo.dto.CountryInfoDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CountryInfoServiceTest {
    @Autowired
    private CountryInfoService countryInfoService;

    static WireMockServer wireMockServer;

    @BeforeAll
    public static void setUp(){

        wireMockServer = new WireMockServer();
        wireMockServer.start();
        configureFor("localhost", 8080);

        wireMockServer.stubFor(get(urlEqualTo("/name/Finland"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("[{\"name\":{\"common\":\"Finland\"},\"flags\":{\"svg\":\"https://flagcdn.com/fi.svg\"},\"capital\":[\"Helsinki\"],\"cca2\":\"FI\",\"population\":5530719}]"))
        );

    }
    @Test
    void testGetCountryInfo() throws JsonProcessingException {

        CountryInfoDTO countryInfoDTO = countryInfoService.getCountryInfo("Finland", "name,flags,capital,cca2,population");

        // Verify that the expected data is extracted from the response
        assertEquals("Finland", countryInfoDTO.getName());
        assertEquals("https://flagcdn.com/fi.svg", countryInfoDTO.getFlagFileUrl());
        assertEquals("Helsinki", countryInfoDTO.getCapital());
        assertEquals("FI", countryInfoDTO.getCountryCode());
        assertEquals(5530719, countryInfoDTO.getPopulation());
    }
}
