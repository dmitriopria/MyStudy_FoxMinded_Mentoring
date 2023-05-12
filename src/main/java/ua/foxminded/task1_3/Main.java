package ua.foxminded.task1_3;

public class Main {
    public static void main(String[] args) {
        String inputString = "Hello World!";
        Anagram anagram = new Anagram();
        String stringAnagram = anagram.makeAnagram(inputString);
        System.out.println(stringAnagram);
    }
}
