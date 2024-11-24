package com.airbnb.service.impl;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import com.airbnb.payload.CountryDto;
import com.airbnb.repository.CountryRepository;
import com.airbnb.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;

    private ModelMapper modelMapper;

    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CountryDto addCountry(CountryDto countryDto) {
        Country country=mapToEntity(countryDto);
        Country savedCountry=countryRepository.save(country);
        return mapToDto(savedCountry);
    }

    @Override
    public void deleteCountry(long cId) {
        Optional<Country>optionalCountry=countryRepository.findById((cId));
        if(optionalCountry.isPresent()){

                countryRepository.deleteById(cId);
            }
            else{
                throw new RuntimeException("country doesn't exist");
            }
    }

    @Override
    public CountryDto updateCountry(CountryDto dto, long cId) {

        Optional<Country> opCountry=countryRepository.findById(cId);
        if(opCountry.isPresent()){

           Country country=mapToEntity(dto);
           country.setId(cId);
            Country save = countryRepository.save(country);
        return mapToDto(save);
        }
        else{
            throw new RuntimeException("country doesn't exist");
        }
    }

    @Override
    public List<CountryDto> getAllCountry() {
        List<Country> countries=countryRepository.findAll();
        return countries.stream().map(c->mapToDto(c)).collect(Collectors.toList());
    }

    @Override
    public List<CountryDto> addCountryList(List<CountryDto> dtos) {
        List<Country> countryList=dtos.stream().map(dto->mapToEntity(dto)).collect(Collectors.toList());
        List<Country> countryList1 = countryRepository.saveAll(countryList);
        return countryList1.stream().map(c->mapToDto(c)).collect(Collectors.toList());
    }

    CountryDto mapToDto(Country country){
        return modelMapper.map(country,CountryDto.class);
    }

    Country mapToEntity (CountryDto dto){
        return modelMapper.map(dto,Country.class);
    }
}
