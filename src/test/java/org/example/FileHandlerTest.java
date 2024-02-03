package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileHandlerTest {

    @Test
    void should_read_all_lines_in_file() {

        // Arrange
        List<String> expectedWords = List.of("word1", "word2", "word3");

        // Act
        List<String> words = FileHandler.readAllNonEmptyLines("src/test/resources/test.txt");

        // Assert
        assertThat(words).hasSize(3);
        assertThat(words).isEqualTo(expectedWords);

    }

    @Test
    void should_throw_exception_when_file_does_not_exist() {

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> FileHandler.readAllNonEmptyLines("non_existing_path.txt")
        );
        assertThat(exception.getMessage()).isEqualTo("Could not read file");

    }

    @Test
    void should_write_list_of_lists_to_file() throws IOException {

        // Arrange
        List<List<String>> listOfLines = List.of(List.of("test1", "test2", "test3"), List.of("test4", "test5"));
        String filePath = "src/test/resources/test-output.txt";

        // Act
        FileHandler.writeToFile(listOfLines, filePath, " ");

        // Assert
        List<String> lines = Files.readAllLines(Path.of(filePath));
        assertThat(lines).hasSize(2);
        assertThat(lines.get(0)).isEqualTo("test1 test2 test3");
        assertThat(lines.get(1)).isEqualTo("test4 test5");

    }

    @Test
    void should_overwrite_list_of_lists_to_file_when_run_twice_on_same_file() throws IOException {

        // Arrange
        List<List<String>> listOfLines = List.of(List.of("test1", "test2", "test3"), List.of("test4", "test5"));
        String filePath = "src/test/resources/test-output.txt";

        FileHandler.writeToFile(listOfLines, filePath, " ");

        // Act
        FileHandler.writeToFile(listOfLines, filePath, " ");

        // Assert
        List<String> lines = Files.readAllLines(Path.of(filePath));
        assertThat(lines).hasSize(2);
        assertThat(lines.get(0)).isEqualTo("test1 test2 test3");
        assertThat(lines.get(1)).isEqualTo("test4 test5");

    }

    @Test
    void should_write_empty_list_of_lists_to_file() throws IOException {

        // Arrange
        List<List<String>> listOfLines = List.of();
        String filePath = "src/test/resources/test-output.txt";

        // Act
        FileHandler.writeToFile(listOfLines, filePath, " ");

        // Assert
        List<String> lines = Files.readAllLines(Path.of(filePath));
        assertThat(lines).isEmpty();

    }

}