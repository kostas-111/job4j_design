package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(String.valueOf(source)));
                try (BufferedInputStream output = new BufferedInputStream(
                        new FileInputStream(String.valueOf(source)))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkArgs(ArgsName args) {
        Path route = Paths.get(args.get("d"));
        if (!Files.isDirectory(route) && !Files.exists(route)) {
            throw new IllegalArgumentException(String.format("Directory \"%s\" does not exist.", route));
        }
        if (!args.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Invalid extencion format for argument -e. Extension must start with \".\"");
        }
        if (!args.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Invalid extencion format for argument -o. Use '.zip' extension");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName zipDir = ArgsName.of(args);
        zip.checkArgs(zipDir);
        zip.packFiles(
                Search.search(
                        Paths.get(zipDir.get("d")),
                        path -> !path.toFile().getName().endsWith(zipDir.get("e"))),
                Paths.get(zipDir.get("o")).toFile()
        );
    }
}