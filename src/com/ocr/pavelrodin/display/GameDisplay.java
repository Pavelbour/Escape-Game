package com.ocr.pavelrodin.display;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Display the interface of the game and manages the user's input.
 */
public class GameDisplay implements Display {

    /**
     * Displays a menu.
     * @param question The title of the menu.
     * @param choices The items of the menu.
     */
    public int displayMenu (String question, String[] choices) {
        System.out.println(question);
        for (int i = 0; i < choices.length; i++) {
            System.out.println(i + 1 + " : " + choices[i]);
        }
        return this.inputNumber(1, choices.length, 1)[0];
    }

    /**
     * Displays the given combination.
     * @param message A message to display.
     * @param number A combination to display.
     */
    public void displayNumber (String message, int[] number) {
        System.out.print(message);
        for ( long digit : number) {
            System.out.print(digit);
        }
        System.out.println("");
    }

    /**
     * Fetches the user's input and verifies it.
     * Display an error message if the value is out of range.
     * @param min The minimum value.
     * @param max The max value.
     * @param number The number of digits in the combination.
     * @return The user's input.
     */
    public int[] inputNumber (int min, int max, int number) {
        Scanner sc = new Scanner(System.in);
        int[] responses = new int[number];
        boolean isResponseValid = true;
        for (int i = 0; i < number; i++){
            do {
                if (!isResponseValid) {
                    System.out.println("Votre entrée est incorrect. Réessayez encore une fois.");
                }
                try {
                    responses[i] = sc.nextInt();
                    if (responses[i] < min || responses [i] > max) {
                        isResponseValid = false;
                    } else {
                        isResponseValid = true;
                    }
                } catch (InputMismatchException e) {
                        isResponseValid = false;
                        sc.next();
                }
            } while (!isResponseValid);
        }
        return responses;
    }

    /**
     * Fetches the user's hints and verifies it.
     * @param number The number of hints.
     * @param template The valid hints.
     * @return The user's hints.
     */
    public char[] inputHints (int number, char[] template) {
        Scanner sc = new Scanner(System.in);
        char[] responses = new char[number];
        boolean isHintsValid = true;
        for (int i = 0; i < number; i++){
            do {
                if (!isHintsValid) {
                    System.out.println("Votre entrée est incorrect. Réessayez encore une fois.");
                }
                try {
                    responses[i] = sc.next().charAt(0);
                    if (!verifyHints(template, responses[i])) {
                        isHintsValid = false;
                    } else {
                        isHintsValid = true;
                    }
                } catch (InputMismatchException e) {
                    isHintsValid = false;
                    sc.next();
                }
            } while (!isHintsValid);
        }
        return responses;
    }

    private boolean verifyHints(char[] template, char hint) {
        for (char h : template) {
            if (hint == h)
                return true;
        }
        return false;
    }
}
