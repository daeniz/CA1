/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class OutputCreator {

    User user;
    List<User> userList;
    PrintWriter pw;

    public OutputCreator(User user, List<User> userList) {
        this.user = user;
        this.userList = userList;
    }

    public void doMsg(String msg, String[] users) {
        if (user == null || user.getUserName() == null) {
            return;
        }
        if (users.length == 1) {
            if (!users[0].equals("")) {
                System.out.println("users[0]:" + users[0] + ".");
                for (User client : userList) {
                    if (client.getUserName().equals(users[0])) {
                        doMsgRes(client, user.getUserName(), msg);
                        return;
                    }
                }
                Logger.getLogger(Log.LOG_NAME).log(Level.SEVERE, "Trying to send message to none-existing user!");
                return;
            }
            for (User client : userList) {
                Logger.getLogger(Log.LOG_NAME).log(Level.INFO, user.getUserName() + "Sending to: " + client.getUserName());
                doMsgRes(client, user.getUserName(), msg);
                //return;
            }

        } else if (users.length > 1) {
            for (int i = 0; i < users.length; i++) {
                for (User client : userList) {
                    if (client.getUserName() != null && client.getUserName().equals(users[i])) {
                        doMsgRes(client, user.getUserName(), msg);
                    }
                }
            }
        }

        // Insert code to find the right users in the userlist
        //pw.write("msg:" + msg);
    }

    public void userLoggedInMsgRes(User user) {
        for (User user1 : userList) {
            if (user1 != user) {
                user1.getPw().println("MSGRES:Server: " + user.getUserName() + " logged in!");
            }
        }
        user.getPw().println("MSGRES:Server:Welcome " + user.getUserName() + "!");
    }

    public void userLoggedOutMsgRes(User user) {
        for (User user1 : userList) {
            if (user1 != user) {
                user1.getPw().println("MSGRES:Server: " + user.getUserName() + " logged out!");
            }
        }
        user.getPw().println("MSGRES:Server:Godbye " + user.getUserName() + "!");
    }

    public void doMsgRes(User receiver, String msgSender, String msg) {
        System.out.println("Do MSG Response");
        System.out.println("MSGRES:" + msgSender + ":" + msg);
        receiver.getPw().println("MSGRES:" + msgSender + ":" + msg);
    }

    public void sendClientList() {
        String clients = "CLIENTLIST:";
        boolean first = true;   //Boolean for controlling the , seperator so it only comes between clientnames
        for (User client : userList) {
            if (first) {
                clients += client.getUserName();
                first = false;
            } else {
                clients += "," + client.getUserName();
            }
        }
        for (User client : userList) {
            client.getPw().println(clients);
        }
    }

    public void doInvalidInput() {
        Logger.getLogger(Log.LOG_NAME).log(Level.SEVERE, "Trying to send invalid input");
        user.getPw().println("Input was invalid. Learn to type and try again!");
    }
}
