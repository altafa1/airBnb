package com.airbnb.repository;

import com.airbnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("select p from Property p INNER JOIN City c on p.city=c.id where c.name=:cityName")
    List<Property>searchProperty(@Param("cityName") String cityName);



    @Query("SELECT p FROM Property p " +
            "JOIN p.city c " +
            "JOIN p.country co " +
            "WHERE c.name =:location OR co.name = :location")
    List<Property>searchPropertyOnCountryOrCity(@Param("location") String location);
}