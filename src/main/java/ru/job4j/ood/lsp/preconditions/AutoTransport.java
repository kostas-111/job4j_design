package ru.job4j.ood.lsp.preconditions;

public class AutoTransport {
    protected float fuel;

    public AutoTransport(float fuel) {
        this.fuel = fuel;
    }

    public void move(float km) {
        if (km < 0) {
            throw new IllegalArgumentException("Invalid distance!");
        }
        if (fuel < 0) { /* <= предусловие */
            throw new IllegalArgumentException("Need more fuel!");
        }
        /*
        other logic
         */
    }
}