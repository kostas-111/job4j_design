package ru.job4j.ood.foodstore.repository;

import ru.job4j.ood.foodstore.model.Food;

import java.util.List;

/*
Интерфейс Store определяет общие методы для добавления и получения продуктов,
которые будут использоваться всеми хранилищами
Система открыта для расширения, так как можно добавлять новые типы хранилищ, реализующие интерфейс Store,
без изменения существующего кода
 */
public interface Store {
    void addProduct(Food product);
    List<Food> getProducts();
}
