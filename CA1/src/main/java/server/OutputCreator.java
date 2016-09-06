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

    PrintWriter pw;
    List<User> userList;
    
    public OutputCreator(PrintWriter pw,List<User> userList){
        this.pw=pw;
        this.userList=userList;
    }
            
            
    
    public void doMsg(String msg, String[] users){
        if (users.length==0){
            for (User user : userList) {
                doMsgRes(user.getUserName(),msg);
            }
            //pw.write("msg::"+msg);
        }
        pw.write("msg:" + msg);
    }

    public void doMsgRes(String msgSender, String msg) {
        pw.write("msgRes:" + msgSender + ":" + msg);
    }
}
