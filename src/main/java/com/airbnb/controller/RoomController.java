package com.airbnb.controller;

import com.airbnb.entity.Room;
import com.airbnb.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/addrooms")
    public ResponseEntity<Room>addRoom(@RequestBody Room room,
                                       @RequestParam long propertyId){
        Room savedRoom=roomService.addRooom(room,propertyId);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);

    }

    @PostMapping("/addListRooms")
    public ResponseEntity<List<Room>> addList(@RequestBody List<Room> rooms,
                                              @RequestParam long propertyId){
        List<Room> savedRoom=roomService.addRooms(rooms,propertyId);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }


}
