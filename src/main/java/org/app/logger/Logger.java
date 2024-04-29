package org.app.logger;

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

    // Example method to support different levels of logging
    public void log(String message) {
        log(message, "INFO");
    }

    public void error(String message) {
        log(message, "ERROR");
    }

    public void warn(String message) {
        log(message, "WARN");
    }

    // General log method that handles all types of logs
    private synchronized void log(String message, String level) {
        // Synchronization is used here minimally for the actual log output
        System.out.println(level + ": " + message);
    }
}

