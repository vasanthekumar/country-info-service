package com.service.countryinfo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.countryinfo.dto.CountryInfoDTO;
import com.service.countryinfo.service.CountryInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(CountryInfoController.class)
class CountryInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryInfoService countryInfoService;

    @Test
    void testGetCountryInfoByName() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/country/info/Finland")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void testGetCountryInfoByName_With_Country_Info() throws Exception {

        when(countryInfoService.getCountryInfo(anyString(),anyString())).thenReturn(getCountryInfoDTO());
        mockMvc.perform(MockMvcRequestBuilders.get("/country/info/Finland")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

//    @Test
//    void testGetCountryInfoByName_Throw_Exception() throws Exception {
//
//        when(countryInfoService.getCountryInfo(eq("Finland"),eq(Constants.FIELDS))).thenThrow(JsonProcessingException.class);
//
//        assertThrows(RuntimeException.class, () -> {
//            mockMvc.perform(MockMvcRequestBuilders.get("/country/info/Finland"));
//        });
//
//    }

    @Test
    void errorEndpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/error")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    private CountryInfoDTO getCountryInfoDTO(){
        CountryInfoDTO country = new CountryInfoDTO();
        country.setName("Finland");
        country.setFlagFileUrl("https://flagcdn.com/fi.svg");
        country.setCapital("Helsinki");
        country.setCountryCode("FI");
        country.setPopulation(5530719.0);

        return country;
    }
}
