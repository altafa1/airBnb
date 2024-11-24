package com.airbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "no_of_beds", nullable = false)
    private int noOfBeds;

    @Column(name = "no_of_bedrooms", nullable = false)
    private int noOfBedrooms;

    @Column(name = "no_of_bathrooms", nullable = false)
    private int noOfBathrooms;

    @Column(name = "no_of_guests", nullable = false)
    private int noOfGuests;

    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

}