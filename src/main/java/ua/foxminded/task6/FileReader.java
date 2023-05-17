package ua.foxminded.task6;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {
    private List<String> startList;
    private List<String> endList;
    private List<String> abbreviationsList;

    public FileReader(String startLogFileName, String endLogFileName, String abbrFileName)
            throws IOException, URISyntaxException {
        this.startList = readLines(startLogFileName);
        this.endList = readLines(endLogFileName);
        this.abbreviationsList = readLines(abbrFileName);
    }

    private List<String> readLines(String fileName) throws IOException, URISyntaxException {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException(Constants.EMPTY_FILE_NAME);
        }
        try {
            URI uri = ClassLoader.getSystemResource(fileName).toURI();
            String mainPath = Paths.get(uri).toString();
            Path path = Paths.get(mainPath);
            List<String> lines = Files.lines(path).collect(Collectors.toList());
            if (lines.isEmpty()) {
                throw new IllegalArgumentException(Constants.EMPTY_FILE);
            }
            return lines;
        } catch (NullPointerException e) {
            throw new FileNotFoundException(Constants.NO_FILE);
        }
    }

    public List<String> getStartList() {
        return startList;
    }

    public List<String> getEndList() {
        return endList;
    }

    public List<String> getAbbreviationsList() {
        return abbreviationsList;
    }
}