package com.ocr.pavelrodin;

import com.ocr.pavelrodin.game.Game;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        if (game.startNewGame()) {
            System.out.println("Vous avez gagné.");
        } else {
            System.out.println("Vous avez perdu.");
        }
    }
}
