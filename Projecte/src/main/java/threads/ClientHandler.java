/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import app.MainServer;
import interfaces.DAOUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import model.DAOUserImpl;
import model.User;
import org.json.JSONObject;
import java.util.HashMap;

/**
 * Classe fil que gestiona els usuaris conectats.
 * @author Gerard
 */
public class ClientHandler extends Thread {

    private Socket client;
    private PrintWriter outData;
    private Scanner inData;
    private MainServer server = new MainServer();
    private HashMap<String, User> loggedUsers = new HashMap<>();

    public ClientHandler(Socket client) {
        try {
            this.client = client;
            this.inData = new Scanner(client.getInputStream());
            this.outData = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     * Fil que es crea al servidor per allotjar multiples clients.
     */
    @Override
    public void run() {
        String request;
        try {
            /**
             * Depenent la petició que entri del client, es fa un login o un
             * registre al servidor
             *
             * @param request llegeix la petició del servidor
             * @throws AssertionError() en cas de que no s'envii cap petició
             */

            outData.println("Benvingut al servidor: Escull 'login' o 'register'");
            while (inData.hasNext()) {
                request = inData.nextLine();
                switch (request) {
                    case "login":
                        System.out.println("Login");
                        try {
                            String userToLogin = inData.nextLine();
                            User loggedUser = loginUser(userToLogin, outData);
                            server.setLoggedUser(loggedUser.getId(), loggedUser);
                        } catch (Exception e) {
                            outData.println("1_2");
                        }
                        break;
                    case "register":
                        System.out.println("Es vol donar d'alta un usuari");
                        try {
                            String jsonObject = inData.nextLine();
                            registerUserFromJsonString(jsonObject, outData);
                        } catch (Exception e) {
                            outData.println("2_2");
                        }
                        break;
                    case "logout":
                        try {
                            System.out.println("Un usuari es vol deslogejar");
                            outData.println("3_1");
                        } catch (Exception e) {
                            outData.println("3_2");
                        }finally{
                            client.close();
                        }
                        break;
                    default:
                        System.out.println("No s'ha enviat una petició de 'login' o 'register'");
                        throw new AssertionError();
                }
            }
        } catch (IOException e) {
            e.getMessage();
        } finally {
            outData.close();
        }
    }

    /**
     * Es registra un nou usuari a la BBDD
     *
     * @param stringUser l'objecte que ve creat des del client en format JSONstring
     * @param outData la resposta al client
     * @throws IOException en cas de que un usuari ja estigui registrat amb
     * aquella ID
     */
    private static void registerUserFromJsonString(String stringUser, PrintWriter outData) {

        try {

            DAOUser daoUser = new DAOUserImpl();
            JSONObject jsonUser = new JSONObject(stringUser);

            User p = new User();
            p.setId(jsonUser.getString("id"));
            p.setUsuari(jsonUser.getString("usuari"));
            p.setPassword(jsonUser.getString("password"));
            p.setType(jsonUser.getString("type"));

            daoUser.register(p);

            outData.println("2_1");

        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * S'inicia una petició per tal de fer un login a l'aplicació
     *
     * @param user l'usuari que vol fer login (juntament amb el nom i la
     * contrasenya) que venen en format JSONstring
     * @param dataOut la resposta que se li envia al client
     * @return l'usuari que s'ha volgut fer login
     * @throws Exception
     */
    private static User loginUser(String user, PrintWriter dataOut) throws Exception {
        DAOUser daoUser = new DAOUserImpl();
        JSONObject jsonUser = new JSONObject(user);
        User u = daoUser.select(jsonUser.getString("usuari"), jsonUser.getString("password"));
        if (u.getUsuari() == null) {
            dataOut.println("1_2");
        } else {
            dataOut.println("1_1");
        }
        return u;
    }
}
