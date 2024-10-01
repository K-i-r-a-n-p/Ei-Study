package client;

import utils.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            // Get username
            System.out.println(in.readLine());  // Prompt for username
            out.println(scanner.nextLine());

            // Choose between public chat room or private message
            System.out.println(in.readLine());  // Choose between chat room or private message
            out.println(scanner.nextLine());

            // Start a thread to listen for messages from the server
            new Thread(() -> {
                try {
                    String incomingMessage;  // Declare inside the lambda to avoid "effectively final" error
                    while ((incomingMessage = in.readLine()) != null) {
                        System.out.println(incomingMessage);  // Safely print the incoming message
                    }
                } catch (IOException e) {
                    Logger.logError("Error reading from server: " + e.getMessage());
                }
            }).start();

            // Handle sending messages to the server
            String message;
            while (true) {
                message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    out.println("exit");
                    break;
                }
                out.println(message);
            }
        } catch (IOException e) {
            Logger.logError("Error connecting to server: " + e.getMessage());
        }
    }
}
