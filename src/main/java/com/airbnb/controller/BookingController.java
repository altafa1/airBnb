package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Booking;
import com.airbnb.entity.Property;
import com.airbnb.entity.Room;
import com.airbnb.repository.BookingRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {

    private RoomRepository roomRepository;
    private PropertyRepository propertyRepository;
    private final BookingRepository bookingRepository;

    public BookingController(RoomRepository roomRepository, PropertyRepository propertyRepository,
                             BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.propertyRepository = propertyRepository;
        this.bookingRepository = bookingRepository;
    }

    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(@RequestParam long propertyId,
                                           @RequestParam String roomType,
                                           @RequestBody Booking booking,
                                           @AuthenticationPrincipal AppUser appUser){
        Property property=propertyRepository.findById(propertyId).get();
        List<LocalDate> datesBetween = getDatesBetween(booking.getCheckInDate(), booking.getCheckOutDate());
        List<Room> roomList=new ArrayList<>();

       for(LocalDate date:datesBetween){
           Room room = roomRepository.findByPropertyAndTypeAndDate(propertyId, roomType, date);

           if(room==null){
               return new ResponseEntity<>("room not available",HttpStatus.EXPECTATION_FAILED);
           }
           roomList.add(room);

       }
       double totalPrice=0;
       for(Room room:roomList){
           totalPrice = room.getPrice() + totalPrice;
       }

       booking.setTypeOfRoom(roomType);
       booking.setAppUser(appUser);
       booking.setProperty(property);
       booking.setTotalPrice(totalPrice);
        Booking savedBooking = bookingRepository.save(booking);
        if(savedBooking!=null){
            for (Room room:roomList){
                int availableRooms = room.getRoomCount();
                room.setRoomCount(availableRooms-1);
                roomRepository.save(room);
            }

            return new ResponseEntity<>(savedBooking,HttpStatus.CREATED);
        }
        return new ResponseEntity<>("roomnot booked",HttpStatus.BAD_GATEWAY);
    }


    public static List<LocalDate> getDatesBetween(LocalDate startDate,LocalDate endDate){
        List<LocalDate> dates =new ArrayList<>();
        LocalDate currentDate=startDate;

        while(!currentDate.isAfter(endDate)){
            dates.add(currentDate);
            currentDate=currentDate.plusDays(1);
        }
        return  dates;
    }
}

//                else if (room.getRoomCount() > 0) {
//                    int roomAvl= room.getRoomCount();
//                    room.setRoomCount(roomAvl-1);
//                    roomRepository.save(room);
//                    return new ResponseEntity<>("room booked", HttpStatus.CREATED);
//                }