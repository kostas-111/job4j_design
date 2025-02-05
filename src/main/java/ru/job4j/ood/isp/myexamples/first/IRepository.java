package ru.job4j.ood.isp.myexamples.first;

/*
интерфейс IRepository предназначен для управления данными в базе данных.
Однако интерфейс содержит множество методов, относящихся к различным операциям над разными сущностями.
Проблема здесь заключается в том, что клиенты, работающие с пользователями, продуктами или заказами,
вынуждены реализовывать все методы интерфейса, хотя им нужны лишь методы, относящиеся к одной сущности
 */
public interface IRepository {

    void saveUser();
    void deleteUser(int userId);
    void updateUser();
    void findUserById(int userId);

    void saveProduct();
    void deleteProduct(int productId);
    void updateProduct();
    void findProductById(int productId);

    void saveOrder();
    void deleteOrder();
    void updateOrder();
}