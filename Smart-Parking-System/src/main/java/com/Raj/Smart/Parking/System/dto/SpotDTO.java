package com.Raj.Smart.Parking.System.dto;

import com.Raj.Smart.Parking.System.enums.SpotSize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpotDTO {
    private String spotNumber;
    private int floorNumber;
    private SpotSize spotSize;

}
