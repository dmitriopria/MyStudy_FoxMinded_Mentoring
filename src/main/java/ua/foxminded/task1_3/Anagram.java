package ua.foxminded.task1_3;

public class Anagram {
    public String makeAnagram(String inputString) {
        if (inputString != null) {
            String[] splitOnWords = inputString.split(" ");
            String[] outputWords = new String[splitOnWords.length];
            int i = 0;
            for (String inputWord : splitOnWords) {
                String reverse = reverseOnlyLetters(inputWord);
                outputWords[i] = reverse;
                i++;
            }
            return String.join(" ", outputWords);
        } else
            return null;
    }

    private String reverseOnlyLetters(String word) {
        char[] wordArray = word.toCharArray();
        int leftBorder = 0;
        int rightBorder = wordArray.length - 1;
        while (leftBorder < rightBorder) {
            if (!Character.isAlphabetic(wordArray[leftBorder])) {
                leftBorder++;
            } else if (!Character.isAlphabetic(wordArray[rightBorder])) {
                rightBorder--;
            } else {
                char alphabetic = wordArray[leftBorder];
                wordArray[leftBorder] = wordArray[rightBorder];
                wordArray[rightBorder] = alphabetic;
                leftBorder++;
                rightBorder--;
            }
        }
        return new String(wordArray);
    }
}
