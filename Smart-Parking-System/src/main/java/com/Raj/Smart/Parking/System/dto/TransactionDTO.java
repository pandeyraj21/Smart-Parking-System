package com.Raj.Smart.Parking.System.dto;

import com.Raj.Smart.Parking.System.enums.VehicleType;
import lombok.Data;

import java.time.Instant;

@Data
public class TransactionDTO {
    private String licensePlate;
    private VehicleType vehicleType;
    private String spotNumber;
    private Instant checkInTime;
    private Instant checkOutTime;
    private Double fee;
}
