package ru.job4j.ood.parking.model.vehicle;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TruckTest {

    @Test
    void whenCreateWrongTruck() {
        assertThrows(IllegalArgumentException.class, () -> new Truck("Volvo", "R111RN", 1));
    }

    @Test
    void getTruckSize() {
        Truck truck = new Truck("Грузовик", "АА0001", 3);
        assertThat(3).isEqualTo(truck.getSize());
    }
}