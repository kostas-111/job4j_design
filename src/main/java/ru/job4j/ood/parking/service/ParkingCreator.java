package ru.job4j.ood.parking.service;

import ru.job4j.ood.parking.model.parkingplace.CarPlace;
import ru.job4j.ood.parking.model.parkingplace.ParkingPlace;
import ru.job4j.ood.parking.model.parkingplace.TruckPlace;

import java.util.ArrayList;
import java.util.List;

public class ParkingCreator {

    public List<ParkingPlace> create(int carPlaceAmount, int truckPlaceAmount) {
        List<ParkingPlace> places = new ArrayList<>();
        for (int i = 1; i <= carPlaceAmount; i++) {
            places.add(new CarPlace(i, true));
        }
        for (int i = 1; i <= truckPlaceAmount; i++) {
            places.add(new TruckPlace(i, true));
        }
        return places;
    }
}