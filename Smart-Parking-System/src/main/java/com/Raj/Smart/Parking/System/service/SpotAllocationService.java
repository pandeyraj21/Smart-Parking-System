package com.Raj.Smart.Parking.System.service;

import com.Raj.Smart.Parking.System.entity.ParkingSpot;
import com.Raj.Smart.Parking.System.enums.SpotSize;
import com.Raj.Smart.Parking.System.enums.VehicleType;
import com.Raj.Smart.Parking.System.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpotAllocationService {


    @Autowired
    private ParkingSpotRepository _parkingSpotRepository;

    private static final Map<VehicleType, List<SpotSize>> compatibilityMap = new HashMap<>();

    static {
        compatibilityMap.put(VehicleType.BIKE, Arrays.asList(SpotSize.SMALL, SpotSize.MEDIUM, SpotSize.LARGE));
        compatibilityMap.put(VehicleType.CAR, Arrays.asList(SpotSize.MEDIUM, SpotSize.LARGE));
        compatibilityMap.put(VehicleType.BUS, Collections.singletonList(SpotSize.LARGE));
    }

    public Optional<ParkingSpot> allocateSpot(VehicleType type) {
        List<SpotSize> compatibleSizes = compatibilityMap.get(type);
        return _parkingSpotRepository
                .findByIsOccupiedFalseAndSpotSizeInOrderByFloorNumberAscSpotNumberAsc(compatibleSizes)
                .stream().findFirst();
    }

    public List<ParkingSpot> getAllAvailableSpots() {
        return _parkingSpotRepository.findByIsOccupiedFalseOrderByFloorNumberAscSpotNumberAsc();
    }

}
