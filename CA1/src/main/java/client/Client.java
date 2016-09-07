/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import static java.lang.System.in;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.NativeRegExp.ignoreCase;

/**
 *Mainly authored by Jarmo, implemented according to a StackOverflow post
 * on the topic
 * http://stackoverflow.com/a/33853246
 * @author danie
 */
public class Client extends Thread implements Observer {

    //Client's socket
    private static final Socket clientSocket = null;
    //Output stream
    private static final PrintStream os = null;
    //Input stream
    private static final DataInputStream is = null;

    private static final BufferedReader inputLine = null;
    private static final boolean closed = false;

    private static String host;
    private static int port;
    
    Client(String host, int port) {
    host = this.host;
    port = this.port;
    }
    
    public static void main(String[] args) throws IOException {

        //Sets the parameters, if nothing is given defaults to local and 9000
        
        if (args.length == 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        } else {
            host = "localhost";
            port = 9000;
        }

        Socket socket = new Socket(host, port);
        BufferedReader inp = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        Thread input = new Thread(() -> {
            String MSG;

            try {
                while ((MSG = inp.readLine()) != ignoreCase("STOP")) {
                    System.out.println(MSG);
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        );
        input.start();

        String userName = "User" + socket;
        
        Thread output = new Thread(() -> {
        String MSG;
        try {

            while (!"LOGOUT".equals(MSG = stdIn.readLine())) {
                for (int i = 0; i < MSG.length(); i++) {
                    System.out.print("\b");
                }
                out.write(":receivers:" + MSG);
                out.flush();

            }}
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        

        

    }
@Override
        public void update
        (Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
}
