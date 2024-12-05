package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Booking;

public interface BookingService {
    Booking createBooking(long propertyId, String roomType, Booking booking, AppUser appUser);
}
