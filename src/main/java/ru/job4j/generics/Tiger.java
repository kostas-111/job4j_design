package ru.job4j.generics;

public class Tiger extends Predator {
    private int weigth;
    private String sex;

    public Tiger(String actions, int weigth, String sex) {
        super(actions);
        this.weigth = weigth;
        this.sex = sex;
    }
}