package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReportEngineIT implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final String delimeter;

    public ReportEngineIT(Store store,
                          DateTimeParser<Calendar> dateTimeParser,
                          String delimeter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.delimeter = delimeter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<String> header = Arrays.asList("Name", "Hired", "Fired", "Salary");
        String delimeterSeparateHeader = header.stream()
                .map(String::toString)
                .collect(Collectors.joining(delimeter));
        StringBuilder text = new StringBuilder();
        text.append(delimeterSeparateHeader).append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(delimeter)
                    .append(dateTimeParser.parse(employee.getHired())).append(delimeter)
                    .append(dateTimeParser.parse(employee.getFired())).append(delimeter)
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}