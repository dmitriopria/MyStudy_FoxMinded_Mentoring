package ua.foxminded.task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class EntityServiceTest {
    @Mock
    private FileReader fileReader;
    private EntityService entityService;
    private Method parseLapInfo;
    private Method parseRacers;
    private Method generateLapsInfo;

    @BeforeEach
    public void setup() throws NoSuchMethodException {
        MockitoAnnotations.openMocks(this);
        entityService = new EntityService(fileReader);
        parseLapInfo = EntityService.class.getDeclaredMethod("parseLapInfo", List.class, Racer.class);
        parseLapInfo.setAccessible(true);
        parseRacers = EntityService.class.getDeclaredMethod("parseRacers", List.class);
        parseRacers.setAccessible(true);
        generateLapsInfo = EntityService.class.getDeclaredMethod("generateLapsInfo", List.class, List.class, List.class);
        generateLapsInfo.setAccessible(true);
    }

    @Test
    void testLoadRaceData() {
        List<String> abbreviationList = new ArrayList<>();
        abbreviationList.add("SVF_Sebastian Vettel_FERRARI");
        abbreviationList.add("NHR_Nico Hulkenberg_RENAULT");
        when(fileReader.getAbbreviationsList()).thenReturn(abbreviationList);

        List<String> startList = new ArrayList<>();
        startList.add("SVF2018-05-24_12:02:58.917");
        startList.add("NHR2018-05-24_12:02:49.914");
        when(fileReader.getStartList()).thenReturn(startList);

        List<String> endList = new ArrayList<>();
        endList.add("SVF2018-05-24_12:03:58.917");
        endList.add("NHR2018-05-24_12:03:49.914");
        when(fileReader.getEndList()).thenReturn(endList);

        RaceData raceData = entityService.loadRaceData();
        Assertions.assertNotNull(raceData);
        Assertions.assertEquals(2, raceData.getRacerList().size());
        Assertions.assertEquals(2, raceData.getRaceDataList().size());
    }

    @Test
    void testGenerateLapsInfo() throws InvocationTargetException, IllegalAccessException {
        List<String> abbreviationList = new ArrayList<>();
        abbreviationList.add("SVF_Sebastian Vettel_FERRARI");
        abbreviationList.add("NHR_Nico Hulkenberg_RENAULT");
        List<String> startList = new ArrayList<>();
        startList.add("SVF2018-05-24_12:02:58.917");
        startList.add("NHR2018-05-24_12:02:49.914");
        List<String> endList = new ArrayList<>();
        endList.add("SVF2018-05-24_12:03:58.917");
        endList.add("NHR2018-05-24_12:03:49.914");

        List<RaceData> raceDataList = (List<RaceData>) generateLapsInfo.invoke(entityService, startList, endList, abbreviationList);
        Assertions.assertEquals(2, raceDataList.size());

        RaceData raceData1 = raceDataList.get(0);
        Assertions.assertEquals(LocalDateTime.parse("2018-05-24T12:02:58.917"), raceData1.getStartDateTime());
        Assertions.assertEquals(LocalDateTime.parse("2018-05-24T12:03:58.917"), raceData1.getEndDateTime());
        Assertions.assertEquals(Duration.ofMinutes(1), raceData1.getFastestLap());
        Assertions.assertNotNull(raceData1.getRacer());

        RaceData raceData2 = raceDataList.get(1);
        Assertions.assertEquals(LocalDateTime.parse("2018-05-24T12:02:49.914"), raceData2.getStartDateTime());
        Assertions.assertEquals(LocalDateTime.parse("2018-05-24T12:03:49.914"), raceData2.getEndDateTime());
        Assertions.assertEquals(Duration.ofMinutes(1), raceData2.getFastestLap());
        Assertions.assertNotNull(raceData2.getRacer());
    }

    @Test
    void testParseRacers() throws InvocationTargetException, IllegalAccessException {
        List<String> list = new ArrayList<>();
        list.add("SVF_Sebastian Vettel_FERRARI");
        list.add("NHR_Nico Hulkenberg_RENAULT");
        List<Racer> actual = (List<Racer>) parseRacers.invoke(entityService, list);

        List<Racer> expected = new ArrayList<>();
        expected.add(new Racer("SVF", "Sebastian Vettel", "FERRARI"));
        expected.add(new Racer("NHR", "Nico Hulkenberg", "RENAULT"));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testParseLapInfo() throws InvocationTargetException, IllegalAccessException {
        List<String> list = new ArrayList<>();
        list.add("SVF2018-05-24_12:02:58.917");
        list.add("NHR2018-05-24_12:03:49.914");
        Racer racer1 = new Racer("SVF", "Sebastian Vettel", "FERRARI");
        Racer racer2 = new Racer("NHR", "Nico Hulkenberg", "RENAULT");

        LocalDateTime lapTime1 = (LocalDateTime) parseLapInfo.invoke(entityService, list, racer1);
        LocalDateTime lapTime2 = (LocalDateTime) parseLapInfo.invoke(entityService, list, racer2);

        Assertions.assertEquals(LocalDateTime.parse("2018-05-24T12:02:58.917"), lapTime1);
        Assertions.assertEquals(LocalDateTime.parse("2018-05-24T12:03:49.914"), lapTime2);
    }
}


