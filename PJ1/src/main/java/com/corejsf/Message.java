package com.corejsf;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private String message;
    private String sender;
    private String receiver;
    private Date date;

    public Message(String message, String receiver, String sender, Date date) {
        this.date = date;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String displayMessage() {
        return "[" + date + "] <From: " + sender + ", To: " + receiver + "> Msg: " + message;
    }
}