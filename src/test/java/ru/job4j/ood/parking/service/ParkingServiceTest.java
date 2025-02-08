package ru.job4j.ood.parking.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.parking.model.parkingplace.CarPlace;
import ru.job4j.ood.parking.model.parkingplace.ParkingPlace;
import ru.job4j.ood.parking.model.parkingplace.TruckPlace;
import ru.job4j.ood.parking.model.vehicle.Car;
import ru.job4j.ood.parking.model.vehicle.Truck;
import ru.job4j.ood.parking.model.vehicle.Vehicle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
class ParkingServiceTest {

    @Test
    void whenPullCar() {
        List<ParkingPlace> parking = List.of(
                new CarPlace(1, true),
                new CarPlace(2, false),
                new TruckPlace(1, false),
                new TruckPlace(2, false)
        );
        Vehicle car = new Car("Lada", "2222");
        ParkingService parkingService = new ParkingService();
        parkingService.pullVehicle(parking, car);
        assertTrue(parking.get(1).isBusy());
    }

    @Test
    void whenPushCar() {
        List<ParkingPlace> parking = List.of(
                new CarPlace(1, false),
                new CarPlace(2, true),
                new TruckPlace(1, false),
                new TruckPlace(2, false)
        );
        Vehicle car = new Car("Lada", "2222");
        ParkingService parkingService = new ParkingService();
        parkingService.pushVehicle(parking, car);
        assertFalse(parking.get(1).isBusy());
    }

    @Test
    void whenPullTruckOnCarParking() {
        List<ParkingPlace> parking = List.of(
                new CarPlace(1, true),
                new CarPlace(2, false),
                new CarPlace(3, false),
                new TruckPlace(1, true)
        );
        Vehicle truck = new Truck("Грузовик", "АА0001", 2);
        ParkingService parkingService = new ParkingService();
        List<ParkingPlace> expectedParking = List.of(
                new CarPlace(1, true),
                new CarPlace(2, true),
                new CarPlace(3, true),
                new TruckPlace(1, true)
        );
        Assertions.assertThat(parkingService.pullVehicle(parking, truck)).containsExactlyElementsOf(expectedParking);
    }

    @Test
    void whenPullTruckOnTruckParking() {
        List<ParkingPlace> parking = List.of(
                new CarPlace(1, true),
                new CarPlace(2, false),
                new TruckPlace(1, false),
                new TruckPlace(2, false)
        );
        Vehicle truck = new Truck("Грузовик", "АА0001", 2);
        ParkingService parkingService = new ParkingService();
        parkingService.pullVehicle(parking, truck);
        List<ParkingPlace> expectedParking = List.of(
                new CarPlace(1, true),
                new CarPlace(2, false),
                new TruckPlace(1, true),
                new TruckPlace(2, false)
        );
        Assertions.assertThat(parkingService.pullVehicle(parking, truck)).containsExactlyElementsOf(expectedParking);
    }
}