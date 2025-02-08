package ru.job4j.ood.parking.model.parkingplace;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.parking.model.vehicle.Car;
import ru.job4j.ood.parking.model.vehicle.Truck;
import ru.job4j.ood.parking.model.vehicle.Vehicle;
import ru.job4j.ood.parking.service.ParkingCreator;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled
class CarPlaceTest {

        @Test
        void whenParkCar() {
            ParkingPlace carPlace = new CarPlace(1, false);
            Vehicle car = new Car("NIVA", "1111AA");
            carPlace.park(car);
            assertThat(carPlace.isBusy()).isTrue();
        }

        @Test
        void whenUnparkCar() {
            ParkingPlace carPlace = new CarPlace(1, false);
            Vehicle car = new Car("NIVA", "1111AA");
            carPlace.unpark(car);
            assertThat(carPlace.isBusy()).isFalse();
        }

        @Test
        void whenParkTruckOnCarPlaces() {
            List<ParkingPlace> parking = List.of(
                    new CarPlace(1, true),
                    new CarPlace(2, false),
                    new CarPlace(3, false),
                    new TruckPlace(1, true)
            );
            Vehicle truck = new Truck("Volvo", "1111ER", 2);
            parking.get(1).park(truck);
            assertThat(parking.get(1).isBusy()).isTrue();
            assertThat(parking.get(2).isBusy()).isTrue();
        }

    @Test
    void whenUnparkTruckFromCarPlaces() {
        List<ParkingPlace> parking = List.of(
                new CarPlace(1, true),
                new CarPlace(2, true),
                new CarPlace(3, false),
                new TruckPlace(1, true)
        );
        Vehicle truck = new Truck("Volvo", "1111ER", 2);
        parking.get(0).unpark(truck);
        assertThat(parking.get(0).isBusy()).isFalse();
        assertThat(parking.get(1).isBusy()).isFalse();
    }

    @Test
    void whenFailParkTruckOnCarPlaces() {
        List<ParkingPlace> parking = List.of(
                new CarPlace(1, false),
                new CarPlace(2, true),
                new CarPlace(3, false),
                new TruckPlace(1, true)
        );
        Vehicle truck = new Truck("Volvo", "1111ER", 2);
        assertThrows(IllegalStateException.class, () -> parking.get(0).unpark(truck));
    }
}