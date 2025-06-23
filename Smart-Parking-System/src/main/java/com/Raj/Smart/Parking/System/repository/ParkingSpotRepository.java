package com.Raj.Smart.Parking.System.repository;

import com.Raj.Smart.Parking.System.entity.ParkingSpot;
import com.Raj.Smart.Parking.System.enums.SpotSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    List<ParkingSpot> findByIsOccupiedFalseAndSpotSizeInOrderByFloorNumberAscSpotNumberAsc(List<SpotSize> sizes);

    List<ParkingSpot> findByIsOccupiedFalseOrderByFloorNumberAscSpotNumberAsc();
}
