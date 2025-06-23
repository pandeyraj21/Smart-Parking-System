package com.Raj.Smart.Parking.System.exception;

public class TransactionNotFoundException extends RuntimeException {
  public TransactionNotFoundException(String licensePlate) {
    super("No active parking transaction found for vehicle: " + licensePlate);
  }
}
