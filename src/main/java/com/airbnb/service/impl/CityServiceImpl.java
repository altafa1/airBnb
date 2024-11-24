package com.airbnb.service.impl;

import com.airbnb.entity.City;
import com.airbnb.payload.CityDto;
import com.airbnb.repository.CityRepository;
import com.airbnb.service.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;
    private ModelMapper modelMapper;

    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CityDto addCity(CityDto dto) {
        City city = mapToEntity(dto);
        City save = cityRepository.save(city);
        return mapToDto(save);
    }

    @Override
    public List<CityDto> addCityList(List<CityDto> dtos) {
        List<City> cities= dtos.stream().map(c->mapToEntity(c)).collect(Collectors.toList());
        List<City> cities1 = cityRepository.saveAll(cities);
        return cities1.stream().map(c->mapToDto(c)).collect(Collectors.toList());

    }

    @Override
    public void deleteCountry(long cId) {
        Optional<City>optionalCity=cityRepository.findById(cId);
        if(optionalCity.isPresent()) {
            cityRepository.deleteById(cId);
        }
        else{
            throw new RuntimeException("city doesn't exist");
        }
    }

    @Override
    public CityDto updateCountry(CityDto dto, long cId) {
        Optional<City> opCity = cityRepository.findById(cId);
        if(opCity.isPresent()){
            City city = mapToEntity(dto);
            city.setId(cId);
            City save = cityRepository.save(city);
            return mapToDto(save);
        }
        else{
            throw new RuntimeException("city doesn't exist");
        }
    }

    @Override
    public List<CityDto> getAllCity() {
        List<City>cities=cityRepository.findAll();
        return cities.stream().map(c->mapToDto(c)).collect(Collectors.toList());
    }

    CityDto mapToDto(City city){
        return modelMapper.map(city,CityDto.class);
    }

    City mapToEntity(CityDto dto){
        return modelMapper.map(dto,City.class);
    }


}
