package ru.job4j.ood.parking.service;

import ru.job4j.ood.parking.model.parkingplace.CarPlace;
import ru.job4j.ood.parking.model.parkingplace.ParkingPlace;
import ru.job4j.ood.parking.model.parkingplace.TruckPlace;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingCreator {

    List<ParkingPlace> parkingPlaceList = new ArrayList<>();

    public List<ParkingPlace> create(int carPlaceAmount, int truckPlaceAmount) {
        if ((carPlaceAmount < 0 || truckPlaceAmount < 0) || (carPlaceAmount == 0 && truckPlaceAmount == 0)) {
            throw new IllegalArgumentException(
                    "Количество хотя бы одного типа парковочных мест должно быть больше 0  и не может быть отрицательным"
            );
        }
        for (int i = 1; i <= carPlaceAmount; i++) {
            parkingPlaceList.add(new CarPlace(i, false));
        }
        for (int i = 1; i <= truckPlaceAmount; i++) {
            parkingPlaceList.add(new TruckPlace(i, false));
        }
        return parkingPlaceList;
    }

    /*
    Метод для поиска первого свободного места для автомобиля
     */
    public Optional<ParkingPlace> findFreeCarPlace() {
        return parkingPlaceList.stream()
                .filter(place -> place.isCarPlace() && !place.isBusy())
                .findFirst();
    }

    /*
     Метод для поиска первого свободного места для грузовика
     */
    public Optional<ParkingPlace> findFreeTruckPlace() {
        return parkingPlaceList.stream()
                .filter(place -> !place.isCarPlace() && !place.isBusy())
                .findFirst();
    }

    /*
    Метод для поиска первого занятого места для автомобиля
    */
    public Optional<ParkingPlace> findBusyCarPlace() {
        return parkingPlaceList.stream()
                .filter(place -> place.isCarPlace() && place.isBusy())
                .findFirst();
    }

    /*
     Метод для поиска первого занятого места для грузовика
     */
    public Optional<ParkingPlace> findBusyTruckPlace() {
        return parkingPlaceList.stream()
                .filter(place -> !place.isCarPlace() && place.isBusy())
                .findFirst();
    }

    public List<ParkingPlace> getParkingPlaceList() {
        return parkingPlaceList;
    }
}