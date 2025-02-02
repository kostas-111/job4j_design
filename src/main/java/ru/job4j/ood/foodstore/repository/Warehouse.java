package ru.job4j.ood.foodstore.repository;

import ru.job4j.ood.foodstore.model.Food;
import ru.job4j.ood.foodstore.services.ShelfLifeCalculator;

public class Warehouse extends AbstractStore {

    @Override
    public void addProduct(Food food) {
        int remainingShelfLife = ShelfLifeCalculator.calculateRemainingShelfLifePercentage(food);
        if (remainingShelfLife < 25) {
            products.add(food);
        }
    }
}
