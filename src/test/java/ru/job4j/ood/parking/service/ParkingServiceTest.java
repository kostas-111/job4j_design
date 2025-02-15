package ru.job4j.ood.parking.service;

import org.assertj.core.api.Assertions;
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

class ParkingServiceTest {

    @Test
    void whenPullCar() {
        ParkingCreator parkingCreator = new ParkingCreator();
        parkingCreator.create(2, 2);
        ParkingService parkingService = new ParkingService(parkingCreator);
        List<ParkingPlace> parking = parkingCreator.getParkingPlaceList();
        parking.get(0).setBusy(true);
        Vehicle car = new Car("Lada", "2222");
        parkingService.pullVehicle(car);
        assertTrue(parking.get(1).isBusy());
    }

    @Test
    void whenPushCar() {
        ParkingCreator parkingCreator = new ParkingCreator();
        parkingCreator.create(2, 2);
        ParkingService parkingService = new ParkingService(parkingCreator);
        List<ParkingPlace> parking = parkingCreator.getParkingPlaceList();
        parking.get(1).setBusy(true);
        Vehicle car = new Car("Lada", "2222");
        parkingService.pushVehicle(car);
        assertFalse(parking.get(1).isBusy());
    }

    @Test
    void whenPullTruckOnCarParking() {
        ParkingCreator parkingCreator = new ParkingCreator();
        parkingCreator.create(3, 1);
        ParkingService parkingService = new ParkingService(parkingCreator);
        List<ParkingPlace> parking = parkingCreator.getParkingPlaceList();
        parking.get(0).setBusy(true);
        parking.get(3).setBusy(true);
        Vehicle truck = new Truck("Грузовик", "АА0001", 2);
        List<ParkingPlace> expectedParking = List.of(
                new CarPlace(1, true),
                new CarPlace(2, true),
                new CarPlace(3, true),
                new TruckPlace(1, true)
        );
        parkingService.pullVehicle(truck);
        Assertions.assertThat(parking).containsExactlyElementsOf(expectedParking);
    }

    @Test
    void whenPullTruckOnTruckParking() {
        ParkingCreator parkingCreator = new ParkingCreator();
        parkingCreator.create(2, 2);
        ParkingService parkingService = new ParkingService(parkingCreator);
        List<ParkingPlace> parking = parkingCreator.getParkingPlaceList();
        parking.get(0).setBusy(true);
        Vehicle truck = new Truck("Грузовик", "АА0001", 2);
        parkingService.pullVehicle(truck);
        List<ParkingPlace> expectedParking = List.of(
                new CarPlace(1, true),
                new CarPlace(2, false),
                new TruckPlace(1, true),
                new TruckPlace(2, false)
        );
        Assertions.assertThat(parking).containsExactlyElementsOf(expectedParking);
    }
}