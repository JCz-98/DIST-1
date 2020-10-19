package com.corejsf;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;

@Named("mchat") // or @ManagedBean(name="user")
@ApplicationScoped
public class ChatManager {

    @Inject
    private ServerData serverData;

    private String recipient = null;
    private String messageToSend = "";
    private String receivedMessages = "";
    private int messageIndexToDelete = 1;
    private ArrayList<Integer> messagesIndexList = new ArrayList<>();

    // getters n setters
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessageToSend() {
        return messageToSend;
    }

    public void setMessageToSend(String messageToSend) {
        this.messageToSend = messageToSend;
    }

    public String getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(String receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public int getMessageIndexToDelete() {
        return messageIndexToDelete;
    }

    public void setMessageIndexToDelete(int messageIndexToDelete) {
        this.messageIndexToDelete = messageIndexToDelete;
    }

    public ArrayList<Integer> getMessagesIndexList() {
        return messagesIndexList;
    }

    public void setMessagesIndexList(ArrayList<Integer> messagesIndexList) {
        this.messagesIndexList = messagesIndexList;
    }

    // chat methods
    public void sendMessage(String message, String user, String receiver) {

        if (!message.isEmpty()) {
            Message msg = new Message(message, receiver, user, new Date());
            serverData.addMessage(msg);
            messageToSend = "";

        } else {
            FacesContext context = FacesContext.getCurrentInstance();

            FacesMessage infoMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Message box is empty",
                    "Please type a message.");
            context.addMessage(null, infoMessage);
        }
    }

    public String receiveMessages(String toUser) {

        String messsagesDisplayString = "";
        int count = 1;
        FacesContext context = FacesContext.getCurrentInstance();
        ArrayList<Message> messagesInServer = serverData.getMessages();

        if (messagesInServer.size() > 0) {

            for (Message messsage : serverData.getMessages()) {

                if (messsage.getReceiver().equals(toUser)) {
                    messsagesDisplayString += count + ". " + messsage.displayMessage() + "\n";
                    messagesIndexList.add(serverData.getMessages().indexOf(messsage));
                    count++;
                }
            }
            receivedMessages = messsagesDisplayString;
            return "welcome";
        }
        else{
            FacesMessage infoMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "There are not new messages for you available in the server",
                    "No new messages.");
            context.addMessage(null, infoMessage);
            return null;
        }
    }

    public void deleteMessage() {
        if (messagesIndexList.size() > 0)
            serverData.removeMessage((int) messagesIndexList.get(messageIndexToDelete - 1));
        else {
            FacesContext context = FacesContext.getCurrentInstance();

            FacesMessage infoMessage = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "There are not messages to delete",
                    "Please check a message.");
            context.addMessage(null, infoMessage);
        }
    }

}