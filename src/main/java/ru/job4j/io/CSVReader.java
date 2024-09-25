package ru.job4j.io;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {

        List<String> header;
        List<String> lines = new ArrayList<>();

        /*
        получаем в userFilter список фильтра по столбцам из входных данных
         */
        List<String> userFilter = new ArrayList<>(Arrays.asList(argsName.get("filter").split(",")));

        FileInputStream inputStream = new FileInputStream(argsName.get("path"));
        try (var scanner = new Scanner(inputStream)) {

            /*
             получаем список заголовков в коллекции
             */
            header = new ArrayList<>(Arrays.asList(scanner.nextLine().split(argsName.get("delimiter"))));

            /*
             получаем список строк из таблицы, в которой хранятся данные через разделитель
             */
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }

        /*
         находим индексы столбцов путем сравнения заголовков из файла (header) и входных данных (userFilter),
         добавляем в коллекцию полученные индексы
         */

        List<Integer> findIndex = new ArrayList<>();
        StringBuilder filterHeader = new StringBuilder();
        for (String f : userFilter) {
            for (String st : header)  {
                if (st.equals(f)) {
                    findIndex.add(header.indexOf(st));
                    filterHeader.append(f);
                    filterHeader.append(argsName.get("delimiter"));
                }
            }
        }

        /*
         формируем отфильтрованную коллекцию
         */
        List<String> resultList = new ArrayList<>();

        /*
         добавляем отфильтрованный заголовок
         */
        resultList.add(filterHeader.deleteCharAt(filterHeader.length() - 1).toString());

        for (String res : lines) {
            StringBuilder resultLine = new StringBuilder();

            /*
             создаем коллекцию из записей в строке
             */
            List<String> line = new ArrayList<>(Arrays.asList(res.split(argsName.get("delimiter"))));

            /*
             строим строку по фильтрам с заданным разделителем
             */
            for (Integer i : findIndex) {
                for (String l : line) {
                    if (i.equals(line.indexOf(l))) {
                        resultLine.append(l);
                        resultLine.append(argsName.get("delimiter"));
                    }
                }
            }

            /*
             добавляем отфильтрованные строки в результирующую коллекцию
             */
            resultList.add(resultLine.deleteCharAt(resultLine.length() - 1).toString());
        }

        /*
        'пишем в файл полученный результат или выводим на консоль
         */
        if (!argsName.get("out").equals("stdout")) {
            Files.write(Path.of(argsName.get("out")), resultList);
        } else {
            resultList.forEach(System.out::println);
        }
    }

    public static void checkArgs(ArgsName args) {
        Path routeIn = Paths.get(args.get("path"));

        if (!Files.isDirectory(routeIn) && !Files.exists(routeIn)) {
            throw new IllegalArgumentException(String.format("Directory \"%s\" does not exist.", routeIn));
        }

        if (!"stdout".equals(args.get("out"))) {
            try {
                Path.of(args.get("out"));
            } catch (InvalidPathException e) {
                throw new InvalidPathException(
                        String.format("%s argument should be 'stdout' or valid path", args.get("out")), e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        checkArgs(argsName);
        handle(argsName);
    }
}