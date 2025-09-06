package com.facilities.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "facilities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;  // e.g., "WiFi", "Parking"

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean available = true;

    @Column(nullable = false)
    private Long hotelId; // FK to hotel
}

