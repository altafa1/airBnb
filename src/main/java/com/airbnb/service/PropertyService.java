package com.airbnb.service;

import com.airbnb.entity.Property;
import com.airbnb.payload.PropertyDto;

import java.util.List;

public interface PropertyService {
    PropertyDto addProperty(PropertyDto propertyDto, long countryId, long cityId);

    PropertyDto getProperty(long pId);

    void deleteProperty(long pId);

    PropertyDto updateProperty(long pId, PropertyDto dto, long countryId, long cityId);

    List<Property> searchProperty(String location);
}
