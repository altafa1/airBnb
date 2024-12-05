package com.airbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "no_of_guests", nullable = false)
    private int noOfGuests;

    @Column(name = "guest_name", nullable = false, length = 50)
    private String guestName;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "email", nullable = false, length = 128)
    private String email;

    @Column(name = "type_of_room", nullable = false)
    private String typeOfRoom;

    @Column(name = "total_nights", nullable = false)
    private int totalNights;

    @ManyToOne
    @JoinColumn(name="property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name="app_user_id")
    private AppUser appUser;

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

    @Column(name = "total_price")
    private Double totalPrice;


}