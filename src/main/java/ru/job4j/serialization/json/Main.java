package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Printer printer = new Printer(false,
                new Producer("Canon", "30-2, Shimomaruko 3-chome, Ohta-ku, Tokyo 146-8501, Japan", "(81) 3-3758-2111"),
                54500.6, new String[] {"USB", "Bluetooth", "LAN"});

        /* Преобразуем объект printer в json-строку. */
        final Gson jsonPrinter = new GsonBuilder().create();
        System.out.println(jsonPrinter.toJson(printer));

        /* Создаём новую json-строку с модифицированными данными*/
        final String modJsonPrinter =
                "{"
                        + "\"isColour\":false,"
                        + "\"producer\":"
                        + "{"
                        + "\"name\":\"HP Inc.\","
                        + "\"address\":\"1501 Page Mill Road Palo Alto, CA 94304 United States\","
                        + "\"phone\":\"+ 1 650-857-1501\""
                        + "},"
                        + "\"price\":35000,"
                        + "\"typesOfConnection\":"
                        + "[\"Bluetooth\",\"WiFi\"]"
                        + "}";

        /* Превращаем json-строку обратно в объект */
        final Printer printerMod = jsonPrinter.fromJson(modJsonPrinter, Printer.class);
        System.out.println(printerMod);
    }
}
