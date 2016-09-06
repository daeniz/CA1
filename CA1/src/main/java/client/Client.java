/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import server.Server;

/**
 *
 * @author danie
 */
public class Client implements Observer {

    static Socket socket;
    private int port;
    private InetAddress serverAddress;
    private Scanner input;
    private PrintWriter output;

    public void connect(String address, int port) throws UnknownHostException, IOException {
        this.port = port;
        serverAddress = InetAddress.getByName(address);
        socket = new Socket(serverAddress, port);
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    public void send(String msg) {
        output.println(msg);
    }

    //Implement the stop method here
    public String receive() throws IOException {
        //This blocks?
        String msg = input.nextLine();
        if (msg.equals("STOP")) {
            socket.close();

        }
        return msg;
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) throws IOException {
        Scanner userInput = new Scanner(socket.getInputStream());
        String ip = "localHost";
        int port = 9000;
        
        if (args.length == 2) {
             ip = args[0];
         port = Integer.parseInt(args[1]);
        }
        
        if (args.length == 1) 
        {
            ip = args[0];
            port = 9000;
        }
        
        try {
        Client client = new Client();
        client.connect(ip, port);
            System.out.println("Please enter your unique Username");
            String userName = userInput.nextLine();
            client.send("LOGIN:" + userName);
        }
        
        catch (IOException ex) {
        
        }
        
    }
}
