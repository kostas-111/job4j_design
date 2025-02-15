package ru.job4j.ood.parking.model.parkingplace;

import ru.job4j.ood.parking.model.vehicle.Vehicle;
import java.util.List;
import java.util.Objects;

public class TruckPlace implements ParkingPlace {

    private final int placeNumber;
    private boolean isBusy;
    private final boolean isCarPlace = false;

    public TruckPlace(int placeNumber, boolean isBusy) {
        this.placeNumber = placeNumber;
        this.isBusy = isBusy;
    }

    @Override
    public void park(Vehicle vehicle, List<ParkingPlace> parkingPlaceList) {
        if (vehicle.getSize() == 1) {
            throw new IllegalStateException("Парковать легковой автомобиль на место для грузовиков запрещено.");
        }
        if (vehicle.getSize() > 1) {
            for (ParkingPlace place : parkingPlaceList) {
                if (!place.isBusy()) {
                    isBusy = true;
                }
            }
        }
    }

    @Override
    public void unpark(Vehicle vehicle, List<ParkingPlace> parkingPlaceList) {
        if (vehicle.getSize() > 1) {
            for (ParkingPlace place : parkingPlaceList) {
                if (place.isBusy()) {
                    isBusy = false;
                }
            }
        }
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
    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public boolean isCarPlace() {
        return isCarPlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TruckPlace that = (TruckPlace) o;
        return placeNumber == that.placeNumber && isBusy == that.isBusy && isCarPlace == that.isCarPlace;
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeNumber, isBusy, isCarPlace);
    }

    @Override
    public String toString() {
        return "TruckPlace{"
                + "placeNumber=" + placeNumber
                + ", isBusy=" + isBusy
                + ", isCarPlace=" + isCarPlace
                + '}';
    }
}