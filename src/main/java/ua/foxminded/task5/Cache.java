package ua.foxminded.task5;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javafx.util.Pair;

public class Cache {
    private static List<Pair<String, String>> storage = new LinkedList<>();

    public void put(String newInput, String result) {
        for (int i = 0; i < storage.size(); i++)
            if (!Objects.equals(newInput, storage.get(i).getKey())) {
                storage.add(new Pair<>(newInput, result));
            }
    }

    public boolean containsValue(String newInput) {
        for (Pair<String, String> stringStringPair : storage) {
            if (Objects.equals(newInput, stringStringPair.getKey())) {
                return true;
            }
        }
        return false;
    }

    public String get(String newInput) {
        String resultString = "";
        for (Pair<String, String> stringStringPair : storage) {
            if (Objects.equals(newInput, stringStringPair.getKey())) {
                resultString = stringStringPair.getValue();
            }
        }
        return resultString;
    }
}
