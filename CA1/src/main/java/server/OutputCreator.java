/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 *
 * @author danie
 */
public class OutputCreator {

    PrintWriter pw;

    public OutputCreator(PrintWriter pw) {
        this.pw = pw;
    }

    public void doMsg(String msg, String[] users) {
        if (users.length == 0) {
            for (User user : userlist) {
                doMsgRes("username", msg);
            }
            //pw.write("msg::"+msg);
        }
        pw.write("msg:" + msg);
    }

    public void doMsgRes(String msgSender, String msg) {
        pw.write("msgRes:" + msgSender + ":" + msg);
    }
}
