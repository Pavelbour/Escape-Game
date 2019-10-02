package com.ocr.pavelrodin.game;

import com.ocr.pavelrodin.config.Config;
import com.ocr.pavelrodin.gameinterface.GameInterface;

import java.util.Scanner;


/**
 * Creates and runs a new game.
 */
public class Game {

    private GameInterface gameInterface;
    private int modeOfGame;
    private int[] number;
    private int[] suggestions;
    private int numberOfDigits;
    private int numberOfAttempts;
    private boolean devmod;
    private boolean isUserWin;

    /**
     * Constructor.
     * Creates a new game.
     * Fetch the parameters from the config file.
     */
    public Game() {
        Config config = new Config();
        gameInterface = new GameInterface();
        numberOfDigits = config.getNumberOfDigits();
        numberOfAttempts = config.getNumberOfAttempts();
        devmod = config.isDevMod();
        number = new int[numberOfDigits];
        suggestions = new int[numberOfDigits];
    }

    /**
     * Generates a key number.
     */
    private void generateNumber() {
        for (int i = 0; i < numberOfDigits; i++) {
            number[i] = (int) Math.round(Math.random() * 9);
        }
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
     * Displays the main menu and selects a mode of game.
     */
    public void startNewGame () {
        String[] menuItems = {"Challenger", "Défenseur", "Duel"};
        modeOfGame = gameInterface.displayMenu("Choisissez le mode de jeu", menuItems);
        this.launchGame();
        this.endGame();
    }

    /**
     * Launches a new game depending on the chosen mode.
     */
    private void launchGame () {
        switch (modeOfGame) {
            case 1: isUserWin = this.challenger();
                break;
            case 2: isUserWin = this.defender();
                break;
            case 3: isUserWin = this.duel();
                break;
        }
    }

    /**
     * Displays the menu in the end of the game and selects an option.
     */
    private void endGame () {
        String[] menuItems = {"Encore une partie", "Menu principal", "Quitter"};
        if (isUserWin) {
            System.out.println("Felicitations ! Vous avez gagné !");
        } else {
            System.out.println("Vous avez perdu.");
            gameInterface.displayNumber("La solution est : ", number);
        }
        switch (gameInterface.displayMenu("Choisissez une option", menuItems)) {
            case 1: this.launchGame();
                    break;
            case 2: this.startNewGame();
                    break;
            case 3: return;
        }
    }

    /**
     * Runs a new game in the mode challenger.
     * @return true if the player wins.
     */
    public boolean challenger () {
        this.generateNumber();
        boolean isWin;
        if (devmod) {
            gameInterface.displayNumber("La solution est : ", number);
        }
        for (int i = 0; i < numberOfAttempts; i++){
            isWin = true;
            System.out.println("Tentative numéro " + (i + 1));
            System.out.println("Saisissez " + numberOfDigits + " chifres");
            suggestions = gameInterface.inputNumber(0, 9, numberOfDigits);
            for (int k = 0; k < numberOfDigits; k++) {
                if (suggestions[k] < number[k]){
                    System.out.print("-");
                    isWin = false;
                } else if (suggestions[k] > number[k]) {
                    System.out.print("+");
                    isWin = false;
                } else {
                    System.out.print("=");
                }
            }
            System.out.println("");
            if (isWin) {
                return true;
            }
        }
        return false;
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
        number = gameInterface.inputNumber(0, 9, numberOfDigits);
        for (int i = 0; i < numberOfAttempts; i++) {
            System.out.println("Tentetive numéro : " + (i + 1));
            gameInterface.displayNumber("Combinaision à essayer : ", suggestions);
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
                return false;
            }
        }
        return true;
    }

    /**
     * Runs a new game in the mode duel.
     * @return true if the player wins.
     */
    public boolean duel () {
        boolean isUserTurn = true;
        while (true) {
            if (isUserTurn) {
                if (this.challenger()) {
                    return true;
                } else {
                    isUserTurn = false;
                }
            } else {
                if (!this.defender()) {
                    return false;
                } else {
                    isUserTurn = true;
                }
            }
        }
    }
}
