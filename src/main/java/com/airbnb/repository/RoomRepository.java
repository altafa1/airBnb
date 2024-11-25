package com.airbnb.repository;

import com.airbnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r where r.property.id=:propertyId And r.roomType=:type And r.date=:date")
    Room findByPropertyAndTypeAndDate(@Param("propertyId") long propertyId,
                               @Param("type")String type,
                               @Param("date") LocalDate date);
}