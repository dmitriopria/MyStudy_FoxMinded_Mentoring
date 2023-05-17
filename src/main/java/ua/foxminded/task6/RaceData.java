package ua.foxminded.task6;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class RaceData implements Comparable<RaceData> {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Duration fastestLap;
    private Racer racer;
    private Stream<RaceData> raceDataStream;
    private List<Racer> racerList;

    public RaceData(LocalDateTime startDateTime, LocalDateTime endDateTime, Duration fastestLap, Racer racer) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.fastestLap = fastestLap;
        this.racer = racer;
    }

    public RaceData() {
    }

    public Racer getRacer() {
        return racer;
    }

    public Duration getFastestLap() {
        return fastestLap;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setRaceDataStream(Stream<RaceData> raceDataStream) {
        this.raceDataStream = raceDataStream;
    }

    public void setRacerList(List<Racer> racerList) {
        this.racerList = racerList;
    }

    public Stream<RaceData> getRaceDataStream() {
        return raceDataStream;
    }

    public List<Racer> getRacerList() {
        return racerList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceData raceData = (RaceData) o;
        return Objects.equals(startDateTime, raceData.startDateTime) && Objects.equals(endDateTime, raceData.endDateTime) && Objects.equals(fastestLap, raceData.fastestLap) && Objects.equals(racer, raceData.racer) && Objects.equals(raceDataStream, raceData.raceDataStream) && Objects.equals(racerList, raceData.racerList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, endDateTime, fastestLap, racer, raceDataStream, racerList);
    }

    @Override
    public int compareTo(RaceData lap) {
        if (lap == null) {
            return -1;
        }

        return this.fastestLap.compareTo(lap.fastestLap);
    }
}