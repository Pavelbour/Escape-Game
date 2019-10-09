package com.ocr.pavelrodin;

import com.ocr.pavelrodin.config.Config;
import com.ocr.pavelrodin.config.ConfigInterface;
import com.ocr.pavelrodin.display.Display;
import com.ocr.pavelrodin.display.GameDisplay;
import com.ocr.pavelrodin.game.*;

public class Main {

    public static void main(String[] args) {
        ConfigInterface config = new Config();
        Display display = new GameDisplay();
        ComputerInterface computer = new Computer(config.getNumberOfDigits(), config.getNumberOfAttempts(), display);
        HumanInterface human = new Human(config.getNumberOfDigits(), config.getNumberOfAttempts(), display, config.isDevMod());
        Game game = new Game(display, computer, human);
        game.startNewGame();
    }
}
