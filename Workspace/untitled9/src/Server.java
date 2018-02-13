import ch.makery.address.model.Pack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    private ArrayList<ConnectionToClient> clientList;
    private LinkedBlockingQueue<Object> messages;
    private ServerSocket serverSocket;

    public Server(int port) {
        clientList = new ArrayList<ConnectionToClient>();
        messages = new LinkedBlockingQueue<Object>();
        try {
            serverSocket = new ServerSocket(port);
        }catch(Exception e)
        {
            System.out.println(e);
        }

        Thread accept = new Thread() {
            public void run(){
                while(true){
                    try{
                        System.out.println("Czekam na socket");
                        Socket s = serverSocket.accept();
                        System.out.println("Dodalem  socket!");
                        clientList.add(new ConnectionToClient(s));
                        if(clientList.size() == 1)
                        {
                            System.out.println("Wyslalem poczatkowy pakiet");
                            Pack tmp = new Pack(0,200,null,false);
                            clientList.get(0).write(tmp);
                        }
                        else if(clientList.size() == 2)
                        {
                            System.out.println("Wyslalem poczatkowy pakiet");
                            Pack tmp = new Pack(0,200,null,true);
                            clientList.get(1).write(tmp);
                        }
                    }
                    catch(IOException e){ e.printStackTrace(); }
                }
            }
        };

        accept.setDaemon(true);
        accept.start();

    }

    private class ConnectionToClient {
        ObjectInputStream in;
        ObjectOutputStream out;
        Socket socket;

        ConnectionToClient(Socket socket) throws IOException {
            this.socket = socket;
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());


            Thread read = new Thread(){
                public void run(){
                    while(true){
                        try{
                            Object obj = in.readObject();
                            System.out.println("Odebralem i wyslalem object.");
                            sendToRest(socket,obj);
                        }
                        catch(Exception e){ System.out.println("Zamknieto socket!");
                            for(int i =0;i<clientList.size();i++)
                                if(clientList.get(i).socket!= socket)
                                    clientList.remove(i);
                            return;
                        }
                    }
                }
            };

            read.setDaemon(true); // terminate when main ends
            read.start();
        }

        public void write(Object obj) {
            try{
                out.writeObject(obj);
            }
            catch(IOException e){ e.printStackTrace(); }
        }
    }

    public void sendToOne(int index, Object message)throws IndexOutOfBoundsException {
        clientList.get(index).write(message);
    }

    public void sendToRest(Socket socket, Object message)throws IndexOutOfBoundsException {
        for(int i =0;i<clientList.size();i++)
            if(clientList.get(i).socket!= socket)
                clientList.get(i).write(message);
}

    public void sendToAll(Object message){
        for(ConnectionToClient client : clientList)
            client.write(message);
    }

}

