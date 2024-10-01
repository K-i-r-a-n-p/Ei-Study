package main;

import singleton.DatabaseConnection;

public class SingletonPatternDemo {
    public static void main(String[] args) {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        db1.connect();

        // Attempting to get the same instance
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        db2.connect(); // Should log that it's already connected

        db1.disconnect();
        db2.disconnect(); // Should log that it's already disconnected
    }
}
