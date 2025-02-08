package ru.job4j.ood.parking.model.parkingplace;

import ru.job4j.ood.parking.model.vehicle.Vehicle;

import java.util.Objects;

public class CarPlace implements ParkingPlace {

    private final int placeNumber;
    private boolean isBusy;

    public CarPlace(int placeNumber, boolean isBusy) {
        this.placeNumber = placeNumber;
        this.isBusy = isBusy;
    }

    @Override
    public void park(Vehicle vehicle) {

    }

    @Override
    public void unpark(Vehicle vehicle) {

    }

    @Override
    public int getPlaceNumber() {
        return placeNumber;
    }

    @Override
    public boolean isBusy() {
        return isBusy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarPlace carPlace = (CarPlace) o;
        return placeNumber == carPlace.placeNumber && isBusy == carPlace.isBusy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeNumber, isBusy);
    }
}
