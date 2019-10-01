package com.ocr.pavelrodin.gameinterface;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Creates and runs a menu
 */
public class GameInterface {

    /**
     * The user's choice.
     */
    private int choice = 0;

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

    public void displayNumber (String message, int[] number) {
        System.out.print(message);
        for ( long digit : number) {
            System.out.print(digit);
        }
        System.out.println("");
    }

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
                }
            } while (!isResponseValid);
        }
        return responses;
    }
}
