package ru.job4j.ood.dip.myexamples.first;

/*
Класс Application напрямую использует конкретный класс Logger.
Если нужно будет изменить способ логирования (например, использовать файловый логгер),
придется менять сам класс Application.
Здесь отсутствуюет абстракции для взаимодействия между модулями
 */
public class Application {
    private final Logger logger;

    public Application() {
        this.logger = new Logger();
    }

    public void run() {
        logger.log("Application started");
    }
}
