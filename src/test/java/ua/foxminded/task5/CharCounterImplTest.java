package ua.foxminded.task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharCounterImplTest {
    private CharCounterImpl charCounterImpl;

    @BeforeEach
    void initialization() {
        charCounterImpl = new CharCounterImpl();
    }

    @Test
    void testEmptyString() {
        String result = charCounterImpl.countCharacters("");
        Assertions.assertEquals("", result);
    }

    @Test
    void testOneLetter() {
        String result = charCounterImpl.countCharacters("q");
        Assertions.assertEquals("\"q\" - 1", result);
    }

    @Test
    void testOneSymbol() {
        String result = charCounterImpl.countCharacters("#");
        Assertions.assertEquals("\"#\" - 1", result);
    }

    @Test
    void testOneNumber() {
        String result = charCounterImpl.countCharacters("1");
        Assertions.assertEquals("\"1\" - 1", result);
    }

    @Test
    void testManySymbols() {
        String result = charCounterImpl.countCharacters("1% ad ER er V q");
        Assertions.assertEquals("\"1\" - 1\n" +
                "\"%\" - 1\n" +
                "\" \" - 5\n" +
                "\"a\" - 1\n" +
                "\"d\" - 1\n" +
                "\"E\" - 1\n" +
                "\"R\" - 1\n" +
                "\"e\" - 1\n" +
                "\"r\" - 1\n" +
                "\"V\" - 1\n" +
                "\"q\" - 1", result);
    }

    @Test
    void testOneSymbolRepeated() {
        String result = charCounterImpl.countCharacters("aaaaaaa");
        Assertions.assertEquals("\"a\" - 7", result);
    }
}
