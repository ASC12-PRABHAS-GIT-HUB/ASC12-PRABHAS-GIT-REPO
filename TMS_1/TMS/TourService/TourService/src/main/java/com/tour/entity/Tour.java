package com.tour.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tours")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tourCode;   // Example: TR-1234

    @Column(nullable = false)
    private String name;

    private String description;
    private int durationDays;
    private double price;

    private boolean deleted = false;
}

