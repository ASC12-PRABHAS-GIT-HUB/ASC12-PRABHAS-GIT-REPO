package com.facilities.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacilityResponse {
    private Long id;
    private String name;
    private String description;
    private boolean available;
    private Long hotelId;
}
