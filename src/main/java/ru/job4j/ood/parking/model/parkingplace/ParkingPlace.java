package ru.job4j.ood.parking.model.parkingplace;

import ru.job4j.ood.parking.model.vehicle.Vehicle;

public interface ParkingPlace {

    void park(Vehicle vehicle);
    void unpark(Vehicle vehicle);
    int getPlaceNumber();
    boolean isBusy();
}