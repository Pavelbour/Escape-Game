package com.ocr.pavelrodin.game;

import com.ocr.pavelrodin.config.Config;
import com.ocr.pavelrodin.menu.Menu;

import java.util.Scanner;


/**
 * Creates and runs a new game.
 */
public class Game {

    private int modeOfGame;
    private long[] number;
    private int numberOfDigits;
    private int numberOfAttempts;
    private boolean devmod;

    /**
     * Constructor.
     * Creates a new game.
     * Fetch the parameters from the config file.
     * Display the principal menu and defines a mode of game.
     */
    public Game() {
        Config config = new Config();
        numberOfDigits = config.getNumberOfDigits();
        numberOfAttempts = config.getNumberOfAttempts();
        devmod = config.isDevMod();
        String[] menuItems = {"Challenger", "Défenseur", "Duel"};
        Menu mainMenu = new Menu("Choisissez le mode du jeux", menuItems);
        mainMenu.displayMenu();
        mainMenu.getResponse();
        modeOfGame = mainMenu.getChoice();
    }

    /**
     * Generates a key number.
     */
    public void generateNumber() {
        number = new long[numberOfDigits];
        for (int i = 0; i < numberOfDigits; i++) {
            number[i] = Math.round(Math.random() * 9);
        }
    }

    /**
     * Generates a digit in the closed range [min, max]
     * @param min minimum value
     * @param max maximum value
     * @return a digit in the closed range [min, max]
     */
    public double generateDigit (double min, double max) {
        double f = Math.random()/Math.nextDown(1.0);
        return min*(1.0 - f) + max*f;
    }

    /**
     * Select a mode of game depending on the user's choice.
     * @return True if the user wins.
     */
    public boolean startNewGame () {
        boolean isWin = false;
        switch (modeOfGame) {
            case 1: isWin = this.challenger();
                    break;
        }
        return isWin;
    }

    /**
     * Runs a new game in the mode challenger.
     * @return
     */
    public boolean challenger () {
        this.generateNumber();
        boolean isWin;
        long[] values;
        values = new long[numberOfDigits];
        Scanner sc = new Scanner(System.in);
        if (devmod) {
            System.out.print("La réponse est : ");
            for(int j = 0; j < numberOfDigits; j++) {
                System.out.print(number[j]);
            }
            System.out.println("");
        }
        for (int i = 0; i < numberOfAttempts; i++){
            isWin = true;
            System.out.println("Tentative numéro " + (i + 1));
            System.out.println("Saisissez " + numberOfDigits + " chifres");
            for (int k = 0; k < numberOfDigits; k++) {
                values[k] = sc.nextLong();
            }
            for (int k = 0; k < numberOfDigits; k++) {
                if (values[k] < number[k]){
                    System.out.print("-");
                    isWin = false;
                } else if (values[k] > number[k]) {
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

}
