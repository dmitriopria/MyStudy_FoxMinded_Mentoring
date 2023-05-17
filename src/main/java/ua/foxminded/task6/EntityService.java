package ua.foxminded.task6;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntityService {
    private List<String> startList;
    private List<String> endList;
    private List<String> abbreviationsList;

    public EntityService(FileReader fileReader) {
        this.startList = fileReader.getStartList();
        this.endList = fileReader.getEndList();
        this.abbreviationsList = fileReader.getAbbreviationsList();
    }

    private Stream<RaceData> generateLapsInfo(List<String> startList, List<String> endList,
                                              List<String> abbreviationList) {
        return parseRacers(abbreviationList).stream().map(racer -> {
            LocalDateTime startTime = parseLapInfo(startList, racer);
            LocalDateTime endTime = parseLapInfo(endList, racer);
            Duration lapDuration = Duration.between(startTime, endTime);
            return new RaceData(startTime, endTime, lapDuration, racer);
        });
    }

    private List<Racer> parseRacers(List<String> abbreviations) {
        Parser parser = new Parser();
        return abbreviations.stream().map(parser::createRacerFromString).collect(Collectors.toList());
    }

    private LocalDateTime parseLapInfo(List<String> list, Racer racer) {
        Parser parser = new Parser();
        return parser.parseTimeDateFromString(list.stream().filter(line -> line.startsWith(racer.getAbbreviation()))
                .findAny().orElseThrow(() -> new NoSuchElementException("No element in start.log")).substring(3));
    }

    public RaceData loadRaceData() {
        List<Racer> racerList = parseRacers(abbreviationsList);
        Stream<RaceData> raceDataStream = generateLapsInfo(startList, endList, abbreviationsList);
        RaceData raceData = new RaceData();
        raceData.setRacerList(racerList);
        raceData.setRaceDataStream(raceDataStream);
        return raceData;
    }
}