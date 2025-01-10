package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.ood.srp.currency.Currency.*;

class ReportEngineCountingRoomTest {

    @Test
    public void whenCountingRoomGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee workerOne = new Employee("Ivan", now, now, 100);
        Employee workerTwo = new Employee("Vasilisa", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        CurrencyConverter currencyConverter = new InMemoryCurrencyConverter();
        store.add(workerOne);
        store.add(workerTwo);
        Currency source = USD;
        Currency target = RUB;
        double salaryWorkerOne = currencyConverter.convert(source, workerOne.getSalary(), target);
        double salaryWorkerTwo = currencyConverter.convert(source, workerTwo.getSalary(), target);
        Report engine = new ReportEngineCountingRoom(store, parser, currencyConverter, source, target);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        expected.append(workerOne.getName()).append("; ")
                .append(parser.parse(workerOne.getHired())).append("; ")
                .append(parser.parse(workerOne.getFired())).append("; ")
                .append(salaryWorkerOne)
                .append(System.lineSeparator())
                .append(workerTwo.getName()).append("; ")
                .append(parser.parse(workerTwo.getHired())).append("; ")
                .append(parser.parse(workerTwo.getFired())).append("; ")
                .append(salaryWorkerTwo)
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}