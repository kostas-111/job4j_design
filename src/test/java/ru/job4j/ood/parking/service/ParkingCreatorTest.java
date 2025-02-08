package ru.job4j.ood.parking.service;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.parking.model.parkingplace.CarPlace;
import ru.job4j.ood.parking.model.parkingplace.ParkingPlace;
import ru.job4j.ood.parking.model.parkingplace.TruckPlace;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingCreatorTest {

    @Test
    void whenCreateCarPlaces() {
        int carPlacesCount = 2;
        ParkingCreator creator = new ParkingCreator();
        List<ParkingPlace> expectedCarPlaces = List.of(
                new CarPlace(1, true),
                new CarPlace(2, true)
        );
        List<ParkingPlace> carPlaces = creator.create(carPlacesCount, 0);
        assertThat(carPlaces.size()).isEqualTo(expectedCarPlaces.size());
        assertTrue(carPlaces.stream().allMatch(p -> p instanceof CarPlace));
    }

    @Test
    void whenCreateCarPlacesAndTruckPlaces() {
        int carPlacesCount = 2;
        int truckPlacesCount = 2;
        ParkingCreator creator = new ParkingCreator();

        List<ParkingPlace> expectedCarPlaces = List.of(
                new CarPlace(1, true),
                new CarPlace(2, true),
                new TruckPlace(1, true),
                new TruckPlace(2, true)
        );
        List<ParkingPlace> carPlaces = creator.create(carPlacesCount, truckPlacesCount);
        assertThat(carPlaces.size()).isEqualTo(expectedCarPlaces.size());
        assertTrue(carPlaces.contains(new TruckPlace(1, true)));
    }
}