package ru.job4j.io;

import org.junit.jupiter.api.Test;
import ru.job4j.collection.ListUtils;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenValueEqualKey() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.url")).isEqualTo("jdbc:postgresql://127.0.0.1:5432/trackstudio");
    }

    @Test
    void whenValueNotEqual() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("Konstantin")).isNull();
    }

    @Test
    void whenBadKeyValue() {
        String path = "./data/bad_key_value.properties";
        Config config = new Config(path);
       assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenValueWithoutSplit() {
        String path = "./data/value_without_split.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenBadValue() {
        String path = "./data/bad_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenOnlyComments() {
        String path = "./data/only_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("# hibernate.connection.username")).isNull();
    }
}