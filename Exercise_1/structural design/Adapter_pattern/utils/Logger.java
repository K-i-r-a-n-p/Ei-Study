package utils;

import java.time.LocalDateTime;

public class Logger {
    public static void logInfo(String message) {
        System.out.println(LocalDateTime.now() + " INFO: " + message);
    }

    public static void logError(String message) {
        System.out.println(LocalDateTime.now() + " ERROR: " + message);
    }
}
