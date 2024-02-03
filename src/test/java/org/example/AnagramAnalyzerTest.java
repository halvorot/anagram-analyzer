package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnagramAnalyzerTest {

    @Test
    void should_return_true_when_is_anagram() {
        String a = "anagram";
        String b = "margana";

        boolean isAnagram = AnagramAnalyzer.isAnagram(a, b);

        assertThat(isAnagram).isTrue();
    }

    @Test
    void should_return_false_when_different_frequency_of_letters() {
        String a = "anagramm";
        String b = "marganaa";

        boolean isAnagram = AnagramAnalyzer.isAnagram(a, b);

        assertThat(isAnagram).isFalse();
    }

    @Test
    void should_return_true_when_same_word_different_case() {
        String a = "Hello";
        String b = "hello";

        boolean isAnagram = AnagramAnalyzer.isAnagram(a, b);

        assertThat(isAnagram).isTrue();
    }

    @Test
    void should_get_anagrams_from_list_of_words_with_anagrams() {

        // Arrange
        List<String> words = List.of("akte", "aldri", "alle", "arild", "raild");

        // Act
        List<List<String>> anagrams = AnagramAnalyzer.getAnagrams(words);

        // Assert
        assertThat(anagrams).hasSize(1);
        assertThat(anagrams.get(0)).containsExactly("aldri", "arild", "raild");

    }

    @Test
    void should_get_anagrams_from_list_of_words_with_multiple_anagrams() {

        // Arrange
        List<String> words = List.of("akte", "aldri", "alle", "arild", "lela", "raild");

        // Act
        List<List<String>> anagrams = AnagramAnalyzer.getAnagrams(words);

        // Assert
        assertThat(anagrams)
                .hasSize(2)
                .containsExactlyInAnyOrder(List.of("aldri", "arild", "raild"), List.of("alle", "lela"));

    }

    @Test
    void should_get_anagrams_from_list_of_words_without_anagrams() {

        // Arrange
        List<String> words = List.of("akte", "aldri", "alle", "test");

        // Act
        List<List<String>> anagrams = AnagramAnalyzer.getAnagrams(words);

        // Assert
        assertThat(anagrams).isEmpty();

    }

    @Test
    void should_get_anagrams_from_empty_list() {

        // Arrange
        List<String> words = List.of();

        // Act
        List<List<String>> anagrams = AnagramAnalyzer.getAnagrams(words);

        // Assert
        assertThat(anagrams).isEmpty();

    }
}