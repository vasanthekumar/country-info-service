package com.service.countryinfo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.countryinfo.dto.CountryInfoDTO;
import com.service.countryinfo.service.CountryInfoService;
import com.service.countryinfo.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kvasanthekumar
 */
@RestController
public class CountryInfoController {

    @Autowired
    CountryInfoService countryInfoService;

    @GetMapping(value = "/country/info/{name}")
    public ResponseEntity<Object> getCountryInfoByName(@PathVariable String name) {
        CountryInfoDTO countryInfoDTO = null;
        try {
            countryInfoDTO = countryInfoService.getCountryInfo(name, Constants.FIELDS_LIST);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(countryInfoDTO);
    }

    @GetMapping("/error")
    public ResponseEntity<String> errorEndpoint(){
        String errorMessage = "Internal Server Error";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
