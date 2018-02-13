import java.net.*;
import java.io.*;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Scanner;

import ch.makery.address.model.*;


public class Main{

        public static void main(String[] args) {
            System.out.println("Podaj port dla servera!");
            Scanner odczyt = new Scanner(System.in);
            int port = odczyt.nextInt();

            try {
                System.out.println("Tworze server pod portem: " + port);
                Server server = new Server(port);

                while(true)
                    Thread.sleep(1000000000);
            } catch (Exception e) {
                System.out.println(e);
            }

        }
}