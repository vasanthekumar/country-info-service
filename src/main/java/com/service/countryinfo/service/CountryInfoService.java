package com.service.countryinfo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.countryinfo.dto.CountryInfoDTO;

public interface CountryInfoService {
    CountryInfoDTO getCountryInfo(String name, String fields) throws JsonProcessingException;
}
