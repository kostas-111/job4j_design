package ru.job4j.ood.dip.myexamples.third;

/*
интерфейс ReportWriter описывает общий контракт для записи отчётов
 */
public interface ReportWriter {
    void writeHeader();
    void writeBody();
    void save();
}
