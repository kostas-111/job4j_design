package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    HashMap<FileProperty, Path> dublicateFiles = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fp = new FileProperty(Files.size(file), file.getFileName().toString());
        if (dublicateFiles.containsKey(fp)) {
            System.out.println(file.toAbsolutePath());
        } else {
            dublicateFiles.put(fp, file);
        }
        return super.visitFile(file, attributes);
    }
}