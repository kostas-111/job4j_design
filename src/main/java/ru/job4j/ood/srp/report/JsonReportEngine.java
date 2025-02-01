package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeWithStrings;
import ru.job4j.ood.srp.store.Store;
import java.util.List;
import java.util.function.Predicate;

public class JsonReportEngine implements Report {
    private final Store store;

    private final Gson gsonReport;

    public JsonReportEngine(Store store) {
        this.store = store;
        this.gsonReport = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    /*
    Метод генерации отчета в формате JSON с использованием библиотеки Gson.
    Реализация я с .setPrettyPrinting().create() формирует красиво отформатированный JSON с отступами.
    По умолчанию (Gson gson = new Gson();) библиотека Gson создает компактный JSON
    без отступов и переносов строк
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        List<EmployeeWithStrings> employees = store.findBy(filter).stream()
                .map(EmployeeWithStrings::new)
                .toList();
        return gsonReport.toJson(employees);
    }
}