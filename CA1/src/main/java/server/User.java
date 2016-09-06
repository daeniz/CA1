/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;
import java.util.List;
import java.net.Socket;

/**
 *
 * @author jarmo
 */
public class User {
    
    private String userName;
    private Socket userSocket;
    
     
    //Default constructor
    public User() 
    {
        this.userName = "";
        this.userSocket = null;
    }
    
    public User(String userName, Socket userSocket) 
    {
        this.userName = userName;
        this.userSocket = userSocket;
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
