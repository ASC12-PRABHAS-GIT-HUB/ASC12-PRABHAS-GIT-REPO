package com.hotel.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelResponse {
    private Long id;
    private String name;
    private String city;
    private double pricePerNight;
    private int rating;
    private String hotelCode;
}
