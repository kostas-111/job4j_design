package ru.job4j.serialization.xml;


import jakarta.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "printer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Printer {

    @XmlAttribute
    private boolean isColour;
    private Producer producer;
    @XmlAttribute
    private double price;

    @XmlElementWrapper(name = "typesOfConnection")
    @XmlElement(name = "type")
    private String[] typesOfConnection;

    public Printer() {

    }

    public Printer(boolean isColour, Producer producer, double price, String... typesOfConnection) {
        this.isColour = isColour;
        this.producer = producer;
        this.price = price;
        this.typesOfConnection = typesOfConnection;
    }

    @Override
    public String toString() {
        return "Printer{"
                + "isColour=" + isColour
                + ", producer=" + producer
                + ", price=" + price
                + ", typesOfConnection=" + Arrays.toString(typesOfConnection)
                + '}';
    }
}
