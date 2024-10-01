package utils;

import java.time.LocalDateTime;

public class Logger {
    // Logs an informational message
    public static void logInfo(String message) {
        System.out.println(LocalDateTime.now() + " INFO: " + message);
    }

    // Logs an error message
    public static void logError(String message) {
        System.out.println(LocalDateTime.now() + " ERROR: " + message);
    }

    // Logs a warning message
    public static void logWarning(String message) {
        System.out.println(LocalDateTime.now() + " WARNING: " + message);
    }
}
