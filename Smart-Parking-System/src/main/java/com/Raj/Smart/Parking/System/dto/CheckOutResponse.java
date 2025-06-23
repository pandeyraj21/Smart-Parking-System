package com.Raj.Smart.Parking.System.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class CheckOutResponse {

    private String licensePlate;
    private String spotNumber;
    private Instant checkInTime;
    private Instant checkOutTime;
    private double totalFee;

}
