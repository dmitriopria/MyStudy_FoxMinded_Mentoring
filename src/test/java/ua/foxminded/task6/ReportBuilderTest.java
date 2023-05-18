package ua.foxminded.task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

class ReportBuilderTest {

    private ReportBuilder reportBuilder;
    private RaceData raceData;

    @BeforeEach
    public void setup() {
        reportBuilder = new ReportBuilder();
        raceData = new RaceData();
    }

    @Test
    void testBuildReport() {
        List<Racer> racerList = createRacerList();
        List<RaceData> raceDataList = createRaceDataList(racerList);

        raceData.setRacerList(racerList);
        raceData.setRaceDataList(raceDataList);

        String report = reportBuilder.buildReport(raceData);

        // Assert the generated report string matches the expected result
        String expectedReport = "1. Sebastian Vettel | FERRARI | 01:30.000\n" +
                "2. Nico Hulkenberg  | RENAULT | 02:31.000\n";
        Assertions.assertEquals(expectedReport, report);
    }

    private List<Racer> createRacerList() {
        List<Racer> racerList = new ArrayList<>();
        racerList.add(new Racer("SVF", "Sebastian Vettel", "FERRARI"));
        racerList.add(new Racer("NHR", "Nico Hulkenberg", "RENAULT"));
        return racerList;
    }

    private List<RaceData> createRaceDataList(List<Racer> racerList) {
        List<RaceData> raceDataList = new ArrayList<>();
        for (int i = 0; i < racerList.size(); i++) {
            Racer racer = racerList.get(i);
            LocalDateTime fastestLapTime = LocalDateTime.of(2018, 5, 5, 0, i + 1, i + 30);
            RaceData raceData = new RaceData(LocalDateTime.MIN, fastestLapTime, Duration.between(LocalTime.MIN, fastestLapTime), racer);
            raceDataList.add(raceData);
        }
        return raceDataList;
    }
}

