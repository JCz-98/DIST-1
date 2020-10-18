package com.corejsf;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@Named("serverData")
@ApplicationScoped
public class ServerData implements Serializable {

    private ArrayList<String> registeredUsers = new ArrayList<>();
    private ArrayList<String> onlineUsers = new ArrayList<>();
    private ArrayList<String> offlineUsers = new ArrayList<>();
    private ArrayList<Message> messagesList = new ArrayList<>();
    private HashMap<String,String> usersCredentials = new HashMap<String,String>();

    // getters / setters
    public ArrayList<String> getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(ArrayList<String> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public ArrayList<String> getOnlineUsers() {
        return onlineUsers;
    }

    public void setUsersOnline(ArrayList<String> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    public ArrayList<String> getOfflineUsers() {
        return offlineUsers;
    }

    public void setUsersOffline(ArrayList<String> offlineUsers) {
        this.offlineUsers = offlineUsers;
    }

    public ArrayList<Message> getMessages() {
        return messagesList;
    }

    public void setMessages(ArrayList<Message> messagesList) {
        this.messagesList = messagesList;
    }

    public HashMap<String, String> getUsersCredentials() {
        return usersCredentials;
    }

    public void setUsersCredentials(HashMap<String, String> usersCredentials) {
        this.usersCredentials = usersCredentials;
    }

    // add / remove methods
    public void registerUser(String string) {
        registeredUsers.add(string);
    }

    public void addOnlineUser(String string) {
        onlineUsers.add(string);
    }

    public void removeOnlineUser(String string) {
        onlineUsers.remove(string);
    }

    public void addOfflineUser(String string) {
        offlineUsers.add(string);
    }

    public void removeOfflineUser(String string) {
        offlineUsers.remove(string);
    }

    public void addMessage(Message msg) {
        messagesList.add(msg);
    }

    public void removeMessage(int item) {
        messagesList.remove(item);
    }

    public void addUserCredentials(String username, String password){
        usersCredentials.put(username,password);
    }
}