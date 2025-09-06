package com.hotel.dto;



import lombok.Data;

@Data
public class HotelRequest {
    private String name;
    private String city;
    private double pricePerNight;
    private int rating;
}
