/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        String command=null;
        User[] receivers = null;
        if (checkInvalidUser(user)) {
            return;
        }
        else if (users.length == 1) {
            if (!users[0].equals("")) {
                receivers=getSingleUser(users[0]);
            }
            else receivers=getAll(filteredUserList(userList));
        } 
        else if (users.length > 1) {
            System.out.println("users.length>1");
            receivers=getMoreUsers(users);
        }
        command = getMsgRes(user.getUserName(),msg);
        executeCommands(command, receivers);

        // Insert code to find the right users in the userlist
        //pw.write("msg:" + msg);
    }
    
    public void executeCommands(String command, User[] users){
        if (command!=null && users!=null){
        for (int i = 0; i < users.length; i++) {
            if (!checkInvalidUser(users[i]) && command!=null) users[i].getPw().println(command);
        }
        }
    }
    
    public User[] getSingleUser(String receiver){
        User[] receiveUser = new User[1];
        for (User client : userList) {
                    if (!checkInvalidUser(client) && client.getUserName().equals(receiver)) {
                        receiveUser[0]=client;
                        return receiveUser;                        
                    }
                }
                Logger.getLogger(Log.LOG_NAME).log(Level.SEVERE, "Trying to send message to none-existing user!");
                return null;
    }
    
    public User[] getMoreUsers(String[] userStrings){
        User[] users = new User[userStrings.length];
        int count=0;
        for (int i = 0; i < users.length; i++) {
                for (User client : userList) {
                    if (!checkInvalidUser(client) && client.getUserName().equals(userStrings[i])) {
                        users[count] = client;
                        count++;
                    }
                }
            }
        return users;
    }
    
    public ArrayList<User> filteredUserList(List<User> list){
        ArrayList<User> filtered = new ArrayList();
        for (User user1 : list) {
            if (user1.getUserName()!=null) filtered.add(user1);
        }
        
        return filtered;
    }
    
    public User[] getAll(List<User> list){
        return list.toArray(new User[0]);
    }
    
    public boolean checkInvalidUser(User user){
        return (user == null || user.getUserName() == null);
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

    public String getMsgRes(String msgSender, String msg) {
        //receiver.getPw().println("MSGRES:" + msgSender + ":" + msg);
        return "MSGRES:" + msgSender + ":" + msg;
    }
    public String getClients(){
        String clients = "CLIENTLIST:";
        boolean first = true;   //Boolean for controlling the , seperator so it only comes between clientnames
        for (User client : userList) {
            if (first && client.getUserName()!=null) {
                clients += client.getUserName();
                first = false;
            } else if (client.getUserName()!=null){
                clients += "," + client.getUserName();
            }
        }
        
        return clients;
    }

    public void sendClientList() {
        String clients= getClients();
        for (User client : userList) {
            //client.getPw().println(clients);
            sendClientMessage(client,clients);
        }
    }

    public void doInvalidInput() {
        Logger.getLogger(Log.LOG_NAME).log(Level.SEVERE, "Trying to send invalid input");
        //sendClientMessage(user,"Input was invalid. Learn to type and try again!");
        //user.getPw().println("Input was invalid. Learn to type and try again!");
    }
    
    
    public void sendClientMessage(User client, String message){
        client.getPw().println(message);
    }
}
