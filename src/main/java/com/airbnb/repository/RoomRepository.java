package com.airbnb.repository;

import com.airbnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r where r.property.id=:propertyId And r.roomType=:type")
    Room findByPropertyAndType(@Param("propertyId") long propertyId,
                               @Param("type")String type);
}