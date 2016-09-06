/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class Server {       //Very much just the basic start of the Echo-server
    public static void main(String[] args) {
    List<String> list = new CopyOnWriteArrayList();    
    String ip;
    int port;
        if(args.length ==2){
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }
        else return;    // End program or hardcoded values?
        
        ServerSocket ss;
        try {
            //Logic for the logger when the server starts
            Log.setLogFile("logFile.txt", "ServerLog");
            Logger.getLogger(Log.LOG_NAME).log(Level.INFO, "Starting the Server");
            ss = new ServerSocket();
            ss.bind(new InetSocketAddress(ip,port));
            System.out.println("Server started!, listening on "+port+ " "+", bound to "+ ip);  // Rework for logger
        while (true){
        Socket socket = ss.accept();
        System.out.println("New Client Connected!");    //Rework to broadcast
        Logger.getLogger(Log.LOG_NAME).log(Level.INFO, "New client connected"); //Add the connected client's name here
        new Thread(new ClientHandler(socket, list)).start();
        //handleClient(socket);  // Needs multithreading
        }
        } catch (IOException ex) {
            //In case of an error this will log it
            Logger.getLogger(Log.LOG_NAME).log(Level.SEVERE, null, ex);
        }
        finally {
            //Closes the logger connection
        Log.closeLogger();
        }
        
        
    }

    private static void handleClient(Socket socket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
