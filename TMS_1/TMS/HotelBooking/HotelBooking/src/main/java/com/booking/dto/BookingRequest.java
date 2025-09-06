package com.booking.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest {
    private Long guestId;
    private Long hotelId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfGuests;
}

