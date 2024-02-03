package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileHandler {

    public static List<String> readAllNonEmptyLines(final String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath)).stream().filter(word -> !word.isEmpty()).toList();
        } catch (IOException exception) {
            throw new IllegalArgumentException("Could not read file", exception);
        }
    }

    public static void writeToFile(final List<List<String>> listOfLines, final String filePath, final String elementSeparator) {
        try {
            Path path = Path.of(filePath);
            Files.deleteIfExists(path);
            Files.write(
                    path,
                    listOfLines.stream()
                            .map(subList -> String.join(elementSeparator, subList))
                            .toList(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (IOException exception) {
            throw new IllegalArgumentException("Could not write to file", exception);
        }
    }

}
