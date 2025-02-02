package ru.job4j.ood.foodstore.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodstore.model.Food;
import ru.job4j.ood.foodstore.repository.Shop;
import ru.job4j.ood.foodstore.repository.Trash;
import ru.job4j.ood.foodstore.repository.Warehouse;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
        LocalDate expiryDate = LocalDate.now().plusDays(10);
        LocalDate createDate = LocalDate.now().minusDays(1);
        Food apple = new Food(UUID.randomUUID().toString(), "Apple", expiryDate, createDate, 50, 0);
        controlQuality.distribute(List.of(apple));
        assertEquals(1, warehouse.getProducts().size());
        assertEquals(apple, warehouse.getProducts().get(0));
    }

    @Test
    void testDistributeToShop() {
        LocalDate expiryDate = LocalDate.now().plusDays(7);
        LocalDate createDate = LocalDate.now().minusDays(6);
        Food banana = new Food(UUID.randomUUID().toString(), "Banana", expiryDate, createDate, 120, 0);
        controlQuality.distribute(List.of(banana));
        assertEquals(1, shop.getProducts().size());
        assertEquals(banana, shop.getProducts().get(0));
    }

    @Test
    void testDistributeToTrash() {
        LocalDate expiryDate = LocalDate.now();
        LocalDate createDate = LocalDate.of(2025, 1, 1);
        Food milk = new Food(UUID.randomUUID().toString(), "Milk", expiryDate, createDate, 110, 0);
        controlQuality.distribute(List.of(milk));
        assertEquals(1, trash.getProducts().size());
        assertEquals(milk, trash.getProducts().get(0));
    }

    @Test
    void testDistributeWithDiscount() {
        LocalDate expiryDate = LocalDate.now().plusDays(1);
        LocalDate createDate = LocalDate.now().minusDays(6);
        Food orange = new Food(UUID.randomUUID().toString(), "Orange", expiryDate, createDate, 60, 0);
        controlQuality.distribute(List.of(orange));
        assertEquals(1, shop.getProducts().size());
        assertEquals(48.0, orange.getPrice());
        assertEquals(0.2, orange.getDiscount());
    }
}