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

/**
 *
 * @author danie
 */
public class OutputCreator {

    User user;
    List<User> userList;
    PrintWriter pw;
    
    public OutputCreator(User user,List<User> userList){
        this.user=user;
        this.userList=userList;
    }
            
            
    
    public void doMsg(String msg, String[] users){
        System.out.println("users length: "+users.length);
        if (users.length==1)
        {
            System.out.println("Size of userList: "+userList.size());
            for (User client : userList) {
                doMsgRes(client, user.getUserName(),msg);
            }
        }
        // Insert code to find the right users in the userlist
        //pw.write("msg:" + msg);
    }
    
    
    
    public void userLoggedInMsgRes(User user){
        for (User user1 : userList) {
            if(user1 != user ){
                user1.getPw().println("MSGRES:Server: " + user.getUserName() + " logged in!");
            }
        }
        user.getPw().println("MSGRES:Server:Welcome " + user.getUserName() + "!");
    }
    
    public void userLoggedOutMsgRes(User user){
        for (User user1 : userList) {
            if(user1 != user ){
                user1.getPw().println("MSGRES:Server: " + user.getUserName() + " logged out!");
            }
        }
        user.getPw().println("MSGRES:Server:Godbye " + user.getUserName() + "!");
    }

    public void doMsgRes(User receiver, String msgSender, String msg) {
        System.out.println("Do MSG Response");
        System.out.println("msgRes:" + msgSender + ":" + msg);
        receiver.getPw().println("msgRes:" + msgSender + ":" + msg);
    }
    
    
    public void sendClientList(){
        String clients="CLIENTLIST:";
        boolean first = true;
        for (User client : userList) {
            if (first){
                clients+=client.getUserName();
                first=false;
            }
            else clients+=","+client.getUserName();
        }
        for (User client : userList) {
            client.getPw().println(clients);
        }
    }
}
