package ru.job4j.serialization.json;

public class Producer {
    private final String name;
    private final String address;
    private final String phone;

    public Producer(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Producer{"
                + "name='" + name + '\''
                + ", address='" + address + '\''
                + ", phone='" + phone + '\''
                + '}';
    }
}