package ru.job4j.spammer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import static org.assertj.core.api.Assertions.*;

class ImportDBTest {

    @Test
    void whenOnlyName(@TempDir Path folder) throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "Petr;"
        );
        File file = folder.resolve("source.txt").toFile();
        Files.writeString(file.toPath(), data);
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("spammer_jdbc.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, file.getAbsolutePath());
        assertThatThrownBy(() -> dataBase.save(dataBase.load())).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Формат данных не соответствует шаблону \"имя;адрес электронной почты\" ");
    }

    @Test
    void whenWhitespaces(@TempDir Path folder) throws Exception {
        String data = String.join(
                System.lineSeparator(),
                " ; "
        );
        File file = folder.resolve("source.txt").toFile();
        Files.writeString(file.toPath(), data);
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("spammer_jdbc.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, file.getAbsolutePath());
        assertThatThrownBy(() -> dataBase.save(dataBase.load())).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Формат данных не соответствует шаблону \"имя;адрес электронной почты\" ");
    }

    @Test
    void whenOnlyEmail(@TempDir Path folder) throws Exception {
        String data = String.join(
                System.lineSeparator(),
                " ;ars@gmail.com"
        );
        File file = folder.resolve("source.txt").toFile();
        Files.writeString(file.toPath(), data);
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("spammer_jdbc.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, file.getAbsolutePath());
        assertThatThrownBy(() -> dataBase.save(dataBase.load())).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Формат данных не соответствует шаблону \"имя;адрес электронной почты\" ");
    }
}