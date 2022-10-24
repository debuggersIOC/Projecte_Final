/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import interfaces.DAOUser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import model.DAOUserImpl;
import model.User;
import org.json.JSONObject;
import codes.Codes;

/**
 *
 * @author Gerard
 */
public class ClientHandler extends Thread {

    private Socket client;
    private PrintWriter outData;
    private Scanner inData;
    private Codes codes;

    public ClientHandler(Socket client) {
        try {
            this.client = client;
            this.inData = new Scanner(client.getInputStream());
            this.outData = new PrintWriter(client.getOutputStream(), true);
        } catch (Exception e) {
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
                            loginUser(userToLogin, outData);
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
                    default:
                        throw new AssertionError();
                }
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            outData.close();
        }
    }

    /**
     * Es registra un nou usuari a la BBDD
     *
     * @param stringUser l'objecte que ve creat des del client
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
     * contrasenya)
     * @param dataOut la resposta que se li envia al client
     * @param outUser l'objecte Usuari que se li envia al client
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
