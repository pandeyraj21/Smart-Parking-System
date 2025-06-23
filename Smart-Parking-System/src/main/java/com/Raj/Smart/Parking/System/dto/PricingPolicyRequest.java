package com.Raj.Smart.Parking.System.dto;


import com.Raj.Smart.Parking.System.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PricingPolicyRequest {
    private VehicleType vehicleType;
    private double hourlyRate;
}
