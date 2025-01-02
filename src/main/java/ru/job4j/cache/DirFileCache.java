package ru.job4j.cache;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String result = null;
        Path filePath = Paths.get(cachingDir, key);
        try {
            byte[] bytes = Files.readAllBytes(filePath);
            result = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + filePath.toAbsolutePath());
        }
        return result;
    }
}
