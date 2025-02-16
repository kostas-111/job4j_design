package ru.job4j.ood.dip.myexamples.second;

/*
Все подклассы Animal вынуждены реализовывать метод move(),
хотя некоторые животные могут вовсе не двигаться.
Это нарушает второй пункт DIP, поскольку абстракция (Animal) зависит от деталей (метод move()).
Здесь наблюдается неправильное использование наследования
 */
public class Dog extends Animal {
    @Override
    public void move() {
        super.move();
        System.out.println("Dog is running");
    }
}
