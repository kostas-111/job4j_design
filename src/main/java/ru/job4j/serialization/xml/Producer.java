package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "producer")
public class Producer {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String address;

    @XmlAttribute
    private String phone;

    public Producer() {

    }

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
