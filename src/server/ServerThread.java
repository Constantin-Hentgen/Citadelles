package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private Socket socket;
    private ArrayList<ServerThread> threadList;
    private PrintWriter output;

    public ServerThread(Socket socket, ArrayList<ServerThread> threads) {
        this.socket = socket;
        this.threadList = threads;
    }

    @Override
    public void run(){

        try {

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Lecture de l'input du client

            output = new PrintWriter(socket.getOutputStream(), true); // Output à envoyer au client

            while (true) {
                
                String inStr = input.readLine();

                System.out.println(inStr);

                if (inStr.equalsIgnoreCase("exit")) {
                    break;                              // Si le client entre "exit" alors le thread est supp
                }

                printClients(inStr);

            }
            
        } catch (Exception e) {
            System.out.println("Erreur dans le main "+e.getStackTrace());
        }
    }

    private void printClients(String s) {

        for ( ServerThread sT: threadList ){
            sT.output.println(s);        // Envoie l'input reçu à tous les clients de tous les threads
        }
    }
}
