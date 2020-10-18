package com.corejsf;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;

@Named("user") // or @ManagedBean(name="user")
@SessionScoped
public class UserManagement implements Serializable {

    @Inject private UserShared usershared;
    private String name;
    private String userToWrite = name;
    private String sendMsg = "";
    private String receivedMessages = "";
    private int messageToDelete = 1;
    private ArrayList<Integer> msgs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserToWrite() {
        return userToWrite;
    }

    public void setUserToWrite(String userToWrite) {
        this.userToWrite = userToWrite;
    }

    public String getSendMsg() {
        return sendMsg;
    }

    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg;
    }

    public String getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(String receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public int getMessageToDelete() {
        return messageToDelete;
    }

    public void setMessageToDelete(int messageToDelete) {
        this.messageToDelete = messageToDelete;
    }



    public void sendMessage(String message, String user, String receiver)
    {
        if(!message.equals(""))
        {
            Messages msg = new Messages (message, receiver, user, new Date());
            usershared.addMessage(msg);
            sendMsg = "";
        }
    }

    public String receiveMessage(String user)
    {
        String msgstr = "";
        int count = 1;
        FacesContext context = FacesContext.getCurrentInstance();
        for (ListIterator<Messages> listiterator = usershared.getMessages().listIterator(); listiterator.hasNext();)
        {
            Messages messagesnext = listiterator.next();
            if(messagesnext.getReceiver().equals(user))
            {
                msgstr += count + ". " + messagesnext.messageComplete() + "\n";
                msgs.add(usershared.getMessages().indexOf(messagesnext));
                count++;
            }
        }
        receivedMessages = msgstr;
        return msgstr;
    }

    public String receive (String user)
    {
        receiveMessage(user);
        return "welcome";
    }
    public void deleteMessage()
    {
        if(msgs.size() > 0)
            usershared.removeMessage((int)msgs.get(messageToDelete-1));
    }


    public ArrayList<String> getUsersOnline()
    {
        return usershared.getUsersOnline();
    }

    public ArrayList<String> getUsersOffline()
    {
        return usershared.getUsersOffline();
    }

    public ArrayList<String> getUsers()
    {
        return usershared.getUsers();
    }

    public String login()
    {
        FacesContext context = FacesContext.getCurrentInstance();

        if(!usershared.getUsersOnline().contains(name) && usershared.getUsers().contains(name))
        {
            usershared.addUserOnline(name);
            if(usershared.getUsersOffline().contains(name))
                usershared.removeUserOffline(name);
            return "welcome";
        }
        else if (!usershared.getUsers().contains(name))
        {
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username '"
                            + name
                            + "' is not registered Please sign up",
                    "Please sign up.");
            context.addMessage(null, message1);
            return null;
        }
        else
        {
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "A user with the username '"
                            + name
                            + "' is already logged in. Please choose a different username or sign up",
                    "Please choose a different username/sign up.");
            context.addMessage(null, message2);
            return null;
        }

    }

    public String register()
    {
        FacesContext context = FacesContext.getCurrentInstance();

        if(!usershared.getUsers().contains(name))
        {
            usershared.addUser(name);
            return"signup";
        }
        else
        {
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username '"
                            + name
                            + "' already exists! Please choose a different username",
                    "Please choose a different username.");
            context.addMessage(null, message1);
            return null;
        }
    }

    public String logout()
    {
        usershared.removeUserOnline(name);
        usershared.addUserOffline(name);
        return "home";
    }

    public String updateUsersLogged()
    {
        String print = "";
        for(Iterator<String> iterator = usershared.getUsersOnline().iterator(); iterator.hasNext();)
        {
            String nextUser = iterator.next();
            print += nextUser + ", ";
        }

        if(print.length() > 0)
            print = print.substring(0, print.length()-2);
        return print;
    }

    public String updateUsersLogout()
    {
        String print = "";
        for(Iterator<String> iterator = usershared.getUsersOffline().iterator(); iterator.hasNext();)
        {
            String nextUser = iterator.next();
            print += nextUser + ", ";
        }

        if(print.length() > 0)
            print = print.substring(0, print.length()-2);
        return print;
    }

    public ArrayList<String> updateAllUsers(){
        return usershared.getUsers();
    }
}