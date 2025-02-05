package ru.job4j.ood.isp.myexamples.third;

/*
у нас есть интерфейсы Logging, Auditing и DataAccess,
и класс AuditService реализует все три интерфейса.
Хотя ему нужны только методы для логирования и аудита.
Методы для доступа к данным остаются неиспользуемыми, что нарушает ISP.
 */
class AuditService implements Logging, Auditing, DataAccess {

    @Override
    public void logInfo(String message) {
        /*
        Реализация логирования информации
         */
    }

    @Override
    public void logWarning(String message) {
        /*
        Реализация логирования предупреждений
         */
    }

    @Override
    public void logError(String message) {
        /*
        Реализация логирования ошибок
         */
    }

    @Override
    public void auditEvent(String eventType, String details) {
        /*
         Реализация аудита события
         */
    }

    /*
    Методы DataAccess остаются нереализованными
     */
    @Override
    public void insert(Object entity) {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Object entity) {

    }
}