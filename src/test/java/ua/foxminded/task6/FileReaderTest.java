package ua.foxminded.task6;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FileReaderTest {
    private FileReader reader;
    private Method readLines;

    @BeforeEach
    void initialization() throws NoSuchMethodException, SecurityException {
        String startLogFileName = "start.log";
        String endLogFileName = "end.log";
        String abbrFileName = "abbreviations.txt";
        readLines = FileReader.class.getDeclaredMethod("readLines", String.class);
        readLines.setAccessible(true);
        reader = new FileReader(startLogFileName, endLogFileName, abbrFileName);
    }

    @Test
    void shouldReturnListFromFile() throws IllegalAccessException, InvocationTargetException {
        List<String> expected = (List<String>) readLines.invoke(reader, "start.log");
        MatcherAssert.assertThat(expected, IsInstanceOf.instanceOf(List.class));
    }

    @Test
    void shouldReturnLengthOfReadLines() throws IllegalAccessException, InvocationTargetException {
        int expected = 19;
        List<String> actual = (List<String>) readLines.invoke(reader, "start.log");
        Assertions.assertEquals(expected, actual.size());
    }

    @Test
    void shouldReturnNonEmptyLines() throws IllegalAccessException, InvocationTargetException {
        Random random = new Random();
        List<String> list = (List<String>) readLines.invoke(reader, "start.log");
        String actual = list.get(random.nextInt(18));
        Assertions.assertTrue(StringUtils.isNotBlank(actual));
    }

    @Test
    void shouldReturnNonEmptyLine() throws InvocationTargetException, IllegalAccessException {
        List<String> list = (List<String>) readLines.invoke(reader, "start.log");
        String actual = list.get(0);
        String expected = "SVF2018-05-24_12:02:58.917";
        Assertions.assertEquals(actual.length(), expected.length());
    }

    @Test
    void shouldReturnFNFEWhenFileIsNotExist() {
        assertThrows(InvocationTargetException.class, () -> {
            readLines.invoke(reader, "hello");
        });
    }

    @Test
    void shouldReturnIllArgExcWhenFileNameIsEmpty() {
        assertThrows(InvocationTargetException.class, () -> {
            readLines.invoke(reader, "");
        });
    }

    @Test
    void shouldReturnEmptyList() {
        assertThrows(InvocationTargetException.class, () -> {
            readLines.invoke(reader, "emptyTestFile");
        });
    }
}
