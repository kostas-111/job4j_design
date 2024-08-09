package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    HashMap<FileProperty, List<Path>> filesMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fp = new FileProperty(Files.size(file), file.getFileName().toString());
        filesMap.computeIfAbsent(fp, k -> new ArrayList<>()).add(file.toAbsolutePath());
        return super.visitFile(file, attributes);
    }

    public void printDuplicates() {
        for (Map.Entry<FileProperty, List<Path>> entry : filesMap.entrySet()) {
            List<Path> paths = entry.getValue();
            if (paths.size() > 1) {
                System.out.println(entry.getKey());
                for (Path path : paths) {
                    System.out.println(path);
                }
            }
        }
    }
}