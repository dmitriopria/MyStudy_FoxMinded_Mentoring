package ua.foxminded.task1_3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AnagramTest {
    Anagram anagram = new Anagram();

    @Test
    void test_makeAnagram_returnNull_whenNull() {
        assertNull(anagram.makeAnagram(null));
    }

    @Test
    void test_makeAnagram_returnEmptyString_whenEmptyInput() {
        assertEquals("", anagram.makeAnagram(""));
    }

    @Test
    void test_makeAnagram_returnCharacter_whenCharacter() {
        assertEquals("%", anagram.makeAnagram("%"));
    }

    @Test
    void test_makeAnagram_returnRepeatedLetters_whenRepeatedLetters() {
        assertEquals("aaa", anagram.makeAnagram("aaa"));
    }

    @Test
    void test_makeAnagram_returnDifferentLetterCases_whenDifferentLetterCases() {
        assertEquals("AaAAaa", anagram.makeAnagram("aaAAaA"));
    }

    @Test
    void test_makeAnagram_returnOneWord_whenOneWord() {
        assertEquals("amiD", anagram.makeAnagram("Dima"));
    }

    @Test
    void test_makeAnagram_returnSameSymbols_whenOnlySymbols() {
        assertEquals("!@#", anagram.makeAnagram("!@#"));
    }

    @Test
    void test_makeAnagram_returnExpression_whenExpression() {
        assertEquals("olleH! yM eman si amiD!",
                anagram.makeAnagram("Hello! My name is Dima!"));
    }
}
