package ru.job4j.ood.foodstore.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodstore.model.Food;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ShopTest {
    @Disabled
    @Test
    void testAddProduct() {
        Shop shop = new Shop();
        Food apple = new Food(UUID.randomUUID().toString(), "Apple", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 12, 31), 50, 0);

        shop.addProduct(apple);

        assertEquals(1, shop.getProducts().size());
        assertEquals(apple, shop.getProducts().get(0));
    }
    @Disabled
    @Test
    void testGetProducts() {
        Shop shop = new Shop();
        Food apple = new Food(UUID.randomUUID().toString(), "Apple", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 12, 31), 50, 0);
        Food banana = new Food(UUID.randomUUID().toString(), "Banana", LocalDate.of(2023, 9, 5), LocalDate.of(2023, 11, 30), 40, 0);

        shop.addProduct(apple);
        shop.addProduct(banana);

        assertEquals(2, shop.getProducts().size());
        assertEquals(apple, shop.getProducts().get(0));
        assertEquals(banana, shop.getProducts().get(1));
    }
}