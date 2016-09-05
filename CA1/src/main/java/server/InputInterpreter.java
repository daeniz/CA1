/*

    Class used for taking apart the String received from a Client and decifring it.

 */
package server;

import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author daniel
 */
public class InputInterpreter implements Observer{
    OutputCreator oc;
    
    public InputInterpreter(PrintWriter pw){   // This is not elegant, let us debate!
        oc = new OutputCreator(pw);
    }
    
    public void processInput(String input) {
        if (input.isEmpty()) {
            return;
        }
        String[] inputSplit = input.split(":");
        System.out.println("Command read as: "+inputSplit[0]);
        switch (inputSplit[0]) {
            case "MSG":
                System.out.println("Do msg");
                //Do stuff
                break;
            case "LOGIN":
                System.out.println("Do LOGIN");
                //Do stuff
                break;
            case "LOGOUT":
                //Do stuff
                break;
        }

    }
    
    public void doMsg(){
        
    }
    public void doLogin(){
        
    }
        
       

    @Override
    public void update(Observable o, Object arg) {
        processInput((String) arg);

    }
}