package ru.job4j.ood.foodstore.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodstore.model.Food;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThatList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShopTest {

    private Shop shop;
    private Food goodFood;
    private Food trashFood;

    @BeforeEach
    void setup() {
        shop = new Shop();
        LocalDate expiryDate = LocalDate.now().plusDays(7);
        LocalDate createDate = LocalDate.now().minusDays(6);
        goodFood = new Food(UUID.randomUUID().toString(), "Cheese", expiryDate, createDate, 500, 0);
        trashFood = new Food(UUID.randomUUID().toString(), "Cheese", expiryDate.minusDays(10), createDate, 500, 0);
    }

    @Test
    void testAddProduct() {
        shop.addProduct(goodFood);
        assertEquals(1, shop.getProducts().size());
        assertEquals(goodFood, shop.getProducts().get(0));
    }

    @Test
    void testAddOnlyGoodProduct() {
        shop.addProduct(goodFood);
        shop.addProduct(trashFood);
        assertEquals(1, shop.getProducts().size());
        assertThatList(shop.getProducts()).doesNotContain(trashFood);
    }

    @Test
    void testGetProducts() {
        shop.addProduct(goodFood);
        shop.addProduct(trashFood);
        List<Food> expected = List.of(goodFood);
        assertThatList(expected).containsAll(shop.getProducts());
    }
}