/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import com.sun.net.ssl.KeyManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import model.User;

import threads.ClientHandler;

/**
 * Clase principal amb la qual s'inicilitza el servidor que esperarà als
 * clients. Conté un HashMap on s'emagatzemen tots els usuaris conectats.
 *
 * @author Gerard
 */
public class MainServer {
    
    private static String KEY = "C:\\Program Files\\Java\\jre1.8.0_251\\bin\\server_ks";
    private static String PASS ="123123";

    private static HashMap<String, User> loggedUsers = new HashMap<>();

    public MainServer() {
        MainServer.loggedUsers = new HashMap<>();
    }

    public User getLoggedUser(String key) {
        return loggedUsers.get(key);
    }

    public HashMap<String, User> getLoggedUsers() {
        return loggedUsers;
    }

    public void setLoggedUser(String s, User u) {
        loggedUsers.put(s, u);
    }

    public void logoutUser(String s) {
        loggedUsers.remove(s);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException, KeyManagementException {

        try {
            //Es crea el ServerSocket del servidor al port 8888
            System.setProperty("javax.net.ssl.trustStore", KEY);
            SSLContext context = SSLContext.getInstance("TLS");
            
            KeyStore ks = KeyStore.getInstance("jceks");
            ks.load(new FileInputStream(KEY), null);
            javax.net.ssl.KeyManagerFactory kf = javax.net.ssl.KeyManagerFactory.getInstance("SunX509");
            kf.init(ks, PASS.toCharArray());
            
            context.init(kf.getKeyManagers(), null, null);
            
            ServerSocketFactory factory = context.getServerSocketFactory();
            ServerSocket server = factory.createServerSocket(8888);
            
            ((SSLServerSocket) server).setNeedClientAuth(false);
            
            System.out.println("[SERVIDOR INICIAT]");
            while (true) {
                //Espera a que es connecti un usuari i llavors accepta la petició              
                System.out.println("Esperant usuari...");
                SSLSocket socket = (SSLSocket) server.accept();
                System.out.println("Usuari connectat");
                ClientHandler clientThread = new ClientHandler(socket);
                clientThread.start();
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

}
