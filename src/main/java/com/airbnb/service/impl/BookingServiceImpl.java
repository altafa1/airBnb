package com.airbnb.service.impl;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Booking;
import com.airbnb.entity.Property;
import com.airbnb.entity.Room;
import com.airbnb.exception.ResourceNotFound;
import com.airbnb.repository.BookingRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.RoomRepository;
import com.airbnb.service.BookingService;
import com.airbnb.service.SmsService;
import com.airbnb.service.WhatsAppService;
import com.airbnb.util.PDFService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private SmsService smsService;
    private WhatsAppService whatsAppService;
    private PropertyRepository propertyRepository;
    private RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private PDFService pdfService;

    public BookingServiceImpl(SmsService smsService, WhatsAppService whatsAppService, PropertyRepository propertyRepository, RoomRepository roomRepository, BookingRepository bookingRepository, PDFService pdfService) {
        this.smsService = smsService;
        this.whatsAppService = whatsAppService;
        this.propertyRepository = propertyRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.pdfService = pdfService;
    }


    @Override
    public Booking createBooking(long propertyId, String roomType, Booking booking, AppUser appUser) {

        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new ResourceNotFound("property not found")
        );
        List<LocalDate> datesBetween = getDatesBetween(booking.getCheckInDate(), booking.getCheckOutDate());
        List<Room> roomList=new ArrayList<>();
        for (LocalDate date:datesBetween){
            Room room=roomRepository.findByPropertyAndTypeAndDate(propertyId,roomType,date);
            if(room==null || room.getRoomCount()<=0){
                throw new ResourceNotFound("rooms not available");
            }
            roomList.add(room);

        }
        double totalPrice=0;
        for(Room room:roomList){
            totalPrice=totalPrice+room.getPrice();
        }

        booking.setAppUser(appUser);
        booking.setProperty(property);
        booking.setTotalPrice(totalPrice);
        booking.setTypeOfRoom(roomType);
        Booking savedBooking=bookingRepository.save(booking);
        if (savedBooking != null) {
            for (Room room:roomList){
                int availRoom=room.getRoomCount();
                room.setRoomCount(availRoom-1);
                roomRepository.save(room);
            }
            pdfService.generatePdf(savedBooking);
            smsService.sendSms(savedBooking.getMobile(),"sts download hua ?");
            whatsAppService.sendWhatsAppMessage(savedBooking.getMobile(),"booking done");
            return savedBooking;

        }
            return null;
    }

    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate){
        List<LocalDate> dates =new ArrayList<>();
        LocalDate currentDate=startDate;

        while(!currentDate.isAfter(endDate)){
            dates.add(currentDate);
            currentDate=currentDate.plusDays(1);
        }
        return  dates;
    }
}
