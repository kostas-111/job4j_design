package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /* JSONObject из json-строки строки */
        JSONObject jsonProducer =
                new JSONObject("{\"name\":\"HP Inc.\", "
                                        + "\"address\":\"1501 Page Mill Road Palo Alto, CA 94304 United States\","
                                        +  "\"phone\":\"+ 1 650-857-1501\"}");

        /* JSONArray из ArrayList */
        List<String> conTypes = new ArrayList<>();
        conTypes.add("USB");
        conTypes.add("Bluetooth");
        conTypes.add("LAN");
        JSONArray jsonConTypes = new JSONArray(conTypes);

        /* JSONObject напрямую методом put */
        final Printer printer = new Printer(false, 54500.6, "USB", "Bluetooth", "LAN");
        JSONObject jsonPrinter = new JSONObject();
        jsonPrinter.put("isColour", printer.isColour());
        jsonPrinter.put("producer", jsonProducer);
        jsonPrinter.put("price", printer.getPrice());
        jsonPrinter.put("typesOfConnection", jsonConTypes);

        /* Выведем результат в консоль */
        System.out.println(jsonPrinter);

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(printer));
    }
}