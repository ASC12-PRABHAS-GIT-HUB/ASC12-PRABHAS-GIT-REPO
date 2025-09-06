package com.facilities.dto;

import lombok.Data;

@Data
public class FacilityRequest {
    private String name;
    private String description;
    private boolean available;
    private Long hotelId;
}

