package com.airbnb.controller;

import com.airbnb.payload.CountryDto;
import com.airbnb.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/add")
    public ResponseEntity<CountryDto>addCountry(@RequestBody CountryDto countryDto){
        CountryDto dto=countryService.addCountry(countryDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/addList")
    public ResponseEntity<List<CountryDto>>addCountryList(@RequestBody List<CountryDto> dtos){
        List<CountryDto>savedCountry=countryService.addCountryList(dtos);
        return new ResponseEntity<>(savedCountry,HttpStatus.OK);
    }

    @DeleteMapping("/del/{cId}")
    public ResponseEntity<String> deleteCountry(@PathVariable long cId){
    countryService.deleteCountry(cId);
    return new ResponseEntity<>("deleted",HttpStatus.OK);
    }

    @PutMapping("/update/{cId}")
    public ResponseEntity<CountryDto>updateCountry(@RequestBody CountryDto dto,@PathVariable long cId){
        CountryDto updatedCountry=countryService.updateCountry(dto,cId);
        return new ResponseEntity<>(updatedCountry,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CountryDto>>getALlCountry(){
        List<CountryDto>countries=countryService.getAllCountry();
        return new ResponseEntity<>(countries,HttpStatus.OK);
    }
}
