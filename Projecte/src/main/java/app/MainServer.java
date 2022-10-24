/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import threads.ClientHandler;

/**
 * Clase principal amb la qual s'inicilitza el servidor que esperarà als
 * clients.
 *
 * @author Gerard
 */
public class MainServer {

    private static ArrayList<ClientHandler> loggedUsers = new ArrayList<>();

    public static void main(String[] args) {

        try {

            //Es crea el ServerSocket del servidor al port 8888
            ServerSocket server = new ServerSocket(8888);
            System.out.println("[SERVIDOR INICIAT]");

            while (true) {

                //Espera a que es connecti un usuari i llavors accepta la petició              
                System.out.println("Esperant usuari...");
                Socket socket = server.accept();
                System.out.println("Usuari connectat");

                ClientHandler clientThread = new ClientHandler(socket);
                clientThread.start();
                loggedUsers.add(clientThread);
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
}
