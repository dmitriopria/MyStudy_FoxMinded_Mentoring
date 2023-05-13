package ua.foxminded.task5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.print("What's on your mind: ");
        String userString = userInput.nextLine();

        CharCounter counter = new CacheDecorator(new CharCounterImpl());
        String result = counter.countCharacters(userString);
        System.out.println(result);

        Scanner userInput2 = new Scanner(System.in);
        System.out.print("What's on your mind: ");
        String userString2 = userInput2.nextLine();

        CharCounter counter2 = new CacheDecorator(new CharCounterImpl());
        String result2 = counter2.countCharacters(userString2);
        System.out.println(result2);
    }
}
