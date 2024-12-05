package com.airbnb.service.impl;

import com.airbnb.entity.Property;
import com.airbnb.entity.Room;
import com.airbnb.exception.ResourceNotFound;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.RoomRepository;
import com.airbnb.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
   private RoomRepository roomRepository;
   private PropertyRepository propertyRepository;

    public RoomServiceImpl(RoomRepository roomRepository, PropertyRepository propertyRepository) {
        this.roomRepository = roomRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Room addRooom(Room room, long propertyId) {
    Property property =propertyRepository.findById(propertyId).orElseThrow(
            ()->new ResourceNotFound("property not found")
    );
    room.setProperty(property);
    return roomRepository.save(room);
    }

    @Override
    public List<Room> addRooms(List<Room> rooms, long propertyId) {
        Property property =propertyRepository.findById(propertyId).orElseThrow(
                ()->new ResourceNotFound("property not found")
        );

        List<Room> savedRooms=new ArrayList<>();
       for(Room room:rooms){
           room.setProperty(property);
           Room save = roomRepository.save(room);
           savedRooms.add(save);

       }
       return savedRooms;
    }
}
