package com.airbnb.service;

import com.airbnb.payload.CityDto;

import java.util.List;

public interface CityService {
    CityDto addCity(CityDto dto);

    List<CityDto> addCityList(List<CityDto> dtos);

    void deleteCountry(long cId);

    CityDto updateCountry(CityDto dto, long cId);

    List<CityDto> getAllCity();
}
