package com.Raj.Smart.Parking.System.entity;

import com.Raj.Smart.Parking.System.enums.SpotSize;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int floorNumber;

    private String spotNumber;

    @Enumerated(EnumType.STRING)
    private SpotSize spotSize;

    private boolean isOccupied;
}
