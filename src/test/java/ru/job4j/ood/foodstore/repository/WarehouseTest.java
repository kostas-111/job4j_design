package ru.job4j.ood.foodstore.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodstore.model.Food;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class WarehouseTest {

    @Test
    void testAddProduct() {
        Warehouse warehouse = new Warehouse();
        Food apple = new Food("Apple", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 11, 15), 50, 0);
        warehouse.addProduct(apple);
        assertEquals(1, warehouse.getProducts().size());
        assertEquals(apple, warehouse.getProducts().get(0));
    }

    @Test
    void testGetProducts() {
        Warehouse warehouse = new Warehouse();
        Food apple = new Food("Apple", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 11, 15), 50, 0);
        Food banana = new Food("Banana", LocalDate.of(2023, 9, 5), LocalDate.of(2023, 10, 30), 40, 0);

        warehouse.addProduct(apple);
        warehouse.addProduct(banana);

        assertEquals(2, warehouse.getProducts().size());
        assertEquals(apple, warehouse.getProducts().get(0));
        assertEquals(banana, warehouse.getProducts().get(1));
    }

}