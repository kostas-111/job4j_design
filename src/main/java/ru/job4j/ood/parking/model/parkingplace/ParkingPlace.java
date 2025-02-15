package ru.job4j.ood.parking.model.parkingplace;

import ru.job4j.ood.parking.model.vehicle.Vehicle;

import java.util.List;

public interface ParkingPlace {

    void park(Vehicle vehicle, List<ParkingPlace> parkingPlaceList);
    void unpark(Vehicle vehicle, List<ParkingPlace> parkingPlaceList);
    int getPlaceNumber();
    boolean isBusy();
    void setBusy(boolean busy);
    boolean isCarPlace();
}