package com.ocr.pavelrodin;

import com.ocr.pavelrodin.config.Config;
import com.ocr.pavelrodin.menu.Menu;

public class Main {

    public static void main(String[] args) {
        Config config = new Config();
        String[] menuItems = {"Offensive", "Déffenseive", "Duel"};
        Menu mainMenu = new Menu("Choisissez le mode du jeux", menuItems);
        mainMenu.displayMenu();
        mainMenu.getResponse();
        System.out.println("Le choix est : " + mainMenu.getChoice());
    }
}
