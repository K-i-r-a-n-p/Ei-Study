package chat;

import utils.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
    private static final int PORT = 12345;  // Server listens on this port
    private static Map<String, ChatRoom> chatRooms = new HashMap<>();
    private static Map<String, ClientHandler> activeUsers = new HashMap<>();  // For direct messaging
    private static ExecutorService pool = Executors.newFixedThreadPool(10);  // Max 10 concurrent clients

    public static void main(String[] args) {
        System.out.println("Server started...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Logger.logInfo("New client connected.");
                ClientHandler clientHandler = new ClientHandler(clientSocket, chatRooms, activeUsers);
                pool.execute(clientHandler);  // Handle each client in a separate thread
            }
        } catch (IOException e) {
            Logger.logError("Server exception: " + e.getMessage());
        }
    }
}
