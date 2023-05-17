package ua.foxminded.task6;

import java.time.format.DateTimeFormatter;

public class Constants {
    public static final String EMPTY_FILE_NAME = "File name can't be empty";
    public static final String EMPTY_ARGUMENT = "Argument can't be empty or null";
    public static final String ARGUMENT_FORMAT = "Wrong argument format";
    public static final String NO_FILE = "No file";
    public static final String EMPTY_FILE = "File can't be empty";

    public static final DateTimeFormatter DATE_FORMAT_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
    public static final DateTimeFormatter BOARD_TIME_FORMAT_PATTERN = DateTimeFormatter.ofPattern("mm:ss.SSS");

    public static final String RACER_INFO = "^[A-Z]{3}_\\D+_\\D+$";
    public static final String DATE_TIME = "^\\d{4}-\\d{2}-\\d{2}_\\d{2}:\\d{2}:\\d{2}\\.\\d{3}$";
}
