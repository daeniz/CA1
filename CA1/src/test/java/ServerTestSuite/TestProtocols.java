/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerTestSuite;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import server.Server;

/**
 *
 * @author dennisschmock
 */
public class TestProtocols implements Observer{
   
    
    public TestProtocols() {
        
        
    }
    
    @BeforeClass
    public static void setUpClass() {
         new Thread(new Runnable() {
            @Override
            public void run() {
                String[] args = new String[2];
                args[0] = "localhost";
                args[1] = "7000";
                Server.main(args);
            }
        }).start();
    }
    
    @AfterClass
    public static void tearDownClass() {
        Server.stopServer();
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void login() throws IOException {
        String testName="loginTest";
        DummyObserver dummyClient= new DummyObserver();
        Client.ClientController client = new Client.ClientController(dummyClient,"localhost",7000);
        client.runClient();
        client.login(testName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted");
        }
        assertTrue(dummyClient.MSGRES!=null);
        boolean loginName=false;
        for (String line : dummyClient.received) {
            if (line.equals(testName))loginName=true;
        }
        System.out.println(dummyClient.received.size());
        assertTrue(loginName);
        client.logout();
        
    }
    
    @Test
    public void msg(){
        String testName="msgTest";
        DummyObserver dummyClient= new DummyObserver();
        Client.ClientController client = new Client.ClientController(dummyClient,"localhost",7000);
        client.runClient();
        System.out.println("MSG:Test");
        client.login(testName);
        client.sendMessage(testName+":msg");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted");
        }
        assertTrue(dummyClient.MSGRES!=null);
        boolean msg=false;
        for (String line : dummyClient.received) {
            if (line.equals(testName))msg=true;
        }
        System.out.println(dummyClient.received.size());
        assertTrue(msg);
        client.logout();
    }

    @Override
    public void update (Observable o, Object arg) {
        System.out.println("NOTIFIED");
        
    }
    
    
}   


