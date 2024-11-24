package com.airbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "room_type", nullable = false)
    private String roomType;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "count", nullable = false)
    private int count;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

}