package ru.job4j.ood.foodstore.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodstore.model.Food;
import ru.job4j.ood.foodstore.repository.Shop;
import ru.job4j.ood.foodstore.repository.Trash;
import ru.job4j.ood.foodstore.repository.Warehouse;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ControlQualityTest {
    private ControlQuality controlQuality;
    private Warehouse warehouse;
    private Shop shop;
    private Trash trash;

    @BeforeEach
    void setup() {
        warehouse = new Warehouse();
        shop = new Shop();
        trash = new Trash();
        controlQuality = new ControlQuality();

        controlQuality.addStore(warehouse);
        controlQuality.addStore(shop);
        controlQuality.addStore(trash);
    }

    @Test
    void testDistributeToWarehouse() {
        Food apple = new Food("Apple", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 11, 15), 50, 0);

        controlQuality.distribute(List.of(apple));

        assertEquals(1, warehouse.getProducts().size());
        assertEquals(apple, warehouse.getProducts().get(0));
    }

    @Test
    void testDistributeToShop() {
        Food banana = new Food("Banana", LocalDate.of(2023, 9, 5), LocalDate.of(2023, 10, 30), 40, 0);

        controlQuality.distribute(List.of(banana));

        assertEquals(1, shop.getProducts().size());
        assertEquals(banana, shop.getProducts().get(0));
    }

    @Test
    void testDistributeToTrash() {
        Food milk = new Food("Milk", LocalDate.of(2023, 10, 7), LocalDate.of(2023, 10, 13), 70, 0);

        controlQuality.distribute(List.of(milk));

        assertEquals(1, trash.getProducts().size());
        assertEquals(milk, trash.getProducts().get(0));
    }

    @Test
    void testDistributeWithDiscount() {
        Food orange = new Food("Orange", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 28), 60, 0);

        controlQuality.distribute(List.of(orange));

        assertEquals(1, shop.getProducts().size());
        assertEquals(48.0, orange.getPrice()); // Цена со скидкой 20%
    }
}