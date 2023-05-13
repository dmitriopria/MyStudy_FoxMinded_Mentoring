package ua.foxminded.task5;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class CharCounterImpl implements CharCounter {
    @Override
    public String countCharacters(String newInput) {
        return resultString(createMap(newInput));
    }

    private Map<Character, Integer> createMap(String inputString) {
        Map<Character, Integer> countedCharsMap = new LinkedHashMap<>();
        for (char character : inputString.toCharArray()) {
            Integer count = countedCharsMap.get(character);
            if (count != null) {
                count++;
            } else {
                count = 1;
            }
            countedCharsMap.put(character, count);
        }
        return countedCharsMap;
    }

    private String resultString(Map<Character, Integer> countedCharsMap) {
        StringJoiner resultStringTable = new StringJoiner("\n");
        for (Map.Entry<Character, Integer> entry : countedCharsMap.entrySet()) {
            resultStringTable.add("\"" + entry.getKey() + "\" - " + entry.getValue());
        }
        return resultStringTable.toString();
    }
}
