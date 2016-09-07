/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author danie
 */
public class Test {
    public static void main(String[] args) {
        String s = "add";
        System.out.println("calc:"+calc(2,3,"add"));
        
        
        System.out.println("calc:"+calc(1,5,"divide"));
        
    }
    public static double calc(int a, int b, String c){
        double answer = 0;
        if (c.equals("add")) answer = a+b;
        
        return answer;
    }
}
