package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            String line = reader.readLine();
            while (line != null) {
                line = line.strip();
                if (!line.startsWith("#") && !line.isEmpty()) {
                    if (line.indexOf('=') > 0 && !line.endsWith("=") && !line.startsWith("=")) {
                        String[] pairKeyValue = line.split("=", 2);
                        values.put(pairKeyValue[0], pairKeyValue[1]);
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.getOrDefault(key, null);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

   public static void main(String[] args) {
       System.out.println(new Config("./data/bad_key_value.properties"));
   }
}