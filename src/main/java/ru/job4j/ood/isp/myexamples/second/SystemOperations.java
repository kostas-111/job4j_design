package ru.job4j.ood.isp.myexamples.second;

/*
 интерфейс SystemOperations включает методы для отправки электронной почты,
 обработки платежей и резервного копирования данных.
 */
public interface SystemOperations {

    void sendEmail(String to, String subject, String body);
    boolean processPayment(double amount);
    void backupData();
}

/*
EmailService реализует интерфейс SystemOperations, хотя ему нужен только метод для отправки электронной почты.
Методы для обработки платежей и резервного копирования остаются неиспользуемыми, что нарушает ISP
 */
class EmailService implements SystemOperations {
    @Override
    public void sendEmail(String to, String subject, String body) {
        /*
        Реализация отправки электронной почты
         */
    }

    @Override
    public boolean processPayment(double amount) {
        /*
        Неиспользуемый метод
         */
        return false;
    }

    @Override
    public void backupData() {
        /*
        Неиспользуемый метод
         */
    }
}