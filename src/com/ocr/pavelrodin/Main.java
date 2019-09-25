package com.ocr.pavelrodin;

import com.ocr.pavelrodin.config.Config;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Config config = new Config();
        System.out.println("Quantité de chifres : " + config.getNumberOfDigits());
    }
}
