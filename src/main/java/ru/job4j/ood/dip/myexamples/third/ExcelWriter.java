package ru.job4j.ood.dip.myexamples.third;

/*
Конкретная реализация для записи в Excel
 */
public class ExcelWriter implements ReportWriter {
    private String filename;

    public ExcelWriter(String filename) {
        this.filename = filename;
    }

    @Override
    public void writeHeader() {
        /*
         Логика записи заголовков в Excel
         */
    }

    @Override
    public void writeBody() {
        /*
         Логика заполнения данными
         */
    }

    @Override
    public void save() {
        /*
        Логика сохранения
         */
    }
}
