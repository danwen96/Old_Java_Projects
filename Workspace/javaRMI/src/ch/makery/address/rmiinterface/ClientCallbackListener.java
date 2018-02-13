package ch.makery.address.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Krzysztof on 09.05.2016.
 */
public interface ClientCallbackListener extends Remote {
    // Metoda implementowana w kliencie ale uruchamiana na serwerze
	int sendToClient(int text) throws RemoteException;
}
