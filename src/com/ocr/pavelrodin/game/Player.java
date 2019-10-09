package com.ocr.pavelrodin.game;

import com.ocr.pavelrodin.display.Display;

/**
 * Class parent for the classes Computer and Human.
 * Represents the two adversaries in the game. Computer and Human.
 */
abstract class Player {

    /**
     * The given combination.
     */
    protected int[] number;
    /**
     * Suggestions.
     */
    protected int[] suggestions;
    /**
     * The number of digits in the given combination.
     */
    protected int numberOfDigits;
    /**
     * The number of attempts.
     */
    protected int numberOfAttempts;
    /**
     * An instance of the class Display that displays the interface of the game.
     */
    protected Display display;

    /**
     * Constructor.
     * @param initNumberOfDigits the number of digits in the given combination.
     * @param initNumberOfAttempts The number of attempts.
     * @param initDisplay an instance of the class Display that displays the interface of the game.
     */
    Player (int initNumberOfDigits, int initNumberOfAttempts, Display initDisplay) {
        number = new int[initNumberOfDigits];
        suggestions = new int[initNumberOfDigits];
        numberOfDigits = initNumberOfDigits;
        numberOfAttempts = initNumberOfAttempts;
        display = initDisplay;
    }
}


