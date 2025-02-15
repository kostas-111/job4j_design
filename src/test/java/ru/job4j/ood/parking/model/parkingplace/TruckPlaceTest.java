package ru.job4j.ood.parking.model.parkingplace;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.parking.model.vehicle.Car;
import ru.job4j.ood.parking.model.vehicle.Truck;
import ru.job4j.ood.parking.model.vehicle.Vehicle;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TruckPlaceTest {

    @Test
    void whenParkTruck() {
        ParkingPlace carPlace = new CarPlace(1, false);
        ParkingPlace truckPlace = new TruckPlace(1, false);
        List<ParkingPlace> parking = List.of(carPlace, truckPlace);
        Vehicle truck = new Truck("Volvo", "1111AA", 2);
        truckPlace.park(truck, parking);
        assertThat(carPlace.isBusy()).isFalse();
        assertThat(truckPlace.isBusy()).isTrue();
    }

    @Test
    void whenUnparkTruck() {
        ParkingPlace carPlace = new CarPlace(1, true);
        ParkingPlace truckPlace = new TruckPlace(1, true);
        List<ParkingPlace> parking = List.of(carPlace, truckPlace);
        Vehicle truck = new Truck("Volvo", "1111AA", 2);
        truckPlace.unpark(truck, parking);
        assertThat(carPlace.isBusy()).isTrue();
        assertThat(truckPlace.isBusy()).isFalse();
    }

    @Test
    void whenTryParkCarOnTruckplace() {
        ParkingPlace truckPlace = new TruckPlace(1, false);
        List<ParkingPlace> parking = List.of(truckPlace);
        Vehicle car = new Car("NIVA", "1111AA");
        assertThrows(IllegalStateException.class, () -> truckPlace.park(car, parking));
    }
}