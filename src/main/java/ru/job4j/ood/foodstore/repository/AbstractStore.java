package ru.job4j.ood.foodstore.repository;

import ru.job4j.ood.foodstore.model.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

    protected static int warehousePercentage = 25;
    protected static int discountPersentage = 75;
    protected static int trashPercentage = 100;

    List<Food> products = new ArrayList<>();

    @Override
    public List<Food> getProducts() {
        return products;
    }
}