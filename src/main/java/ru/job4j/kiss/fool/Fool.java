package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        boolean isComputer = true;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            String result = getGameResult(startAt);
            if (isComputer) {
                System.out.println(result);
                isComputer = false;
            } else {
                String answer = input.nextLine();
                if (!answer.equals(result)) {
                    System.out.println("Ошибка. Начинай снова.");
                    startAt = 0;
                }
                isComputer = true;
            }
            startAt++;
        }
        System.out.println("Поздравляем! Вы выиграли!");
        input.close();
    }

    private static String getGameResult(int number) {
        StringBuilder result = new StringBuilder();
        if (number % 3 == 0) {
            result.append("Fizz");
        }
        if (number % 5 == 0) {
            result.append("Buzz");
        }
        String resultStr = result.toString();
        return resultStr.isBlank() ? Integer.toString(number) : resultStr;
    }
}
