package com.airbnb.service.impl;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import com.airbnb.entity.Property;
import com.airbnb.payload.PropertyDto;
import com.airbnb.repository.CityRepository;
import com.airbnb.repository.CountryRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {
    private PropertyRepository propertyRepository;
    private ModelMapper modelMapper;

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository, ModelMapper modelMapper,
                               CountryRepository countryRepository,
                               CityRepository cityRepository) {
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;

        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public PropertyDto addProperty(PropertyDto propertyDto, long countryId, long cityId) {
        Country country=countryRepository.findById(countryId).get();
        City city=cityRepository.findById(cityId).get();
        Property property = mapToEntity(propertyDto);
        property.setCity(city);
        property.setCountry(country);
        Property save = propertyRepository.save(property);
        return mapToDto(save);
    }

    @Override
    public PropertyDto getProperty(long pId) {
        Optional<Property>opProperty=propertyRepository.findById(pId);
        if(opProperty.isPresent()){
            return mapToDto(opProperty.get());
        }
        return null;
    }

    @Override
    public void deleteProperty(long pId) {
        propertyRepository.deleteById(pId);
    }

    @Override
    public PropertyDto updateProperty(long pId, PropertyDto dto, long countryId, long cityId) {
        Optional<Property> opProperty=propertyRepository.findById(pId);
        if(opProperty.isPresent()){
            Property property=mapToEntity(dto);
            Country country=countryRepository.findById(countryId).orElseThrow(
                    ( )-> new RuntimeException("Country not found")
            );
            City city=cityRepository.findById(cityId).orElseThrow(
                    () -> new RuntimeException("City not found")
            );
            property.setId(pId);
            property.setCity(city);
            property.setCountry(country);
            Property save = propertyRepository.save(property);
            return mapToDto(save);
        }
        return null;
    }

    @Override
    public List<Property> searchProperty(String location) {
        List<Property> properties = propertyRepository.searchPropertyOnCountryOrCity(location);
        return properties;
    }

    PropertyDto mapToDto(Property property){
        return modelMapper.map(property, PropertyDto.class);
    }

    Property mapToEntity(PropertyDto propertyDto){
        return modelMapper.map(propertyDto, Property.class);
    }
}
