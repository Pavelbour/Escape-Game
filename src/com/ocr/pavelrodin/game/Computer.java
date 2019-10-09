package com.ocr.pavelrodin.game;

import com.ocr.pavelrodin.display.Display;

/**
 * Represents the entity Computer and the AI of the game.
 */
public class Computer extends Player implements ComputerInterface{

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
        boolean isWin;
        for (int i = 0; i < numberOfDigits; i++) {
            suggestions[i] = generateDigit(0, 9);
        }
        System.out.println("Saisissez " + numberOfDigits + " chifres.");
        number = display.inputNumber(0, 9, numberOfDigits);
        for (int i = 0; i < numberOfAttempts; i++) {
            System.out.println("Tentetive numéro : " + (i + 1));
            display.displayNumber("Combinaision à essayer : ", suggestions);
            isWin = false;
            for (int j = 0; j < numberOfDigits; j++) {
                if (suggestions[j] < number[j]) {
                    isWin = true;
                    suggestions[j] = generateDigit(suggestions[j] + 1, 9);
                } else if (suggestions[j] > number[j]) {
                    isWin = true;
                    suggestions[j] = generateDigit(0, suggestions[j] - 1);
                }
            }
            if (!isWin){
                System.out.println("Vous avez perdu.");
                return false;
            }
        }
        System.out.println("Felicitations ! Vous avez gagné !");
        return true;
    }

}
