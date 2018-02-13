package com.blogspot.mstachniuk.example.rmiserver;


import javax.naming.Context;
import javax.naming.InitialContext;

public class MyServerMain {

    public static void main(String[] args) {
         try {
              MyServerImpl obj1 = new MyServerImpl();
              Context context = new InitialContext();
              context.bind("rmi:MyRemoteObject", obj1);
              System.out.println("Wait...");
         } catch (Exception e) {
              e.printStackTrace();
         }
    }
}
