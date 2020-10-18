package com.corejsf;

import java.util.Date;

public class Messages {
    private String message;
    private String receiver;
    private String sender;
    private Date date;

    public Messages(String message, String receiver, String sender, Date date) {
        this.date = date;
        this.receiver = receiver;
        this.sender = sender;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String messageComplete() {
        return sender + "@chat" + " to: " + receiver + "[ " + date + " ]  " + message;
    }
}