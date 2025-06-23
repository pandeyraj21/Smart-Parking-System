package com.Raj.Smart.Parking.System.controller;


import com.Raj.Smart.Parking.System.dto.CheckInRequest;
import com.Raj.Smart.Parking.System.dto.CheckOutResponse;
import com.Raj.Smart.Parking.System.dto.SpotDTO;
import com.Raj.Smart.Parking.System.entity.ParkingSpot;
import com.Raj.Smart.Parking.System.entity.ParkingTransaction;
import com.Raj.Smart.Parking.System.service.ParkingService;
import com.Raj.Smart.Parking.System.service.SpotAllocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parking")
public class ParkingController {


    @Autowired
    private ParkingService parkingService;

    @Autowired
    private SpotAllocationService spotAllocationService;

    @PostMapping("/checkin")
    public ParkingTransaction checkIn(@RequestBody @Valid CheckInRequest request) {
        return parkingService.checkIn(request.getLicensePlate(), request.getVehicleType());
    }

    @PostMapping("/checkout/{licensePlate}")
    public CheckOutResponse checkOut(@PathVariable String licensePlate) {
        ParkingTransaction tx = parkingService.checkOut(licensePlate);

        return CheckOutResponse.builder()
                .licensePlate(tx.getVehicle().getLicensePlate())
                .spotNumber(tx.getParkingSpot().getSpotNumber())
                .checkInTime(tx.getCheckInTime())
                .checkOutTime(tx.getCheckOutTime())
                .totalFee(tx.getFee())
                .build();
    }


    @GetMapping("/available-spots")
    public List<SpotDTO> getAvailableSpots() {
        List<ParkingSpot> spots = spotAllocationService.getAllAvailableSpots();

        return spots.stream()
                .map(spot -> new SpotDTO(
                        spot.getSpotNumber(),
                        spot.getFloorNumber(),
                        spot.getSpotSize()
                ))
                .collect(Collectors.toList());
    }

}
