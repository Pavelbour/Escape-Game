package com.ocr.pavelrodin.game;

import com.ocr.pavelrodin.display.Display;

/**
 * Represents the entity human.
 */
public class Human extends Player implements HumanInterface {

    private boolean devmod;

    /**
     * Constructor. Creates a new entity Human.
     * @param numberOfDigits the number of digits in the given combination.
     * @param numberOfAttempts the number of attempts.
     * @param display an instance of class Display that displays the interface of the game.
     * @param initDevmode if true the game runs in the mode developer.
     */
    public Human (int numberOfDigits, int numberOfAttempts, Display display, boolean initDevmode) {
        super(numberOfDigits, numberOfAttempts, display);
        devmod = initDevmode;
    }

    /**
     * Generates a key number.
     */
    public void generateNumber() {
        for (int i = 0; i < numberOfDigits; i++) {
            number[i] = (int) Math.round(Math.random() * 9);
        }
    }

    /**
     * Runs a new game in the mode challenger.
     * @return true if the player wins.
     */
    public boolean challenger () {
        for (int i = 0; i < numberOfAttempts; i++){
            System.out.println("Tentative numéro " + (i + 1));
            System.out.println("");
            if (this.turn()) {
                System.out.println("Felicitations ! Vous avez gagné !");
                return true;
            }
        }
        System.out.println("Vous avez perdu.");
        display.displayNumber("La solution est : ", number);
        return false;
    }

    /**
     * Makes an attempt to guess the combination.
     * @return true if the attempt is successful.
     */
    public boolean turn() {
        if (devmod) {
            display.displayNumber("La solution est : ", number);
        }
        boolean isWin = true;
        System.out.println("Saisissez " + numberOfDigits + " chifres");
        suggestions = display.inputNumber(0, 9, numberOfDigits);
        for (int i = 0; i < numberOfDigits; i++) {
            if (suggestions[i] < number[i]){
                System.out.print("-");
                isWin = false;
            } else if (suggestions[i] > number[i]) {
                System.out.print("+");
                isWin = false;
            } else {
                System.out.print("=");
            }
        }

        System.out.println("");
        return isWin;

    }
}
