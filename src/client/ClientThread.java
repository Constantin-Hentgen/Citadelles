package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

// Obligé de faire un thread du client pour qu'il puisse lire en continu les messages envoyés par le serveur
// même quand il est en train de lire l'input du Scanner dans le main

public class ClientThread extends Thread {
    
    private Socket socket;
    private BufferedReader input;

    public ClientThread(Socket s) throws IOException{

        this.socket = s;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Lecture de l'input du serveur
    }

    @Override
    public void run(){

        try {
            
            while (true) {
                String str = input.readLine();
                System.out.println(str); // Affichage de l'input du serveur
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {                
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
