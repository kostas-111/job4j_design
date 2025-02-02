package ru.job4j.ood.foodstore.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodstore.model.Food;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
class TrashTest {
    @Disabled
    @Test
    void testAddProduct() {
        Trash trash = new Trash();
        Food apple = new Food("Apple", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 5), 50, 0);

        trash.addProduct(apple);

        assertEquals(1, trash.getProducts().size());
        assertEquals(apple, trash.getProducts().get(0));
    }
    @Disabled
    @Test
    void testGetProducts() {
        Trash trash = new Trash();
        Food apple = new Food("Apple", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 5), 50, 0);
        Food banana = new Food("Banana", LocalDate.of(2023, 9, 5), LocalDate.of(2023, 9, 29), 40, 0);

        trash.addProduct(apple);
        trash.addProduct(banana);

        assertEquals(2, trash.getProducts().size());
        assertEquals(apple, trash.getProducts().get(0));
        assertEquals(banana, trash.getProducts().get(1));
    }
}