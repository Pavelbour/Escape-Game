package com.ocr.pavelrodin.game;

/**
 * Represents the entity Computer and the AI of the game.
 */
public interface ComputerInterface {

    /**
     * Saves the combination given by the player.
     */
    public void inputCombination();

    /**
     * Saves the hints given by the player.
     */
    public void inputHints();

    /**
     * Runs a new game in the mode defender.
     * @return true if the player wins.
     */
    public boolean defender ();

    /**
     * Makes an attempt to guess the combination.
     * @return true if the attempt is successful.
     */
    public boolean turn();
}
