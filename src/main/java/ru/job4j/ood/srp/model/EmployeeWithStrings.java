package ru.job4j.ood.srp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import java.util.Calendar;

@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeWithStrings {

    private final String name;
    private final String hired;
    private final String fired;
    private final double salary;

    /*
    Конструктор принимает объект Employee
    и конвертирует даты в строки
     */
    public EmployeeWithStrings(Employee employee) {
        this.name = employee.getName();
        this.salary = employee.getSalary();
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        this.hired = dateTimeParser.parse(employee.getHired());
        this.fired = dateTimeParser.parse(employee.getFired());
    }

    public String getName() {
        return name;
    }

    public String getHired() {
        return hired;
    }

    public String getFired() {
        return fired;
    }

    public double getSalary() {
        return salary;
    }
}