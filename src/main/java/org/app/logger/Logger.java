package org.app.logger;

import org.app.valuables.Valuable;

public class Logger {
    private static volatile Logger instance;  // Volatile to ensure visibility of changes across threads

    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void logDeposit(Valuable valuable) {
        log(valuable.getName() + " Valuables deposited in deposit box.", "INFO");
    }

    public void logRemoval(Valuable valuable) {
        log(valuable.getName() + " Valuable removed from the deposit.", "INFO");
    }

    public void logEmptyRemoval() {
        log("Attempt to remove from an empty deposit.", "WARN");
    }

    public void logWoodenCoinDiscard() {
        log("Wooden Coin detected, discarded.", "INFO");
    }

    private synchronized void log(String message, String level) {
        System.out.println(level + ": " + message);
    }

    public void log(String s) {
        log(s, "INFO");
    }
}