package ru.job4j.ood.srp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

/*
Класс для формирования xml по шаблону из задания.
Передаем сюда коллекцию объектов EmployeeWithStrings,
в которых преобразовали даты в строки
 */
@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeForXmlRoot {

    @XmlElement(name = "employee")
    private List<EmployeeWithStrings> employees;

    public EmployeeForXmlRoot() {
    }

    public EmployeeForXmlRoot(List<EmployeeWithStrings> employees) {
        this.employees = employees;
    }
}