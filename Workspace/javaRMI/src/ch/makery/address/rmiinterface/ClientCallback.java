package ch.makery.address.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Krzysztof on 09.05.2016.
 */
public interface ClientCallback extends Remote {
    // Interfejsy do ustawiania callbacków na serwerze (metod z klienta uruchamianych przez serwer)
    void setPrintOnClientCallback(ClientCallbackListener clientCallbackListener) throws RemoteException;
    ClientCallbackListener getPrintOnClientCallback() throws RemoteException;
}
