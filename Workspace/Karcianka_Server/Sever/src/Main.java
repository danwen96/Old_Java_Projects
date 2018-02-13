import java.net.*;
import java.io.*;
import java.lang.Thread;
import java.util.ArrayList;

import ch.makery.address.model.*;


public class Main{

        public static void main(String[] args) {
            System.out.println("Hello World!");

            int port = 2002;
            try {

                Server server = new Server(2002);
                while(true)
                Thread.sleep(100000);

            } catch (Exception e) {
                System.out.println(e);
            }

        }


}