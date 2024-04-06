package com.service.countryinfo.controller;

import com.service.countryinfo.service.CountryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * *
 */
@RestController
public class CountryInfoController {

    @Autowired
    CountryInfoService countryInfoService;

    @GetMapping(value = "country/into/{name}")
    public String getCountryInfoByName(@PathVariable String name) {
        System.out.println("Application Running......");
        return countryInfoService.getCountryInfo(name);
    }
}
