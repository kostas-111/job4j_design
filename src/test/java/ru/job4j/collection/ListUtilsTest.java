package ru.job4j.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 2, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf() {
        Predicate<Integer> filter = num -> num.equals(3);
        ListUtils.removeIf(input, filter);
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void whenRemoveIfInvalidValue() {
        Predicate<Integer> filter = num -> num.equals(5);
        ListUtils.addBefore(input, 1, 2);
        ListUtils.removeIf(input, filter);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenReplaceIf() {
        Predicate<Integer> filter = num -> num.equals(3);
        ListUtils.replaceIf(input, filter, 2);
        assertThat(input).hasSize(2).containsSequence(1, 2);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 3, 5, 7));
        ListUtils.addBefore(input, 1, 2);
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(1).containsSequence(2);
    }
}