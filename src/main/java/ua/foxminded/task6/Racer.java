package ua.foxminded.task6;

import java.util.Objects;

public class Racer {
    private String name;
    private String teamName;
    private String abbreviation;

    public Racer(String abbreviation, String name, String teamName) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.teamName = teamName;
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Racer racer = (Racer) o;
        return Objects.equals(name, racer.name) && Objects.equals(teamName, racer.teamName) && Objects.equals(abbreviation, racer.abbreviation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, teamName, abbreviation);
    }
}