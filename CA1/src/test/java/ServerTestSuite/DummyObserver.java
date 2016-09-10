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

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg.toString());
        MSGRES=arg.toString();
        received.add(arg.toString());
    }
    
}
