package com.Raj.Smart.Parking.System.dto;

import com.Raj.Smart.Parking.System.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CheckInRequest {

    @NotBlank
    private String licensePlate;

    @NotNull
    private VehicleType vehicleType;
}
