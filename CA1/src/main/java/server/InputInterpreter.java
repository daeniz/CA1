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
public class InputInterpreter implements Observer {
    
    private OutputCreator oc;
    private List<User> userList;
    private User user;
    
    public InputInterpreter(User user, List<User> userList) {   // This is not elegant, let us debate!
        oc = new OutputCreator(user, userList);
        this.userList = userList;
        this.user = user;
    }
    
    public void processInput(String input) {
        if (input.isEmpty()) {
            return;
        }
        String[] inputSplit = input.split(":");
        System.out.println("Command read as: " + inputSplit[0]);
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
                logout();
                break;
            default:
                if(user.getUserSocket().isClosed()){
                    logout();
                }
                
        }
        
    }
    
    public void doMsg(String[] inputSplit) {
        //Check: if logged in->cont //Else blow up

        String[] users = inputSplit[1].split(",");  //Split so we have an array of users to send to
        String msg = "";
        if (inputSplit.length>=3 && inputSplit[2] != null && inputSplit.length > 0) {
            msg = inputSplit[2];  // If there is a message...
            oc.doMsg(msg, users);
        }
        else oc.doInvalidInput();
           //Call the OutputCreator with the needed parameters
    }
    
    public void doLogin(String[] inputSplit) {
        if (inputSplit.length <= 1) {
            
            return;
        }
        if (user.getUserName() != null) {
            //if username assigned ignore login with message.
            user.getPw().println("MSGRES:Server:You are already logged in!");
            return;
        }
        for (User user1 : userList) {
            if (user1.getUserName() != null && user1.getUserName().contentEquals(inputSplit[1])) {
                user.getPw().println("MSGRES:Server:Username exists. Try again!");
                //how many tries?
                return;
            }
            
        }
        //Create user
        System.out.println("Created user");
        user.setUserName(inputSplit[1]);
        oc.userLoggedInMsgRes(user);
        //user.getPw().println("Welcome " + user.getUserName() + "!");
        
        oc.sendClientList(); //Maybe this is a candidate for Observer pattern?
        //Send welcome message

        //check if already logged in
    }
    
    @Override
    public void update(Observable o, Object arg) {
        processInput((String) arg);
        
    }
    
    private void logout() {
        oc.userLoggedOutMsgRes(user);
        userList.remove(user);
        oc.sendClientList();
    }
}
