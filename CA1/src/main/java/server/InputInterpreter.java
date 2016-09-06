/*

    Class used for taking apart the String received from a Client and decifring it.

 */
package server;

import java.io.PrintWriter;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author daniel
 */
public class InputInterpreter implements Observer{
    OutputCreator oc;
    List<User> userList;
    User user;
    
    public InputInterpreter(User user, List<User> userList){   // This is not elegant, let us debate!
        oc = new OutputCreator(user,userList);
        this.userList=userList;
        this.user = user;
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
                doMsg(inputSplit);
                //Do stuff
                break;
            case "LOGIN":
                System.out.println("Do LOGIN");
                doLogin(inputSplit);
                break;
            case "LOGOUT":
                //Do stuff
                break;
        }

    }
    
    public void doMsg(String[] inputSplit){
        //Check: if logged in->cont //Else blow up
        
        String[] users = inputSplit[1].split(",");  //Split so we have an array of users to send to
        String msg = "";
        if (inputSplit[2]!=null && inputSplit.length>0) msg=inputSplit[2];  // If there is a message...
        
        oc.doMsg(msg, users);   //Call the OutputCreator with the needed parameters
    }
    public void doLogin(String[] inputSplit){
        if (user.getUserName()!=null){
            //if username assigned ignore login with message.
            System.out.println("user already logged in");
            return;
        }
        for (User user1 : userList) {
            if(user1.getUserName()!=null && user1.getUserName().contentEquals(inputSplit[1])){
                System.out.println("User already exists");
                //how many tries?
                return;
            }
            
        }
        //Create user
        System.out.println("Created user");
        user.setUserName(inputSplit[1]);
        //Send welcome message
        
        //check if already logged in
    }
        
       

    @Override
    public void update(Observable o, Object arg) {
        processInput((String) arg);

    }
}
