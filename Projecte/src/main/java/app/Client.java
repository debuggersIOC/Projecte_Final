/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Gerard
 */
public class Client {
    
    public static void main(String[] args) {
        final String HOST = "169.254.224.147";
        Scanner sc = new Scanner(System.in);
    

        try {
            Socket socket = new Socket(HOST, 8888);
            
            ObjectOutputStream outUser = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inUser = new ObjectInputStream(socket.getInputStream()); 
            
            User u = new User();
            
            System.out.println("ID:");
            u.setId(sc.next());
            System.out.println("NOM:");
            u.setUsuari(sc.next());
            System.out.println("PASS:");
            u.setPassword(sc.next());
            System.out.println("ROL:");
            u.setType(sc.next());
            
            outUser.writeObject(u);
            
            String message="";
            
            while (!message.equals("exit")) {  
                message = sc.next();
                System.out.println("Informació enviada!");
            }
            
            System.out.println("Informació enviada");

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
}
