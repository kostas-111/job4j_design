package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkArrayStringEmpty() {
        NameLoad nl = new NameLoad();
        assertThatThrownBy(nl::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkNotContainSymbol() {
        NameLoad nl = new NameLoad();
        assertThatThrownBy(() -> nl.parse("wrongKey:value", "key1=value1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("wrongKey:value");
    }

    @Test
    void checkNotContainKey() {
        NameLoad nl = new NameLoad();
        assertThatThrownBy(() -> nl.parse("Key=value", "=key1=value1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=key1=value1");
    }

    @Test
    void checkNotContainValue() {
        NameLoad nl = new NameLoad();
        assertThatThrownBy(() -> nl.parse("Key=", "key1="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Key=", "key1=");
    }
}