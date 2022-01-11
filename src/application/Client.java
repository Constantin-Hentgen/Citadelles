package application;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    private DataOutputStream dto = null;
    private Scanner sc = null;
    private Socket skt = null;

    public Client(String ip, int port){

        try {
            
            sc = new Scanner(System.in);

            skt = new Socket(ip,port);
            System.out.println("Connexion établie");
            System.out.println("Ecrire \"Déconnexion\" pour clore la connexion");
            
            dto = new DataOutputStream(skt.getOutputStream());

        } catch (IOException e) {

            System.out.println(e);
        }        
         
        String str = "";

        while (!str.equals("Déconnexion") || !str.equals("Deconnexion")) {
            
            str = sc.nextLine();

            try {
                
                dto.writeUTF(str);;
            } catch (IOException e) {

                System.out.println(e);
            }
        }

        System.out.println("Connexion fermée");

        try {
            
            dto.close();
            skt.close();
        } catch (IOException e) {
            
            System.out.println(e);
        }

    }
    public static void main(String[] args) {
            
        Client client = new Client("localhost", 6666);
    }
}
