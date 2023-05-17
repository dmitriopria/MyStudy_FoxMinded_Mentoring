package ua.foxminded.task6;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {

        String startLogFileName = "start.log";
        String endLogFileName = "end.log";
        String abbrFileName = "abbreviations.txt";

        FileReader fileReader = new FileReader(startLogFileName, endLogFileName, abbrFileName);
        EntityService service = new EntityService(fileReader);
        RaceData data = service.loadRaceData();
        ReportBuilder reportBuilder = new ReportBuilder();
        String result = reportBuilder.buildReport(data);
        System.out.println(result);
    }
}