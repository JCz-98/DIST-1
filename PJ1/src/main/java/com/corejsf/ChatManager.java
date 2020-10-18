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

//    private String name;
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

    public void sendMessage(String message, String user, String receiver) {
        if (!message.isEmpty()) {
            Message msg = new Message(message, receiver, user, new Date());
            serverData.addMessage(msg);
            messageToSend = "";
        }
        else{
            FacesContext context = FacesContext.getCurrentInstance();

            FacesMessage infoMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Message box is empty",
                    "Please type a message.");
            context.addMessage(null, infoMessage);

        }
    }

    public void receiveMessage(String user) {

        String msgstr = "";
        int count = 1;
        FacesContext context = FacesContext.getCurrentInstance();

        for (Message messagesnext : serverData.getMessages()) {
            if (messagesnext.getReceiver().equals(user)) {
                msgstr += count + ". " + messagesnext.displayMessage() + "\n";
                messagesIndexList.add(serverData.getMessages().indexOf(messagesnext));
                count++;
            }
        }
        receivedMessages = msgstr;
    }

    public String receive(String user) {
        receiveMessage(user);
        return "welcome";
    }

    public void deleteMessage() {
        if (messagesIndexList.size() > 0)
            serverData.removeMessage((int) messagesIndexList.get(messageIndexToDelete - 1));
    }

}