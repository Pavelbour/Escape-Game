package com.ocr.pavelrodin.config;

/**
 * Provides the setup stocked in the config file.
 */
public interface ConfigInterface {
    public int getNumberOfDigits();
    public int getNumberOfAttempts();
    public boolean isDevMod();
}
