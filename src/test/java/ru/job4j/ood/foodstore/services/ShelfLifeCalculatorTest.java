package ru.job4j.ood.foodstore.services;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.foodstore.model.Food;
import java.time.LocalDate;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
import static ru.job4j.ood.foodstore.services.ShelfLifeCalculator.calculateRemainingShelfLifePercentage;

class ShelfLifeCalculatorTest {

    @Test
    void whenExpiryDateForTrash() {
        LocalDate expiryDate = LocalDate.now();
        LocalDate createDate = LocalDate.of(2025, 1, 1);
        Food banana = new Food(UUID.randomUUID().toString(), "Banana", expiryDate, createDate, 120, 0);
        int result = calculateRemainingShelfLifePercentage(banana);
        assertThat(result).isEqualTo(100);
    }

    @Test
    void whenExpiryDateLessThanNowAndForTrash() {
        LocalDate expiryDate = LocalDate.now().minusDays(3);
        LocalDate createDate = LocalDate.of(2025, 1, 1);
        Food banana = new Food(UUID.randomUUID().toString(), "Banana", expiryDate, createDate, 120, 0);
        int result = calculateRemainingShelfLifePercentage(banana);
        assertThat(result).isEqualTo(100);
    }

    @Test
    void whenExpiryDateForShop() {
        LocalDate expiryDate = LocalDate.now().plusDays(7);
        LocalDate createDate = LocalDate.now().minusDays(6);
        Food banana = new Food(UUID.randomUUID().toString(), "Banana", expiryDate, createDate, 120, 0);
        int result = calculateRemainingShelfLifePercentage(banana);
        assertThat(result).isEqualTo(46);
    }

    @Test
    void whenExpiryDateForShopDiscount() {
        LocalDate expiryDate = LocalDate.now().plusDays(1);
        LocalDate createDate = LocalDate.now().minusDays(6);
        Food banana = new Food(UUID.randomUUID().toString(), "Banana", expiryDate, createDate, 120, 0);
        int result = calculateRemainingShelfLifePercentage(banana);
        assertThat(result).isEqualTo(86);
    }

    @Test
    void whenExpiryDateForWarehouse() {
        LocalDate expiryDate = LocalDate.now().plusDays(10);
        LocalDate createDate = LocalDate.now().minusDays(1);
        Food banana = new Food(UUID.randomUUID().toString(), "Banana", expiryDate, createDate, 120, 0);
        int result = calculateRemainingShelfLifePercentage(banana);
        assertThat(result).isEqualTo(9);
    }
}