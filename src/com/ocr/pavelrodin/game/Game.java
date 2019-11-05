package com.ocr.pavelrodin.game;

import com.ocr.pavelrodin.display.Display;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Creates and runs a new game.
 */
public class Game {

    /**
     * An instance of the class Computer that represents the entity computer.
     */
    private ComputerInterface computer;
    /**
     * An instance of the class Human that represents the entity human.
     */
    private HumanInterface human;
    /**
     * An instance of the class Displays that displays the interface of the game.
     */
    private Display display;
    /**
     * If true the game runs in the mode developer.
     */
    private int modeOfGame;

    /**
     * Constructor.
     * Creates a new game.
     * Fetch the parameters from the config file.
     * @param initDisplay an instance of the class Display that displays the interface of the game.
     * @param initComputer an instance of the class Computer that represents the entity computer.
     * @param initHuman an instance of the class Human that represents the entity human.
     */
    public Game (Display initDisplay, ComputerInterface initComputer, HumanInterface initHuman) {
        computer = initComputer;
        human = initHuman;
        display = initDisplay;
    }


    /**
     * Displays the main menu and selects a mode of game.
     */
    public void startNewGame () {
        String[] menuItems = {"Challenger", "Défenseur", "Duel"};
        modeOfGame = display.displayMenu("Choisissez le mode de jeu", menuItems);
        this.launchGame();
        this.endGame();
    }

    /**
     * Launches a new game depending on the chosen mode.
     */
    private void launchGame () {
        Logger logger = LogManager.getLogger();
        switch (modeOfGame) {
            case 1: logger.info("A new game was started in mode challenger.");
                    if (human.challenger()) {
                        logger.info("The game is over. The player won.");
                    } else {
                        logger.info("The game is over. The computer won.");
                    }
                    break;
            case 2: logger.info("A new game was started in mode defender.");
                    if (computer.defender()) {
                        logger.info("The game is over. The player won.");
                    } else {
                        logger.info("The game is over. The computer won.");
                    }
                    break;
            case 3: logger.info("A new game was started in mode duel.");
                    if (this.duel()) {
                        logger.info("The game is over. The player won.");
                    } else {
                        logger.info("The game is over. The computer won.");
                    }
                    break;
        }
    }

    /**
     * Displays the menu in the end of the game and selects an option.
     */
    private void endGame () {
        String[] menuItems = {"Encore une partie", "Menu principal", "Quitter"};
        switch (display.displayMenu("Choisissez une option", menuItems)) {
            case 1: this.launchGame();
                    break;
            case 2: this.startNewGame();
                    break;
            case 3: return;
        }
    }

    /**
     * Runs a new game in the mode duel.
     * @return true if the player wins.
     */
    private boolean duel () {
        boolean isUserTurn = true;
        while (true) {
            if (isUserTurn) {
                if (human.challenger()) {
                    return true;
                } else {
                    isUserTurn = false;
                }
            } else {
                if (!computer.defender()) {
                    return false;
                } else {
                    isUserTurn = true;
                }
            }
        }
    }
}
