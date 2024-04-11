package com.service.countryinfo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.countryinfo.dto.CountryInfoDTO;
import com.service.countryinfo.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Business logic implementation for service class.
 * @author kvasanthakumar
 * @version 0.0.1
 * Date: April 5,2024
 */
@Service
@Slf4j
public class CountryInfoServiceImpl implements CountryInfoService{

    /**
     * Properties injection to autowire WebClient dependency.
     */
    @Autowired
    WebClient webClient;

    /**
     * Description: Method to get country information.
     * @param name country name
     * @param fields list of required fields
     * @return CountryInfoDTO
     */
    public CountryInfoDTO  getCountryInfo(String name, String fields) throws JsonProcessingException {
        log.info("Calling getCountryInfo with the country name {} and fields :{} ", name, fields);
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
        String countryName = data.get(Constants.NAME).get(Constants.COMMON).asText();
        String flagUrl = data.get(Constants.FLAGS).get(Constants.SVG).asText();
        String capital = data.get(Constants.CAPITAL).get(0).asText();
        String countryCode = data.get(Constants.CCA2).asText();
        double population = data.get(Constants.POPULATION).asDouble();
        return new CountryInfoDTO(countryName,countryCode,capital,population,flagUrl);

    }
}
