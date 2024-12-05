package com.airbnb.service;

import com.airbnb.entity.Room;

import java.util.List;

public interface RoomService {
    Room addRooom(Room room, long propertyId);

    List<Room> addRooms(List<Room> rooms, long propertyId);
}
