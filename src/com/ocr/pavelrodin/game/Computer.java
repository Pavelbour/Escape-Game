package com.ocr.pavelrodin.game;

import com.ocr.pavelrodin.display.Display;

/**
 * Represents the entity Computer and the AI of the game.
 */
public class Computer extends Player implements ComputerInterface{

    /**
     * Hints.
     */
    private char[] hints = new char[numberOfDigits];

    /**
     * Valid hints.
     */
    private char[] template = {'-', '+', '='};

    /**
     * Constructor. Creates a new entity Computer.
     * @param numberOfDigits the number of digits in the given combination.
     * @param numberOfAttempts the number of attempts.
     * @param display an instance of class Display that displays the interface of the game.
     */
    public Computer (int numberOfDigits, int numberOfAttempts, Display display) {
        super(numberOfDigits, numberOfAttempts, display);
    }

    /**
     * Generates a digit in the closed range [min, max]
     * @param min minimum value
     * @param max maximum value
     * @return a digit in the closed range [min, max]
     */
    private int generateDigit (int min, int max) {
        double f = Math.random()/Math.nextDown(1.0);
        return (int) Math.round(min*(1.0 - f) + max*f);
    }

    /**
     * Runs a new game in the mode defender.
     * @return true if the player wins.
     */
    public boolean defender () {

        for (int i = 0; i < numberOfAttempts; i++) {
            System.out.println("Tentetive numéro : " + (i + 1));
            if (!this.turn()){
                System.out.println("Vous avez perdu.");
                return false;
            } else if (i < numberOfAttempts - 1) {
                inputHints();
            }
        }
        System.out.println("Felicitations ! Vous avez gagné !");
        return true;
    }

    /**
     * Saves the combination given by the player.
     */
    public void inputCombination() {
        System.out.println("Saisissez " + numberOfDigits + " chifres.");
        number = display.inputNumber(0, 9, numberOfDigits);
        for (int i = 0; i < numberOfDigits; i++) {
            suggestions[i] = -1;
            hints[i] = '+';
        }
    }

    /**
     * Saves the hints given by the player.
     */
    public void inputHints() {
        System.out.println("L'ordinateur n'a pas réussi à diviner la combination. Saisissez des indices.");
        hints = display.inputHints(numberOfDigits, template);
    }


    /**
     * Makes an attempt to guess the combination.
     * @return true if the attempt is successful.
     */
    public boolean turn() {
        boolean isWin = false;
        for (int i = 0; i < numberOfDigits; i++) {
            if (hints[i] == '+') {
                suggestions[i] = generateDigit(suggestions[i] + 1, 9);
            } else if (hints[i] == '-') {
                suggestions[i] = generateDigit(0, suggestions[i] - 1);
            }
        }

        display.displayNumber("Combinaison à essayer : ", suggestions);
        for (int i = 0; i < numberOfDigits; i++) {
            if (suggestions[i] != number[i])
                isWin = true;
        }

        return isWin;
    }

}
