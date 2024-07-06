package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> result = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader("data/log.txt"))) {
            String line;
            while ((line = input.readLine()) != null) {
                String[] logs = line.split(" ");
                if ("404".equals(logs[logs.length - 2])) {
                    result.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter pw =
                     new PrintWriter(new BufferedOutputStream(
                             new FileOutputStream(out)))) {
            data.forEach(pw::println);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}