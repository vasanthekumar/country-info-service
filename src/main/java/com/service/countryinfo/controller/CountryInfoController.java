package com.service.countryinfo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.countryinfo.dto.CountryInfoDTO;
import com.service.countryinfo.service.CountryInfoServiceImpl;
import com.service.countryinfo.Constants.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contains country information related Api calls .
 * @author kvasanthakumar
 * @version 0.0.1
 * Date: April 4,2024
 */
@RestController
@Slf4j
@RequestMapping(value = "/v1")
public class CountryInfoController {

    /**
     * Properties injection to autowire CountryInfoService dependency.
     */
    @Autowired
    CountryInfoServiceImpl countryInfoService;

    /**
     * Description : Get a country details by Name.
     * Endpoint: GET /country/info/{name}
     * @param name The name of the country
     * @return The Response entity with specified Name,Population etc
     */
    @GetMapping(value = "/country/info/{name}")
    public ResponseEntity<CountryInfoDTO> getCountryInfoByName(@PathVariable String name) {
        log.info("Calling getCountryInfoByName with the country name : {} ", name );
        CountryInfoDTO countryInfoDTO = null;
        try {
            countryInfoDTO = countryInfoService.getCountryInfo(name, Constant.FIELDS_LIST);
            log.info("Return from service call : {} ", countryInfoDTO.toString() );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(countryInfoDTO);
    }

    /**
     * Description : Exception handler.
     * @param ex Runtime exception
     * @return The Response entity with error message.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> errorEndpoint(RuntimeException ex){
        String errorMessage = "Internal Server Error";
        log.info("Exception occurred: {} ", errorMessage );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
