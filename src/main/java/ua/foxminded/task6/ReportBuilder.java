package ua.foxminded.task6;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Consumer;

public class ReportBuilder {
    public String buildReport(RaceData data) {
        List<Racer> racerList = data.getRacerList();
        List<RaceData> raceDataList = data.getRaceDataList();
        int maxTeamNameLength = racerList.stream().mapToInt(racer -> racer.getTeamName().length()).max().getAsInt();
        int maxRacerNameLength = racerList.stream().mapToInt(racer -> racer.getName().length()).max().getAsInt();

        StringBuilder stageBoardBuilder = new StringBuilder();
        raceDataList.stream().sorted().forEachOrdered(new Consumer<RaceData>() {
            int index = 1;
            @Override
            public void accept(RaceData lap) {
                stageBoardBuilder.append(generateBoard(lap, maxRacerNameLength, maxTeamNameLength, index));
                index++;
            }
        });
        return stageBoardBuilder.toString();
    }

    private String generateBoard(RaceData lap, int maxRacerNameLength, int maxTeamNameLength, int index) {
        StringBuilder racersBoardBuilder = new StringBuilder();
        LocalTime time = LocalTime.ofNanoOfDay(lap.getFastestLap().toNanos());
        String timeOutput = time.format(Constants.BOARD_TIME_FORMAT_PATTERN);
        int spacesName = maxRacerNameLength - lap.getRacer().getName().length();
        int spacesTeam = maxTeamNameLength - lap.getRacer().getTeamName().length();
        if (index > 9) {
            spacesName--;
        }
        String line = String.format("%d. %s%s | %s%s | %s", index, lap.getRacer().getName(), repeatChar(spacesName, ' '),
                lap.getRacer().getTeamName(), repeatChar(spacesTeam, ' '), timeOutput);
        racersBoardBuilder.append(line).append("\n");
        if (index == 15) {
            racersBoardBuilder.append(repeatChar(line.length(), '-')).append("\n");
        }
        return racersBoardBuilder.toString();
    }

    private String repeatChar(int length, char ch) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(ch);
        }
        return builder.toString();
    }
}
