package com.hotel.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hotels")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private double pricePerNight;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private boolean deleted = false;

    @Column(unique = true, nullable = false)
    private String hotelCode;
}


