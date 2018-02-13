package com.blogspot.mstachniuk.example.rmiserver;
 
import java.rmi.Remote;
import java.rmi.RemoteException;
 
public interface MyServerInt extends Remote {
     String getDescription(String text) throws RemoteException;
}
