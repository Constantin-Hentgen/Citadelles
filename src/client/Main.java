package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        try (Socket socket = new Socket("localhost", 6666)){

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Lecture de l'input du serveur

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true); // Ecriture de l'output pour le serveur

            try (Scanner sc = new Scanner(System.in)) {
                String str;
                String pseudo = "";
                ClientThread clientThread = new ClientThread(socket); // Création du thread client lié au socket pour qu'il puisse lire les messages lui étant destiné
                clientThread.start(); // Lancement du thread client

                // Boucle qui ne s'arrête que lorsque le client input "exit"
                do {
                    
                    if (pseudo.equals("")) {
                        System.out.println("Veuillez entrer votre pseudo :");      // Fonctionnalité inutile mais on peut mettre des pseudos voilà
                        str = sc.nextLine();
                        pseudo = str;
                        output.println(str);
                        if (str.equalsIgnoreCase("exit")) {
                            break;
                        }
                    }
                    else {

                        str = sc.nextLine();
                        output.println(str); // Envoie de l'input vers le serveur
                        if (str.equalsIgnoreCase("exit")) {
                            break;
                        }
                    }

                } while (!str.equalsIgnoreCase("exit"));
            }
            
        } catch (Exception e) {
            System.out.println("Erreur dans la méthode main : "+e.getStackTrace());
        }
    }
}
