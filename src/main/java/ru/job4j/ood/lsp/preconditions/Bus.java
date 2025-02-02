package ru.job4j.ood.lsp.preconditions;

class Bus extends AutoTransport {

    public Bus(float fuel) {
        super(fuel);
    }

    public void move(float km) {
        if (km < 0) {
            throw new IllegalArgumentException("Invalid distance!");
        }
        if (fuel < 5) { /* условие усилено */
            throw new IllegalArgumentException("Need more fuel!");
        }
        /*
        other logic
        */
    }
}