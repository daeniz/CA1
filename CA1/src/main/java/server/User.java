/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jarmo
 */
public class User {
    
    private String userName;

    
    private int userSocket;
     
    //Default constructor
    public User() 
    {
        this.userName = "";
        this.userSocket = 0;
    }
    
    public User(String userName, int userSocket) 
    {
        this.userName = userName;
        this.userSocket = userSocket;
    }
    //Gets the userName
    public String getUserName() {
        return userName;
    }
    //Gets the user's socket (unique for everyone)
    public int getUserSocket() {
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
    public void setUserSocket(int userSocket) {
        this.userSocket = userSocket;
    }
    
}
