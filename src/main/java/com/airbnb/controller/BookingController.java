package com.airbnb.controller;

import com.airbnb.entity.Room;
import com.airbnb.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {

    private RoomRepository roomRepository;

    public BookingController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(@RequestParam long propertyId,
                                           @RequestParam String type){
        Room room = roomRepository.findByPropertyAndType(propertyId, type);

        if(room!=null) {
            if (room.getCount() == 0) {
                return new ResponseEntity<>("Rooms not available", HttpStatus.UNAUTHORIZED);
            }
            else if (room.getCount() > 0) {
              int roomAvl= room.getCount();
              room.setCount(roomAvl-1);
              roomRepository.save(room);
                return new ResponseEntity<>("room booked", HttpStatus.CREATED);
            }
        }

        return null;
    }
}
