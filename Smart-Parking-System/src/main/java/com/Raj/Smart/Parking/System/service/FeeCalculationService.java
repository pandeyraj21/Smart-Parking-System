package com.Raj.Smart.Parking.System.service;


import com.Raj.Smart.Parking.System.entity.ParkingTransaction;
import com.Raj.Smart.Parking.System.enums.VehicleType;
import com.Raj.Smart.Parking.System.repository.PricingPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class FeeCalculationService {

    @Autowired
    private PricingPolicyRepository _pricingPolicyRepository;

    public double calculateFee(ParkingTransaction transaction) {
        Instant checkIn = transaction.getCheckInTime();
        Instant checkOut = Instant.now();

        long minutes = Duration.between(checkIn, checkOut).toMinutes();
        long hours = (long) Math.ceil(minutes / 60.0);

        VehicleType type = transaction.getVehicle().getVehicleType();
        double rate = _pricingPolicyRepository.findByVehicleType(type)
                .orElseThrow(() -> new RuntimeException("Pricing not defined"))
                .getHourlyRate();

        return rate * hours;
    }
}
