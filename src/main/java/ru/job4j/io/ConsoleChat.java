package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    Pattern patternOut = Pattern.compile(OUT, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    Pattern patternStop = Pattern.compile(STOP, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    Pattern patternCont = Pattern.compile(CONTINUE, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    public void run() {
        List<String> reply = readPhrases();
        List<String> log = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean isActive = true;
        String act = CONTINUE;
        while (!patternOut.matcher(act).matches()) {
            String phrase = sc.nextLine();
            log.add(phrase);
            if (patternOut.matcher(phrase).matches()) {
                isActive = false;
                saveLog(log);
                act = OUT;
            }
            if (patternStop.matcher(phrase).matches()) {
                isActive = false;
            }
            if (patternCont.matcher(phrase).matches()) {
                isActive = true;
            }
            if (isActive) {
                String botReply = reply.get(new Random().nextInt(reply.size()));
                log.add(botReply);
                System.out.println(botReply);
            }
        }
        sc.close();
    }

    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines()
                    .forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/bot_logs.txt", "data/bot_answers.txt");
        consoleChat.run();
    }
}