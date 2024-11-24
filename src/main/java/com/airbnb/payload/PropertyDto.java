package com.airbnb.payload;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDto {
    private Long id;

    private String name;

    private int noOfBeds;

    private int noOfBedrooms;

    private int noOfBathrooms;

    private int noOfGuests;

    private Country country;

    private City city;
}
