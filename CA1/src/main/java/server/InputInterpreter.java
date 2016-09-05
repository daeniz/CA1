/*

    Class used for taking apart the String received from a Client and decifring it.

 */
package server;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author daniel
 */
public class InputInterpreter implements Observer{
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
