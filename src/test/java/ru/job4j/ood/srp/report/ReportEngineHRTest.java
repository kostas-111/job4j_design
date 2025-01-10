package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportEngineHRTest {

    @Test
    public void whenHRGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee workerOne = new Employee("Ivan", now, now, 100);
        Employee workerTwo = new Employee("Vasilisa", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(workerOne);
        store.add(workerTwo);
        Report engine = new ReportEngineHR(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator());
        expected.append(workerTwo.getName()).append("; ")
                .append(workerTwo.getSalary())
                .append(System.lineSeparator())
                .append(workerOne.getName()).append("; ")
                .append(workerOne.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}