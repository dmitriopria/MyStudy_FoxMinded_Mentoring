package ua.foxminded.task6;

import java.time.LocalDateTime;

public class Parser {

    public Racer createRacerFromString(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException(Constants.EMPTY_ARGUMENT);
        }
        if (!string.matches(Constants.RACER_INFO)) {
            throw new IllegalArgumentException(Constants.ARGUMENT_FORMAT);
        }
        String[] params = string.split("_");
        return new Racer(params[0], params[1], params[2]);
    }

    public LocalDateTime parseTimeDateFromString(String dateTime) {
        if (dateTime == null || dateTime.isEmpty()) {
            throw new IllegalArgumentException(Constants.EMPTY_ARGUMENT);
        }
        if (!dateTime.matches(Constants.DATE_TIME)) {
            throw new IllegalArgumentException(Constants.ARGUMENT_FORMAT);
        }
        return LocalDateTime.parse(dateTime, Constants.DATE_FORMAT_PATTERN);
    }
}