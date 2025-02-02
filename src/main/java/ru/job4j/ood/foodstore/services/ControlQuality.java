package ru.job4j.ood.foodstore.services;

import ru.job4j.ood.foodstore.model.Food;
import ru.job4j.ood.foodstore.repository.Store;
import java.util.ArrayList;
import java.util.List;

/*
Класс ControlQuality использует агрегацию для хранения списка хранилищ (stores).
Он управляет этими хранилищами, но не владеет ими напрямую.
Связь между ControlQuality и хранилищами является слабосвязанной,
так как используется интерфейс Store
ControlQuality не зависит от конкретной реализации хранилищ,
а оперирует с ними через интерфейс Store.
Это позволяет легко заменять или добавлять новые типы хранилищ без
изменения основного функционала
 */
public class ControlQuality {

    private final List<Store> stores = new ArrayList<>();

    public void addStore(Store store) {
        stores.add(store);
    }

    public void removeStore(Store store) {
        stores.remove(store);
    }

    /*
    Метод проходит по всем доступным хранилищам и пытается распределить каждый продукт.
     */
    public void distribute(List<Food> foods) {
        for (Food food : foods) {
            for (Store store : stores) {
                store.addProduct(food);
            }
        }
    }
}