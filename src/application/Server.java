package application;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private DataInputStream dti = null;
    private Socket skt = null;
    private ServerSocket srvrskt = null;

    public Server(int port){

        try {
            
            srvrskt = new ServerSocket(port);
            System.out.println("Démarrage du serveur");
            System.out.println("En attente de la connexion d'un client");
            skt = srvrskt.accept();
            System.out.println("Client conencté");

            dti = new DataInputStream(skt.getInputStream());
            String str = "";

            while (!str.equals("Déconnexion") || !str.equals("Deconnexion")) {
                
                try {
                    
                    str = dti.readUTF();

                    System.out.println(str);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }

            skt.close();
            dti.close();
            System.out.println("Connexion fermée");

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        
        Server server = new Server(6666);
    }
}
