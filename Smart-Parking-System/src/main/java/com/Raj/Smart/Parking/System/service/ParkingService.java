package com.Raj.Smart.Parking.System.service;


import com.Raj.Smart.Parking.System.entity.ParkingSpot;
import com.Raj.Smart.Parking.System.entity.ParkingTransaction;
import com.Raj.Smart.Parking.System.entity.Vehicle;
import com.Raj.Smart.Parking.System.enums.VehicleType;
import com.Raj.Smart.Parking.System.exception.SpotUnavailableException;
import com.Raj.Smart.Parking.System.exception.TransactionNotFoundException;
import com.Raj.Smart.Parking.System.exception.VehicleNotFoundException;
import com.Raj.Smart.Parking.System.repository.ParkingSpotRepository;
import com.Raj.Smart.Parking.System.repository.ParkingTransactionRepository;
import com.Raj.Smart.Parking.System.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ParkingService {
    @Autowired
    private VehicleRepository _vehicleRepository;

    @Autowired
    private ParkingSpotRepository _parkingSpotRepository;

    @Autowired
    private ParkingTransactionRepository _parkingTransactionRepository;

    @Autowired
    private SpotAllocationService spotAllocationService;

    @Autowired
    private FeeCalculationService feeCalculationService;

    public ParkingTransaction checkIn(String licensePlate, VehicleType type) {
        Vehicle vehicle = _vehicleRepository.findByLicensePlate(licensePlate)
                .orElseGet(() -> _vehicleRepository.save(new Vehicle(null, licensePlate, type)));

        ParkingSpot spot = spotAllocationService.allocateSpot(type)
                .orElseThrow(() -> new SpotUnavailableException("No spot available for vehicle type: " + type));

        spot.setOccupied(true);
        _parkingSpotRepository.save(spot);

        ParkingTransaction tx = ParkingTransaction.builder()
                .vehicle(vehicle)
                .parkingSpot(spot)
                .checkInTime(Instant.now())
                .build();

        return _parkingTransactionRepository.save(tx);
    }

    public ParkingTransaction checkOut(String licensePlate) {
        Vehicle vehicle = _vehicleRepository.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new VehicleNotFoundException(licensePlate));

        ParkingTransaction tx = _parkingTransactionRepository
                .findTopByVehicleAndCheckOutTimeIsNullOrderByCheckInTimeDesc(vehicle)
                .orElseThrow(() -> new TransactionNotFoundException(licensePlate));

        tx.setCheckOutTime(Instant.now());
        tx.setFee(feeCalculationService.calculateFee(tx));
        tx.getParkingSpot().setOccupied(false);

        _parkingSpotRepository.save(tx.getParkingSpot());
        return _parkingTransactionRepository.save(tx);
    }


}
