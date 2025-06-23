package com.Raj.Smart.Parking.System.repository;

import com.Raj.Smart.Parking.System.entity.PricingPolicy;
import com.Raj.Smart.Parking.System.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PricingPolicyRepository extends JpaRepository<PricingPolicy, Long> {
    Optional<PricingPolicy> findByVehicleType(VehicleType vehicleType);
}
