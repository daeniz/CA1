/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerTestSuite;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author danie
 */
public class DummyObserver implements Observer {
String MSGRES;
ArrayList<String> received = new ArrayList();

public DummyObserver(){
    //System.out.println(this.getClass().getName());
}
    @Override
    public void update(Observable o, Object arg) {
        //System.out.println(o.getClass().getName());
        if(!o.getClass().getName().equals("Client.Interpreter")){
        
        System.out.println(arg.toString());
        MSGRES=arg.toString();
        received.add(arg.toString());
        
        //}
    }
    }
    
}
