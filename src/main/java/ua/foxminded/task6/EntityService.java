package ua.foxminded.task6;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class EntityService {
    private FileReader fileReader;
    private Parser parser;

    public EntityService(FileReader fileReader) {
        this.fileReader = fileReader;
        parser = new Parser();
    }

    public RaceData loadRaceData() {
        List<Racer> racerList = parseRacers(fileReader.getAbbreviationsList());
        List<RaceData> raceDataStream =
                generateLapsInfo(fileReader.getStartList(), fileReader.getEndList(), fileReader.getAbbreviationsList());
        RaceData raceData = new RaceData();
        raceData.setRacerList(racerList);
        raceData.setRaceDataList(raceDataStream);
        return raceData;
    }

    private List<RaceData> generateLapsInfo(List<String> startList, List<String> endList,
                                            List<String> abbreviationList) {
        return parseRacers(abbreviationList).stream().map(racer -> {
            LocalDateTime startTime = parseLapInfo(startList, racer);
            LocalDateTime endTime = parseLapInfo(endList, racer);
            Duration lapDuration = Duration.between(startTime, endTime);
            return new RaceData(startTime, endTime, lapDuration, racer);
        }).collect(Collectors.toList());
    }

    private List<Racer> parseRacers(List<String> abbreviations) {
        return abbreviations.stream().map(parser::createRacerFromString).collect(Collectors.toList());
    }

    private LocalDateTime parseLapInfo(List<String> list, Racer racer) {
        return parser.parseTimeDateFromString(list.stream().filter(line -> line.startsWith(racer.getAbbreviation()))
                .findAny().orElseThrow(() -> new NoSuchElementException("No element in start.log")).substring(3));
    }
}