package chat;

import utils.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Map<String, ChatRoom> chatRooms;
    private Map<String, ClientHandler> activeUsers;  // To handle direct messaging
    private String username;
    private ChatRoom chatRoom;

    public ClientHandler(Socket clientSocket, Map<String, ChatRoom> chatRooms, Map<String, ClientHandler> activeUsers) throws IOException {
        this.clientSocket = clientSocket;
        this.chatRooms = chatRooms;
        this.activeUsers = activeUsers;
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            // Get username
            out.println("Enter your username: ");
            this.username = in.readLine();
 
            
            // Add the user to activeUsers map for private messaging
            synchronized (activeUsers) {
                activeUsers.put(username, this);
            }

            // Ask the user whether to join a room or send private messages
            clientChoice();

        } catch (IOException e) {
            Logger.logError("Client communication error: " + e.getMessage());
        } finally {
            try {
                // Remove user from activeUsers and close socket
                synchronized (activeUsers) {
                    activeUsers.remove(username);
                }
                clientSocket.close();
            } catch (IOException e) {
                Logger.logError("Error closing client socket: " + e.getMessage());
            }
        }
    }


    private void clientChoice() throws IOException {
        out.println("Choose an option: 1. Join a chat room , 2. Send private messages");
    
        String choice = in.readLine();

        if (choice.equals("1")) {
            handleChatRoom();
        } else if (choice.equals("2")) {
            handlePrivateMessaging();
        } else {
            out.println("Invalid choice. Please try again.");
            clientChoice();
        }
    }


    private void handleChatRoom() throws IOException {
        out.println("Enter Chat Room ID: ");
        String roomId = in.readLine();

        // Join the chat room
        synchronized (chatRooms) {
            chatRoom = chatRooms.getOrDefault(roomId, new ChatRoom(roomId));
            chatRooms.put(roomId, chatRoom);
        }
        chatRoom.addUser(this);

        // Handle incoming messages in the chat room
        String message;
        while ((message = in.readLine()) != null) {
            if (message.equalsIgnoreCase("exit")) {
                chatRoom.removeUser(this);
                break;
            } else if (message.equalsIgnoreCase("/users")) {  // Show active users in the room
                chatRoom.sendActiveUsers(this);
            } else {
                chatRoom.broadcast(username + ": " + message, this);
            }
        }
    }

    private void handlePrivateMessaging() throws IOException {
        out.println("Enter the username of the recipient: ");
        String recipient = in.readLine();

        ClientHandler recipientHandler = activeUsers.get(recipient);
        if (recipientHandler != null) {
            out.println("You can now send private messages to " + recipient + ". Type 'exit' to stop.");
            String message;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                recipientHandler.sendMessage("(Private) " + username + ": " + message);
                out.println("(Private) to " + recipient + ": " + message);
                Logger.logInfo("Private message from " + username + " to " + recipient);
            }
        } else {
            out.println("User " + recipient + " is not available.");
            clientChoice();
        }
    }

    // Send a message to this client
    public void sendMessage(String message) {
        out.println(message);
    }

    // Get the username of this client
    public String getUsername() {
        return username;
    }
}
