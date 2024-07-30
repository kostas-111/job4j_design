package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> result = new ArrayList<>();
        boolean serverStopped = false;
        String startTime = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(" ", 2);
                String time = parts[1];
                boolean condition = line.startsWith("400") || line.startsWith("500");
                if (condition && !serverStopped) {
                    serverStopped = true;
                    startTime = time;
                } else if (!condition && serverStopped) {
                    serverStopped = false;
                    result.add(startTime + ";" + time + ";");
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter output = new PrintWriter(
                new BufferedWriter(
                             new FileWriter(target)))) {
            for (String period : result) {
                output.write(period);
                output.println();
            }
        } catch (IOException err) {
            err.printStackTrace();
        }

    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}