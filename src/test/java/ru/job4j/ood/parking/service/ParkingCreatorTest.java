package ru.job4j.ood.parking.service;


import org.junit.jupiter.api.Test;
import ru.job4j.ood.parking.model.parkingplace.CarPlace;
import ru.job4j.ood.parking.model.parkingplace.ParkingPlace;
import ru.job4j.ood.parking.model.parkingplace.TruckPlace;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ParkingCreatorTest {

    @Test
    void whenCreateCarPlaces() {
        int carPlacesCount = 2;
        ParkingCreator creator = new ParkingCreator();
        List<ParkingPlace> expectedCarPlaces = List.of(
                new CarPlace(1, false),
                new CarPlace(2, false)
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
                new CarPlace(1, false),
                new CarPlace(2, false),
                new TruckPlace(1, false),
                new TruckPlace(2, false)
        );
        List<ParkingPlace> carPlaces = creator.create(carPlacesCount, truckPlacesCount);
        assertThat(carPlaces.size()).isEqualTo(expectedCarPlaces.size());
        assertTrue(carPlaces.contains(new TruckPlace(1, false)));
    }

    @Test
    void whenTryToCreateParkingWithNullOrMinusSize() {
        ParkingCreator creatorNull = new ParkingCreator();
        assertThrows(IllegalArgumentException.class, () -> creatorNull.create(0, 0));
        ParkingCreator creatorOneNull = new ParkingCreator();
        assertThrows(IllegalArgumentException.class, () -> creatorOneNull.create(0, -5));
        ParkingCreator creatorMinus = new ParkingCreator();
        assertThrows(IllegalArgumentException.class, () -> creatorMinus.create(2, -1));
    }
}