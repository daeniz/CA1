/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jarmo
 */
public class ClientHandler extends Observable implements Runnable {

    

    private List<String> clientList;
    Socket socket;

    //The main logic of this class, takes the connection Socket as a parameter
    public void handleClient(Socket socket) throws IOException {

        PrintWriter pw;
        
        try (Scanner scan = new Scanner(socket.getInputStream())) {
            pw = new PrintWriter(socket.getOutputStream(), true);
            InputInterpreter ii = new InputInterpreter(pw);
            String message = "";
            while (!message.equals("LOGOUT")) {
                message = scan.nextLine();
                
                //To be implemented later with the connected classes
                setChanged();
                notifyObservers(message);                                       //Observer pattern seems pointless now that I think about it. I'm just quite fond of it.
                
            }
        }
        pw.close();
        socket.close();

    }

    //Adds the user to the ClientList by passing the assumed unique name
    //as the parameter
    public void addUser(String name) {
        String userName = name;
        
        //Adding the userName variable to the list of users
        clientList.add(userName);
    }

    @Override
    public void run() {
        try {
            handleClient(socket);
        } catch (IOException ex) {
            //Insert logging
        }

    }
    
    public ClientHandler(Socket socket, List clientList){
        
        this.socket=socket;
    }
}
