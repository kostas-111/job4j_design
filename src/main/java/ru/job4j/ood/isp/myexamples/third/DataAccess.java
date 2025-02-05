package ru.job4j.ood.isp.myexamples.third;

public interface DataAccess {

    void insert(Object entity);
    void update(Object entity);
    void delete(Object entity);
}