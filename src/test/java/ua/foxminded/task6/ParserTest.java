package ua.foxminded.task6;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {
    private Parser parser;

    @BeforeEach
    void initialization() {
        parser = new Parser();
    }

    @Test
    void shouldReturnRacerFromString() {
        Racer actual = parser.createRacerFromString("SVF_Sebastian Vettel_FERRARI");
        String expectedAbbr = "SVF";
        String expectedFullName = "Sebastian Vettel";
        String expectedTeamName = "FERRARI";
        MatcherAssert.assertThat(actual, IsInstanceOf.instanceOf(Racer.class));

        Assertions.assertEquals(actual.getAbbreviation(), expectedAbbr);
        Assertions.assertEquals(actual.getName(), expectedFullName);
        Assertions.assertEquals(actual.getTeamName(), expectedTeamName);
    }

    @Test
    void shouldReturnIllArgExceptionWhenArgumentIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.createRacerFromString("");
        });
    }

    @Test
    void shouldReturnIllArgExceptionWhenArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.createRacerFromString(null);
        });
    }

    @Test
    void shouldReturnIllArgExceptionWhenArgumentHaveWrongFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.createRacerFromString("aa");
        });
    }

    @Test
    void shouldReturnLocalDateTime() {
        LocalDateTime actual = parser.parseTimeDateFromString("2018-05-24_12:04:03.332");
        MatcherAssert.assertThat(actual, IsInstanceOf.instanceOf(LocalDateTime.class));
    }

    @Test
    void shouldReturnIllArgExceptionWhenArgIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.createRacerFromString("");
        });
    }

    @Test
    void shouldReturnIllArgExceptionWhenArgIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.createRacerFromString(null);
        });
    }

    @Test
    void shouldReturnIllArgExceptionWhenTimeFormatWrong() {
        assertThrows(IllegalArgumentException.class, () -> {
            parser.createRacerFromString("dd");
        });
    }
}
