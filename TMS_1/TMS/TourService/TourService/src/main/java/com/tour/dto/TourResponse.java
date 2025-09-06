package com.tour.dto;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourResponse {
    private Long id;
    private String tourCode;
    private String name;
    private String description;
    private int durationDays;
    private double price;
}

