package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import java.util.Calendar;
import static org.assertj.core.api.Assertions.*;


class ReportEngineITTest {
    @Test
    public void whenITGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee workerOne = new Employee("Ivan", now, now, 100);
        Employee workerTwo = new Employee("Vasilisa", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        String delimeter = ",";
        store.add(workerOne);
        store.add(workerTwo);
        Report engine = new ReportEngineIT(store, parser, delimeter);
        StringBuilder expected = new StringBuilder()
                .append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator());
        expected.append(workerOne.getName()).append(delimeter)
                .append(parser.parse(workerOne.getHired())).append(delimeter)
                .append(parser.parse(workerOne.getFired())).append(delimeter)
                .append(workerOne.getSalary())
                .append(System.lineSeparator())
                .append(workerTwo.getName()).append(",")
                .append(parser.parse(workerTwo.getHired())).append(delimeter)
                .append(parser.parse(workerTwo.getFired())).append(delimeter)
                .append(workerTwo.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}