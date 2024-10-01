package singleton;

import utils.Logger;

public class DatabaseConnection {
    // The single instance of the DatabaseConnection class
    private static DatabaseConnection instance;
    private boolean connected;

    // Private constructor to prevent instantiation
    private DatabaseConnection() {
        this.connected = false;
        Logger.logInfo("DatabaseConnection instance created.");
    }

    // Static method to provide access to the single instance
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Method to connect to the database
    public void connect() {
        if (!connected) {
            Logger.logInfo("Connecting to the database...");
            connected = true;
        } else {
            Logger.logWarning("Already connected to the database.");
        }
    }

    // Method to disconnect from the database
    public void disconnect() {
        if (connected) {
            Logger.logInfo("Disconnecting from the database...");
            connected = false;
        } else {
            Logger.logWarning("Already disconnected from the database.");
        }
    }

    // Defensive programming: prevent cloning of singleton instance
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning is not allowed for singleton.");
    }
}
