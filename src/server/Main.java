package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        
        ArrayList<ServerThread> threadList = new ArrayList<>(); // Liste contenant tous les threads du serveur
        try (ServerSocket serverSocket = new ServerSocket(6666)) {

            while (true) {
                Socket socket = serverSocket.accept(); // Etablir connexion entre le serveur et le client sur le socket
                
                ServerThread serverThread = new ServerThread(socket, threadList); // Création du thread

                threadList.add(serverThread); // Ajout du thread à la liste des threads
                serverThread.start(); // Lancement du thread
            }
        } catch (Exception e) {
            System.out.println("Erreur dans le main : "+e.getStackTrace());
        }
    }
}
