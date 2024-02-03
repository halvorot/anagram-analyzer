package org.example;

import org.junit.jupiter.api.Test;

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
}