package com.blogspot.mstachniuk.example.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
 
public class MyServerImpl extends UnicastRemoteObject 
          implements MyServerInt {
 
     private static final long serialVersionUID = 1L;
 
     protected MyServerImpl() throws RemoteException {
          super();
     }
 
     @Override
     public String getDescription(String text) 
               throws RemoteException {
          System.out.println("MyServerImpl.getDescription " + 
               text);
          return "getDescription: " + text;
     }
 
}
