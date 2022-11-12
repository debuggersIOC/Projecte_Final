/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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

    public static void main(String[] args) throws ClassNotFoundException {
        final String HOST = "169.254.224.147";
        Scanner sc = new Scanner(System.in);
        try {
            Socket socket = new Socket(HOST, 8888);
            Scanner inData = new Scanner(socket.getInputStream());
            PrintWriter outData = new PrintWriter(socket.getOutputStream(), true);

            System.out.println(inData.nextLine());
            String m = sc.next();
            switch (m) {
                case "login":
                    outData.println(m);
                    System.out.println("Enviant petició...");
                    outData.println("{\"usuari\": \"Pedro\", \"password\":\"1234\"}");
                    System.out.println(inData.nextLine());
                    String message = "";
                    
                    while (!message.equals("exit")) {
                        message = sc.next();
                        outData.println(message);
                        if(message.equals("logout")){
                            try{
                        System.out.println("es vol fer logout");
                    }catch(Exception e){
                        e.getMessage();
                    }finally{
                        socket.close();
                        System.exit(0);
                    }
                        }
                        System.out.println("Informació enviada");
                    }
                    break;
                case "register":
                    System.out.println("Es vol registrar");
                    outData.println("register");

                    outData.println("{\"id\": \"7897\", \"usuari\": \"Gerard\", \"password\":\"1234\", \"type\":\"client\"}");

                    message = "";

                    System.out.println(inData.nextLine());

                    while (!message.equals("exit")) {
                        message = sc.next();
                        outData.println(sc.next());
                    }
                    break;
                    
                case "logout":
                    try{
                        System.out.println("es vol fer logout");
                    }catch(Exception e){
                        e.getMessage();
                    }finally{
                        socket.close();
                    }
                    break;
                default:
                    throw new AssertionError();
            }

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
