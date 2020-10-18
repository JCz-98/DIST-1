
package com.corejsf;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Collections;

@Named ("usershare")
@ApplicationScoped
public class UserShared implements Serializable
{
    private ArrayList<String> users = new ArrayList<>();
    private ArrayList<String> usersOnline = new ArrayList<>();
    private ArrayList<String> usersOffline = new ArrayList<>();
    private ArrayList<Messages> messagesList = new ArrayList<>();

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public ArrayList<String> getUsersOnline() {
        return usersOnline;
    }

    public void setUsersOnline(ArrayList<String> usersOnline) {
        this.usersOnline = usersOnline;
    }

    public ArrayList<String> getUsersOffline() {
        return usersOffline;
    }

    public void setUsersOffline(ArrayList<String> usersOffline) {
        this.usersOffline = usersOffline;
    }

    public ArrayList<Messages> getMessages() {
        return messagesList;
    }

    public void setMessages(ArrayList<Messages> messagesList) {
        this.messagesList = messagesList;
    }


    public void addUser(String string)
    {
        users.add(string);
    }

    public void addUserOnline(String string)
    {
        usersOnline.add(string);
    }

    public void removeUserOnline(String string)
    {
        usersOnline.remove(string);
    }

    public void addUserOffline(String string)
    {
        usersOffline.add(string);
    }

    public void removeUserOffline(String string)
    {
        usersOffline.remove(string);
    }

    public void addMessage(Messages msg)
    {
        messagesList.add(msg);
    }

    public void removeMessage(int item)
    {
        messagesList.remove(item);
    }
}