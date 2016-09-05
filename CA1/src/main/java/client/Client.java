/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author danie
 */
public class Client implements Observer {

    public void processInput(String input) {
        if (input.isEmpty()) {
            return;
        }
        String[] inputSplit = input.split(":");

        switch (inputSplit[0]) {
            case "msg":
                //Do stuff
                break;
            case "login":
                //Do stuff
                break;
            case "logout":
                //Do stuff
                break;
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        processInput((String) arg);

    }
}
