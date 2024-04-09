package com.service.countryinfo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.countryinfo.dto.CountryInfoDTO;
import com.service.countryinfo.service.CountryInfoService;
import com.service.countryinfo.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> errorEndpoint(RuntimeException ex){
        String errorMessage = "Internal Server Error";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
