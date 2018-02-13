package ch.makery.address.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {

    public int helloTo(int index) throws RemoteException;
   // public void printVal() throws RemoteException;
}