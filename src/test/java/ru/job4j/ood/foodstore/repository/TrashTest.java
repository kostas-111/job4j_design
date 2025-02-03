package ru.job4j.ood.foodstore.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodstore.model.Food;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThatList;
import static org.junit.jupiter.api.Assertions.assertEquals;


class TrashTest {

    private Trash trash;
    private Food goodFood;
    private Food trashFood;

    @BeforeEach
    void setup() {
        trash = new Trash();
        LocalDate expiryDate = LocalDate.now().plusDays(7);
        LocalDate createDate = LocalDate.now().minusDays(6);
        goodFood = new Food(UUID.randomUUID().toString(), "Cheese", expiryDate, createDate, 500, 0);
        trashFood = new Food(UUID.randomUUID().toString(), "Cheese", LocalDate.now(), createDate, 500, 0);
    }

    @Test
    void testAddProduct() {
        trash.addProduct(trashFood);
        assertEquals(1, trash.getProducts().size());
        assertEquals(trashFood, trash.getProducts().get(0));
    }

    @Test
    void testAddOnlyGoodProduct() {
        trash.addProduct(goodFood);
        trash.addProduct(trashFood);
        assertEquals(1, trash.getProducts().size());
        assertThatList(trash.getProducts()).doesNotContain(goodFood);
    }

    @Test
    void testGetProducts() {
        trash.addProduct(goodFood);
        trash.addProduct(trashFood);
        List<Food> expected = List.of(trashFood);
        assertThatList(expected).containsAll(trash.getProducts());
    }
}