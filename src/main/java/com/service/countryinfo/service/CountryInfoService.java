package com.service.countryinfo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.countryinfo.dto.CountryInfoDTO;
import com.service.countryinfo.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CountryInfoService {

    @Autowired
    WebClient webClient;

    public CountryInfoDTO  getCountryInfo(String name, String fields) throws JsonProcessingException {
        String jsonString = getCountryInfoServiceCall(name, fields);
        return getRequiredData(jsonString);
    }

    private String getCountryInfoServiceCall(String name, String fields){

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(Constants.URI_COUNTRY_NAME)
                        .queryParam(Constants.FIELDS, fields)
                        .build(name))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private CountryInfoDTO getRequiredData(String result) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(result);
        JsonNode data = jsonNode.get(0);
        CountryInfoDTO country = new CountryInfoDTO();
        country.setName(data.get(Constants.NAME).get(Constants.COMMON).asText());
        country.setFlagFileUrl(data.get(Constants.FLAGS).get(Constants.SVG).asText());
        country.setCapital(data.get(Constants.CAPITAL).get(0).asText());
        country.setCountryCode(data.get(Constants.CCA2).asText());
        country.setPopulation(data.get(Constants.POPULATION).asDouble());
        return country;

    }
}
