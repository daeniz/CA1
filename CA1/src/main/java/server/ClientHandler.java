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

/**
 *
 * @author jarmo
 */
public class ClientHandler extends Observable implements Runnable {

    

    private List<String> clientList;

    //The main logic of this class, takes the connection Socket as a parameter
    public static void handleClient(Socket socket) throws IOException {

        PrintWriter pw; 
        try (Scanner scan = new Scanner(socket.getInputStream())) {
            pw = new PrintWriter(socket.getOutputStream(), true);
            String message = "";
            while (!message.equals("STOP")) {
                message = scan.nextLine();
                //To be implemented later with the connected classes
                //notifyObservers();
                
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

    }
}
