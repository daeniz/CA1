/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jarmo
 */
public class ClientHandler extends Observable implements Runnable {

    private List<User> userList;
    Socket socket;

    //The main logic of this class, takes the connection Socket as a parameter
    public void handleClient(Socket socket) throws IOException {

       
        User user;

        try (Scanner scan = new Scanner(socket.getInputStream())) {
            
            user = new User(null, socket);
            userList.add(user);
            InputInterpreter ii = new InputInterpreter(user, userList);
            this.addObserver(ii);
            String message = "";
            while (socket.isConnected()&&!message.equals("LOGOUT")) {
                message = scan.nextLine();
                setChanged();
                notifyObservers(message);                                       //Observer pattern seems pointless now that I think about it. I'm just quite fond of it.

            }
            
        }
        user.getPw().close();
        socket.close();

    }

    //Adds the user to the ClientList by passing the assumed unique name
    //as the parameter
    public void addUser(String name, Socket socket) {

        //Adding the userName variable to the list of users
        userList.add(new User(name, socket));
    }

    @Override
    public void run() {
        try {
            handleClient(socket);
            System.out.println("Client handled");
        } catch (IOException ex) {
            //Insert logging
        }

    }

    public ClientHandler(Socket socket, List<User> userList) {

        this.socket = socket;
        this.userList = userList;
    }
}
