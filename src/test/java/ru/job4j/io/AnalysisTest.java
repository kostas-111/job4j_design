package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void whenTwoLineResult(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("300 10:59:01");
            output.println("500 11:01:02");
            output.println("200 11:02:02");
        }
        File target  = tempDir.resolve("target.csv").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        List<String> result = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::add);
        }
        assertThat("10:57:01;10:59:01;").contains(result.get(0));
        assertThat("11:01:02;11:02:02;").contains(result.get(1));
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void whenOneLineResult(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("500 10:59:01");
            output.println("400 11:01:02");
            output.println("300 11:02:02");
        }
        File target  = tempDir.resolve("target.csv").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        List<String> result = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::add);
        }
        assertThat("10:57:01;11:02:02;").contains(result.get(0));
        assertThat(result.size()).isEqualTo(1);
    }
}