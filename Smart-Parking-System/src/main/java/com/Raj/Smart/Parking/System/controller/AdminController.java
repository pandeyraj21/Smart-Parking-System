package com.Raj.Smart.Parking.System.controller;


import com.Raj.Smart.Parking.System.dto.ParkingSpotRequest;
import com.Raj.Smart.Parking.System.dto.PricingPolicyRequest;
import com.Raj.Smart.Parking.System.entity.ParkingSpot;
import com.Raj.Smart.Parking.System.entity.PricingPolicy;
import com.Raj.Smart.Parking.System.repository.ParkingSpotRepository;
import com.Raj.Smart.Parking.System.repository.PricingPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ParkingSpotRepository _parkingSpotRepository;

    @Autowired
    private PricingPolicyRepository _pricingPolicyRepository;

    // --- Add new parking spot ---
    @PostMapping("/spots")
    public ParkingSpot addParkingSpot(@RequestBody ParkingSpotRequest spotRequest) {
        ParkingSpot spot = new ParkingSpot();
        spot.setFloorNumber(spotRequest.getFloor());
        spot.setSpotNumber(spotRequest.getSpotNumber());
        spot.setSpotSize(spotRequest.getVehicleType());
        spot.setOccupied(false); // default value

        return _parkingSpotRepository.save(spot);
    }

    // --- Get all parking spots ---
    @GetMapping("/spots")
    public List<ParkingSpot> getAllParkingSpots() {
        return _parkingSpotRepository.findAll();
    }

    // --- Add or update pricing policy ---
    @PostMapping("/pricing")
    public PricingPolicy addOrUpdatePricing(@RequestBody PricingPolicyRequest request) {
        PricingPolicy policy = new PricingPolicy();
        policy.setVehicleType(request.getVehicleType());
        policy.setHourlyRate(request.getHourlyRate());

        return _pricingPolicyRepository.save(policy);
    }

    // --- Get all pricing policies ---
    @GetMapping("/pricing")
    public List<PricingPolicy> getAllPricingPolicies() {
        return _pricingPolicyRepository.findAll();
    }

}
