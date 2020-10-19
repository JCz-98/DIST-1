package com.corejsf;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@Named("usm")
@ApplicationScoped
public class UserSessionManager {

    @Inject
    private ServerData serverData;

    @Inject
    private UserBean user;

    public String login() {

        FacesContext context = FacesContext.getCurrentInstance();

        String name = user.getName();
        String password = user.getPassword();

        if (name.isEmpty() || password.isEmpty()) {

            FacesMessage check1 = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username or Password is empty", "Type username or password");
            context.addMessage(null, check1);
            return null;
        }

        if (!serverData.getUsersCredentials().containsKey(name) || !serverData.getUsersCredentials().get(name).equals(password)) {
            FacesMessage message0 = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Incorrect password for '" + name + "'",
                    "Please type the correct password.");
            context.addMessage(null, message0);
            return null;
        }

        if (!serverData.getOnlineUsers().contains(name) && serverData.getRegisteredUsers().contains(name)) {
            serverData.addOnlineUser(name);

            if (serverData.getOfflineUsers().contains(name))
                serverData.removeOfflineUser(name);
            return "welcome";

        } else if (!serverData.getRegisteredUsers().contains(name)) {
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username '"
                            + name
                            + "' is not registered. Please sign up",
                    "Please sign up.");
            context.addMessage(null, message1);
            return null;

        } else {
            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "A user with the username '"
                            + name
                            + "' is already logged in. Please choose a different username or sign up",
                    "Please choose a different username/sign up.");
            context.addMessage(null, message2);
            return null;
        }

    }


    public String register() {

        FacesContext context = FacesContext.getCurrentInstance();
        String name = user.getName();
        String password = user.getPassword();

        if (name.isEmpty() || password.isEmpty()) {
            FacesMessage check1 = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username or Password is empty", "Type username or password");
            context.addMessage(null, check1);
            return null;

        }

        if (!serverData.getRegisteredUsers().contains(name)) {
            serverData.registerUser(name);
            serverData.addUserCredentials(name, password);
            return "signup";
        } else {
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username '"
                            + name
                            + "' already exists! Please choose a different username",
                    "Please choose a different username.");
            context.addMessage(null, message1);
            return null;
        }
    }

    public String logout() {
        String name = user.getName();

        serverData.removeOnlineUser(name);
        serverData.addOfflineUser(name);
        return "home";
    }

    public String updateUsersLogged() {
        String loggedUsersString = "";
        for (String onlineUser : serverData.getOnlineUsers()) {
            loggedUsersString +=  ">> " + onlineUser + " \n";
        }

        if (loggedUsersString.length() > 0)
            loggedUsersString = loggedUsersString.substring(0, loggedUsersString.length() - 2);
        return loggedUsersString;
    }

    public String updateUsersLogout() {
        String offUsersString = "";
        for (String offlineUser : serverData.getOfflineUsers()) {
            offUsersString += "<< " + offlineUser + " \n";
        }

        if (offUsersString.length() > 0)
            offUsersString = offUsersString.substring(0, offUsersString.length() - 2);
        return offUsersString;
    }

    public ArrayList<String> updateAllUsers() {
        return serverData.getRegisteredUsers();
    }
}
