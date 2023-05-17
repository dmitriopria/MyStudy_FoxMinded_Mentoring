package ua.foxminded.task6;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FileReaderTest {
    private FileReader reader;
    private Method method;

    @BeforeEach
    void initialization() throws NoSuchMethodException, SecurityException, IOException, URISyntaxException {
        String startLogFileName = "start.log";
        String endLogFileName = "end.log";
        String abbrFileName = "abbreviations.txt";
        method = FileReader.class.getDeclaredMethod("readLines", String.class);
        method.setAccessible(true);
        reader = new FileReader(startLogFileName, endLogFileName, abbrFileName);
    }

    @Test
    void shouldReturnListFromFile() throws IOException, IllegalAccessException, InvocationTargetException {
        List<String> expected = (List<String>) method.invoke(reader, "start.log");
        MatcherAssert.assertThat(expected, IsInstanceOf.instanceOf(List.class));
    }

    @Test
    void shouldReturnLengthOfReadedLines() throws IOException, IllegalAccessException, InvocationTargetException {
        int expected = 19;
        List<String> actual = (List<String>) method.invoke(reader, "start.log");
        Assertions.assertEquals(expected, actual.size());
    }

    @Test
    void shouldReturnNonEmptyLines() throws IOException, IllegalAccessException, InvocationTargetException {
        Random random = new Random();
        List<String> list = (List<String>) method.invoke(reader, "start.log");
        String actual = list.get(random.nextInt(18));
        Assertions.assertTrue(StringUtils.isNotBlank(actual));
    }

    @Test
    void shouldReturnNonEmptyLine() throws IOException, IllegalAccessException, InvocationTargetException {
        List<String> list = (List<String>) method.invoke(reader, "start.log");
        String actual = list.get(0);
        String expected = "SVF2018-05-24_12:02:58.917";
        Assertions.assertEquals(actual.length(), expected.length());
    }

    @Test
    void shouldReturnFNFEWhenFileIsNotExsist() {
        assertThrows(InvocationTargetException.class, () -> {
            method.invoke(reader, "hello");
        });
    }

    @Test
    void shouldReturnIllArgExcWhenFileNameIsEmpty() {
        assertThrows(InvocationTargetException.class, () -> {
            method.invoke(reader, "");
        });
    }

    @Test
    void shouldReturnEmptyList() throws IOException, IllegalAccessException, IllegalArgumentException {
        assertThrows(InvocationTargetException.class, () -> {
            method.invoke(reader, "emptyTestFile");
        });
    }
}
