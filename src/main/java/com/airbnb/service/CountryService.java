package com.airbnb.service;

import com.airbnb.payload.CountryDto;

import java.util.List;

public interface CountryService {
    CountryDto addCountry(CountryDto countryDto);

    void deleteCountry(long cId);

    CountryDto updateCountry(CountryDto dto, long cId);

    List<CountryDto> getAllCountry();

    List<CountryDto> addCountryList(List<CountryDto> dtos);
}
