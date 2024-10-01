package chat;

import utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private String roomId;
    private List<ClientHandler> clients;
    private List<String> messageHistory;  // To store message history

    public ChatRoom(String roomId) {
        this.roomId = roomId;
        this.clients = new ArrayList<>();
        this.messageHistory = new ArrayList<>();
    }

    // Add a user to the chat room and send message history
    public synchronized void addUser(ClientHandler client) {
        clients.add(client);
        Logger.logInfo(client.getUsername() + " joined chat room " + roomId);
        sendHistory(client);
        broadcast(client.getUsername() + " has joined the room.", client);
        sendActiveUsers(client);  // Send list of active users to the new client
    }

    // Remove a user from the chat room
    public synchronized void removeUser(ClientHandler client) {
        clients.remove(client);
        Logger.logInfo(client.getUsername() + " left chat room " + roomId);
        broadcast(client.getUsername() + " has left the room.", client);
    }

    // Broadcast a message to all users
    public synchronized void broadcast(String message, ClientHandler sender) {
        messageHistory.add(message);  // Add to message history
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
        Logger.logInfo("Message broadcasted in room " + roomId + ": " + message);
    }

    // Send the list of active users in the room to a specific client
    public synchronized void sendActiveUsers(ClientHandler client) {
        StringBuilder activeUsersList = new StringBuilder("Active users in the room: ");
        for (ClientHandler c : clients) {
            activeUsersList.append(c.getUsername()).append(", ");
        }
        client.sendMessage(activeUsersList.toString());
    }

    // Handle private messaging
    public synchronized void privateMessage(String message, String recipient, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client.getUsername().equalsIgnoreCase(recipient)) {
                client.sendMessage("(Private) " + sender.getUsername() + ": " + message);
                sender.sendMessage("(Private) to " + recipient + ": " + message);
                Logger.logInfo("Private message from " + sender.getUsername() + " to " + recipient);
                return;
            }
        }
        sender.sendMessage("User " + recipient + " not found.");
    }

    // Send the message history to the user when they join
    public synchronized void sendHistory(ClientHandler client) {
        client.sendMessage("----- Message History -----");
        for (String msg : messageHistory) {
            client.sendMessage(msg);
        }
        client.sendMessage("--------------------------");
    }
}
