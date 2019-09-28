package com.ocr.pavelrodin.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Creates and runs a menu
 */
public class Menu {

    /**
     * The title of the menu.
     */
    private String question;
    /**
     * The items of the menu.
     */
    private String[] choices;
    /**
     * The user's choice.
     */
    private int choice = 0;

    /**
     * Constructor. Creates a menu.
     * @param questionInit The title of the menu.
     * @param choicesInit The items of the menu.
     */
    public Menu (String questionInit, String[] choicesInit) {
        question = questionInit;
        choices = choicesInit;
    }

    /**
     * Displays the menu
     */
    public void displayMenu () {
        System.out.println(question);
        for (int i = 0; i < choices.length; i++) {
            System.out.println(i + 1 + " : " + choices[i]);
        }
    }

    /**
     * Gets the user's input and verify it.
     */
    public void getResponse () {

        Scanner sc = new Scanner(System.in);
        int response;
        boolean isResponseValid = false;
        do {
            try {
                response = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
                response = 0;
            }
            if (response > 0 && response <= choices.length) {
                isResponseValid = true;
            } else {
                System.out.println("Votre réponse est incorrect. Réessayez encore une fois.");
            }
        } while (!isResponseValid);
        choice = response;
    }

    public int getChoice() {
        return choice;
    }
}
