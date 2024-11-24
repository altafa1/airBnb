package com.airbnb.controller;

import com.airbnb.entity.Property;
import com.airbnb.payload.PropertyDto;
import com.airbnb.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/addproperty/{countryId}/{cityId}")
    public ResponseEntity<PropertyDto> addProperty(@RequestBody PropertyDto propertyDto,
                                                   @PathVariable long countryId,
                                                   @PathVariable long cityId) {
        PropertyDto savedProperty = propertyService.addProperty(propertyDto,countryId,cityId);
        return ResponseEntity.ok(savedProperty);
    }
    @GetMapping("/get/{pId}")
    public ResponseEntity<PropertyDto> getProperty(@PathVariable long pId){
        PropertyDto propertyDto=propertyService.getProperty(pId);
        return ResponseEntity.ok(propertyDto);
    }

    @DeleteMapping("/del/{pId}")
    public ResponseEntity<String>deleteProperty(@PathVariable long pId){
        propertyService.deleteProperty(pId);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
    @PutMapping("/update/{pId}/{countryId}/{cityId}")
    public ResponseEntity<PropertyDto>updateProperty(@PathVariable long pId,
                                                     @RequestBody PropertyDto dto,
                                                     @PathVariable long countryId,
                                                     @PathVariable long cityId){
        PropertyDto updatedProperty=  propertyService.updateProperty(pId,dto,countryId,cityId);
        return new ResponseEntity<>(updatedProperty, HttpStatus.OK);
    }

//    @GetMapping("/propertyresult")
//    public ResponseEntity<List<Property>>searchProperty(@RequestParam String cityName){
//        List<Property> properties=propertyService.searchProperty(cityName);
//        return new ResponseEntity<>(properties,HttpStatus.OK);
//    }
//
    @GetMapping("/propertyresults")
    public ResponseEntity<List<Property>>searchProperty(@RequestParam String location){
        List<Property> properties=propertyService.searchProperty(location);
        return new ResponseEntity<>(properties,HttpStatus.OK);
    }


}
