package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Printer printer = new Printer(false,
                new Producer("Canon", "30-2, Shimomaruko 3-chome, Ohta-ku, Tokyo 146-8501, Japan", "(81) 3-3758-2111"),
                54500.6, "USB", "Bluetooth", "LAN");

        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Printer.class);

        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();

        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";

        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(printer, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();

        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Printer result = (Printer) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}