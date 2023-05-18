package ua.foxminded.task6;

public class Main {
    public static void main(String[] args) {

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