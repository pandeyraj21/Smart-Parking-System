package com.Raj.Smart.Parking.System.repository;

import com.Raj.Smart.Parking.System.entity.ParkingTransaction;
import com.Raj.Smart.Parking.System.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingTransactionRepository extends JpaRepository<ParkingTransaction, Long> {
    Optional<ParkingTransaction> findTopByVehicleAndCheckOutTimeIsNullOrderByCheckInTimeDesc(Vehicle vehicle);
}
