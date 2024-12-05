package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Booking;
import com.airbnb.repository.BookingRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.RoomRepository;
import com.airbnb.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {
    private BookingService bookingService;
    private RoomRepository roomRepository;
    private PropertyRepository propertyRepository;
    private final BookingRepository bookingRepository;

    public BookingController(BookingService bookingService, RoomRepository roomRepository, PropertyRepository propertyRepository,
                             BookingRepository bookingRepository) {
        this.bookingService = bookingService;
        this.roomRepository = roomRepository;
        this.propertyRepository = propertyRepository;
        this.bookingRepository = bookingRepository;
    }

    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(@RequestParam long propertyId,
                                           @RequestParam String roomType,
                                           @RequestBody Booking booking,
                                           @AuthenticationPrincipal AppUser appUser) {
        Booking savedBooking = bookingService.createBooking(propertyId, roomType, booking, appUser);
            if(savedBooking==null){
                throw new RuntimeException("Failed to save the booking. Please try again.");
            }

            return new ResponseEntity<>(savedBooking,HttpStatus.CREATED);
    }
}