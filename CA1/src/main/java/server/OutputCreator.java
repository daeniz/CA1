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
    PrintWriter pw = new PrintWriter(new OutputStream() {  //Insert outputstream
        @Override
        public void write(int b) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    },true);
    
    public void doMsg(String msg, String[] users){
        if (users.length==0){
            pw.write("msg::"+msg);
        }
    pw.write("msg:"+msg);
}
    
    public void doMsgRes(String msgSender, String msg){
        
    pw.write("msgRes:"+msgSender+":"+msg);
}
}
