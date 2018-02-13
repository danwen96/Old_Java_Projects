
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.mkyong.rmiclient.*;
import com.mkyong.rmiinterface.*;
import com.mkyong.rmiserver.*;



public class Main {

	 public static void main(String[] args) throws RemoteException, InterruptedException, MalformedURLException, NotBoundException{
		//ServerOperation tempo = new ServerOperation();
		ServerOperation.main(args);
	 
		Thread.sleep(4000);
		
		ClientOperation.main(args);
		
		Thread.sleep(400000);
	 }
	
}
