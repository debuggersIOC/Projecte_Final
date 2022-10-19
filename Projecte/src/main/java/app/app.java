/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import interfaces.DAOUser;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import model.DAOUserImpl;
import model.User;

/**
 *
 * @author isard
 */
public class app {
    
    public static void main(String[] args) {
        
        try {
            
            ServerSocket server  = new ServerSocket(8888);
            
            while(true) {

                System.out.println("Esperant usuari...");
                Socket socket = server.accept();
                System.out.println("Usuari connectat");
                
                ObjectOutputStream outUser = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inUser = new ObjectInputStream(socket.getInputStream()); 

                
                /*TODO:
                
                - Llegir la petició entrant del client (amb un switch)
                - Retornar la informació al client
                - Login
                */
                
                //Mostra de com fer un INSERT a la BBDD
                User u = (User) inUser.readObject();               
                User p = new User();

                p.setId(u.getId());
                p.setUsuari(u.getUsuari());
                p.setPassword(u.getPassword());
                p.setType(u.getType());
                
                DAOUser daoUser = new DAOUserImpl();
                daoUser.register(p);
                
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
