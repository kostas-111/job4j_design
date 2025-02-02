package ru.job4j.ood.foodstore.repository;

import ru.job4j.ood.foodstore.model.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    List<Food> products = new ArrayList<>();

    @Override
    public void addProduct(Food product) {
        products.add(product);
    }

    @Override
    public List<Food> getProducts() {
        return products;
    }
}