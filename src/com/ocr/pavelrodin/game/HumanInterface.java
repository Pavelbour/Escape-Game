package com.ocr.pavelrodin.game;

/**
 * Represents the entity Human.
 */
public interface HumanInterface {

    /**
     * Makes an attempt to guess the combination.
     * @return true if the attempt is successful.
     */
    public boolean turn();

    /**
     * Runs a new game in the mode challenger.
     * @return true if the player wins.
     */
    public boolean challenger ();

    /**
     * Generates a key number.
     */
    public void generateNumber();

}
