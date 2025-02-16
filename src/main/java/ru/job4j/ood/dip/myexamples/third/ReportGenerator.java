package ru.job4j.ood.dip.myexamples.third;

/*
Класс ReportGenerator напрямую зависит от конкретной реализации ExcelWriter.
Если понадобится поддерживать другие форматы отчётов (например, PDF),
придётся изменять код класса ReportGenerator.
Чтобы соблюсти принцип DIP, здесь необходимо ввести зависимость через интерфейс ReportWriter.
И тогда класс ReportGenerator не будет знать о конкретной реализации ReportWriter.
Мы можем передать любую реализацию, соответствующую интерфейсу ReportWriter,
при создании объекта ReportGenerator
 */
public class ReportGenerator {
    public void generateReport(String filename) {
        ExcelWriter writer = new ExcelWriter(filename);
        writer.writeHeader();
        writer.writeBody();
        writer.save();
    }
}
