package com.airbnb.controller;

import com.airbnb.entity.City;
import com.airbnb.payload.CityDto;
import com.airbnb.payload.CountryDto;
import com.airbnb.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.*;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/add")
    public ResponseEntity<CityDto>addCity(@RequestBody CityDto dto){
        CityDto dto1=cityService.addCity(dto);
        return new ResponseEntity<>(dto1, HttpStatus.CREATED);
    }
    @PostMapping("/addlist")
    public ResponseEntity<List<CityDto>> addCityList(@RequestBody List<CityDto> dtos){
        List<CityDto> dtos1=cityService.addCityList(dtos);
        return new ResponseEntity<>(dtos1,HttpStatus.CREATED);
    }

    @DeleteMapping("/del/{cId}")
    public ResponseEntity<String> deleteCity(@PathVariable long cId){
        cityService.deleteCountry(cId);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }

    @PutMapping("/update/{cId}")
    public ResponseEntity<CityDto>updateCity(@RequestBody CityDto dto,@PathVariable long cId){
        CityDto updatedCity=cityService.updateCountry(dto,cId);
        return new ResponseEntity<>(updatedCity,HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CityDto>> getAllCity(){
        List<CityDto>cities=cityService.getAllCity();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

}
