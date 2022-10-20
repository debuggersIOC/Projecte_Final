/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import interfaces.DAOUser;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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

            ServerSocket server = new ServerSocket(8888);

            while (true) {

                System.out.println("Esperant usuari...");
                Socket socket = server.accept();
                System.out.println("Usuari connectat");

                ObjectOutputStream outUser = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inUser = new ObjectInputStream(socket.getInputStream());

                DataInputStream inData = new DataInputStream(socket.getInputStream());
                DataOutputStream outData = new DataOutputStream(socket.getOutputStream());

                /*TODO:         
                - Retornar la informació al client
                - Login
                 */
                switch (inData.readUTF()) {
                    case "login":
                        System.out.println("Es vol fer un login");
                        loginUser("Gerard", "1234");
                        break;
                    case "register":
                        System.out.println("Es vol donar d'alta un usuari");
                        registerUser(inUser, outData);
                        break;
                    default:
                        throw new AssertionError();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void registerUser(ObjectInputStream inUser, DataOutputStream outData) {

        try {
            User u = (User) inUser.readObject();
            DAOUser daoUser = new DAOUserImpl();

            User p = new User();

            p.setId(u.getId());
            p.setUsuari(u.getUsuari());
            p.setPassword(u.getPassword());
            p.setType(u.getType());

            daoUser.register(p);

            outData.writeUTF(u.getUsuari() + " afegida a la base de dades");

        } catch (Exception e) {
            e.getMessage();
        }

    }

    private static boolean loginUser(String user, String pass) throws Exception {
        
        DAOUser daoUser = new DAOUserImpl();
        boolean select = daoUser.select(user, pass);
        System.out.println("ending");
        if(select){
            System.out.println("usuari trobat");
        }else{
            System.out.println("No s'ha trobat l'usuari");
        }
        return select;
        

        
        

    }
}
