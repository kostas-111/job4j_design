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
class TruckPlaceTest {

    @Test
    void whenParkTruck() {
        ParkingPlace truckPlace = new TruckPlace(1, false);
        Vehicle truck = new Truck("Volvo", "1111AA", 2);
        truckPlace.park(truck);
        assertThat(truckPlace.isBusy()).isTrue();
    }

    @Test
    void whenUnparkTruck() {
        ParkingPlace truckPlace = new TruckPlace(1, true);
        Vehicle truck = new Truck("Volvo", "1111AA", 2);
        truckPlace.unpark(truck);
        assertThat(truckPlace.isBusy()).isFalse();
    }

    @Test
    void whenTryParkCarOnTruckplace() {
        ParkingPlace truckPlace = new TruckPlace(1, false);
        Vehicle car = new Car("NIVA", "1111AA");
        assertThrows(IllegalStateException.class, () -> truckPlace.park(car));
    }
}