/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jarmo
 */
public class User {
    
    private String userName;
    private Socket userSocket;
    private PrintWriter pw;

    public PrintWriter getPw() {
        return pw;
    }

    public void setPw(PrintWriter pw) {
        this.pw = pw;
    }
     
    //Default constructor
    public User() //Why?... Just why?
    {
        this.userName = "";
        this.userSocket = null;
    }
    
    public User(String userName, Socket userSocket) 
    {
        this.userName = userName;
        this.userSocket = userSocket;
        try {
            pw = new PrintWriter(userSocket.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Gets the userName
    public String getUserName() {
        return userName;
    }
    //Gets the user's socket (unique for everyone)
    public Socket getUserSocket() {
        return userSocket;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @param userSocket the userSocket to set
     */
    public void setUserSocket(Socket userSocket) {
        this.userSocket = userSocket;
    }
    
}
