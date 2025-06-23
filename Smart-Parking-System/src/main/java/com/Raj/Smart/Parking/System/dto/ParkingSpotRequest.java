package com.Raj.Smart.Parking.System.dto;

import com.Raj.Smart.Parking.System.enums.SpotSize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ParkingSpotRequest {
    private int floor;
    private String spotNumber;
    private SpotSize vehicleType;
}
