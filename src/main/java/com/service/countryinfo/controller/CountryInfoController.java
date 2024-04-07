package com.service.countryinfo.controller;

import com.service.countryinfo.service.CountryInfoService;
import com.service.countryinfo.util.ResponseHandler;
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

    @GetMapping(value = "country/info/{name}")
    public ResponseEntity<Object> getCountryInfoByName(@PathVariable String name,@RequestParam String fields) {
        System.out.println("Application Running......" +fields);
        return ResponseHandler.responseBuilder("Requested country details are given here", HttpStatus.OK, countryInfoService.getCountryInfo(name,fields));
    }
}
