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
            for (User user : userList) {
                doMsgRes(user.getUserName(),msg);
            }
            //pw.write("msg::"+msg);
        }
        // Insert code to find the right users in the userlist
        pw.write("msg:" + msg);
    }

    public void doMsgRes(String msgSender, String msg) {
        System.out.println("Do MSG Response");
        System.out.println("msgRes:" + msgSender + ":" + msg);
        pw.write("msgRes:" + msgSender + ":" + msg);
    }
}
