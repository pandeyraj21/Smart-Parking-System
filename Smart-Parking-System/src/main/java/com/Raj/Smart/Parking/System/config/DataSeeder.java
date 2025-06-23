package com.Raj.Smart.Parking.System.config;

import com.Raj.Smart.Parking.System.entity.ParkingSpot;
import com.Raj.Smart.Parking.System.entity.PricingPolicy;
import com.Raj.Smart.Parking.System.enums.SpotSize;
import com.Raj.Smart.Parking.System.enums.VehicleType;
import com.Raj.Smart.Parking.System.repository.ParkingSpotRepository;
import com.Raj.Smart.Parking.System.repository.PricingPolicyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ParkingSpotRepository parkingSpotRepository;
    private final PricingPolicyRepository pricingPolicyRepository;

    public DataSeeder(ParkingSpotRepository parkingSpotRepository, PricingPolicyRepository pricingPolicyRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.pricingPolicyRepository = pricingPolicyRepository;
    }

    @Override
    public void run(String... args) {
        seedPricingPolicies();
        seedParkingSpots();
    }

    private void seedPricingPolicies() {
        if (pricingPolicyRepository.count() == 0) {
            List<PricingPolicy> policies = Arrays.asList(
                    new PricingPolicy(null, VehicleType.BIKE, 10.0),
                    new PricingPolicy(null, VehicleType.CAR, 20.0),
                    new PricingPolicy(null, VehicleType.BUS, 50.0)
            );
            pricingPolicyRepository.saveAll(policies);
            System.out.println("✅ Seeded pricing policies.");
        }
    }

    private void seedParkingSpots() {
        if (parkingSpotRepository.count() == 0) {
            List<ParkingSpot> spots = Arrays.asList(
                    new ParkingSpot(null, 0, "A1", SpotSize.SMALL, false),
                    new ParkingSpot(null, 0, "A2", SpotSize.SMALL, false),
                    new ParkingSpot(null, 1, "B1", SpotSize.MEDIUM, false),
                    new ParkingSpot(null, 1, "B2", SpotSize.MEDIUM, false),
                    new ParkingSpot(null, 2, "C1", SpotSize.LARGE, false)
            );
            parkingSpotRepository.saveAll(spots);
            System.out.println("✅ Seeded parking spots.");
        }
    }


}
