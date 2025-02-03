package ru.job4j.ood.foodstore.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodstore.model.Food;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThatList;
import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    private Warehouse warehouse;
    private Food goodFood;
    private Food trashFood;

    @BeforeEach
    void setup() {
        warehouse = new Warehouse();
        LocalDate expiryDate = LocalDate.now().plusDays(10);
        LocalDate createDate = LocalDate.now().minusDays(1);
        goodFood = new Food(UUID.randomUUID().toString(), "Cheese", expiryDate, createDate, 500, 0);
        trashFood = new Food(UUID.randomUUID().toString(), "Cheese", expiryDate.minusDays(10), createDate, 500, 0);
    }

    @Test
    void testAddProduct() {
        warehouse.addProduct(goodFood);
        assertEquals(1, warehouse.getProducts().size());
        assertEquals(goodFood, warehouse.getProducts().get(0));
    }

    @Test
    void testAddOnlyGoodProduct() {
        warehouse.addProduct(goodFood);
        warehouse.addProduct(trashFood);
        assertEquals(1, warehouse.getProducts().size());
        assertThatList(warehouse.getProducts()).doesNotContain(trashFood);
    }

    @Test
    void testGetProducts() {
        warehouse.addProduct(goodFood);
        warehouse.addProduct(trashFood);
        List<Food> expected = List.of(goodFood);
        assertThatList(expected).containsAll(warehouse.getProducts());
    }
}