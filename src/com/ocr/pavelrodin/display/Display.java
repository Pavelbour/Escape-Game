package com.ocr.pavelrodin.display;

/**
 * Display the interface of the game and manages the user's input.
 */
public interface Display {
    public int displayMenu (String question, String[] choices);
    public void displayNumber (String message, int[] number);
    public int[] inputNumber (int min, int max, int number);
    public char[] inputHints (int number, char[] template);
}
