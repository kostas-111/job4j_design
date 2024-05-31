package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .startsWith("first")
                .contains("second")
                .containsAnyOf("zero", "second", "six")
                .anySatisfy(l -> assertThat(l.length()).isLessThan(6));
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "first", "second");
        assertThat(set).hasSize(2)
                .startsWith("first")
                .containsAnyOf("zero", "second", "six")
                .isNotNull()
                .allMatch(e -> e.length() < 12);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "first", "second");
        assertThat(map).containsValues(0,1)
                .doesNotContainKey("fourth")
                .containsEntry("second", 1)
                .doesNotContainEntry("first", 2);
    }
}