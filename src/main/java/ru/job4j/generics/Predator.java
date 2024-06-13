package ru.job4j.generics;

public class Predator extends Animal {
    private String actions;

    public Predator(String name, String type, String actions) {
        super(name, type);
        this.actions = actions;
    }

    public Predator(String actions) {
        this.actions = actions;
    }
}