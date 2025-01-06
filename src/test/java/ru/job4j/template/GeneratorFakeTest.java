package ru.job4j.template;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class GeneratorFakeTest {

    @Test
    public void whenGetKeyValue() {
        Generator generator = new GeneratorFake();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Konstantin");
        args.put("subject", "you");
        String expected = "I am a Konstantin, Who are you?";
        assertThat(generator.produce(template, args)).isEqualTo(expected);
    }

    @Test
    public void whenTemplateContainWrongKey() {
        Generator generator = new GeneratorFake();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Konstantin");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenMapContainWrongKey() {
        Generator generator = new GeneratorFake();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Konstantin");
        args.put("subject", "you");
        args.put("badKey", "badValue");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }
}