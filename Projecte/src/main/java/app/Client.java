/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyStore;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import model.User;

/**
 *
 * @author Gerard
 */
public class Client {

    private static String KEY = "C:\\Program Files\\Java\\jre1.8.0_251\\bin\\client_ks";
    private static String PASS = "456456";
    final String HOST = "169.254.224.147";

    public static void main(String[] args) throws ClassNotFoundException, Exception {

        System.setProperty("javax.net.ssl.trustStore", KEY);
        System.setProperty("javax.net.debug", "ssl,handshake");

        Scanner sc = new Scanner(System.in);
        try {
            Client client = new Client();
            Socket socket = client.certificate();
            Scanner inData = new Scanner(socket.getInputStream());
            PrintWriter outData = new PrintWriter(socket.getOutputStream(), true);
            System.out.println(inData.nextLine());
            String m = sc.next();
            switch (m) {
                case "loginUser":
                    outData.println(m);
                    System.out.println("Enviant petició...");
                    outData.println("{\"id\": \"7897\", \"usuari\": \"Gerard\", \"password\":\"1234\", \"type\":\"client\", \"valoracio\":\"5\", \"nom\":\"Gerard\", \"cognoms\":\"Pérez\", \"email\":\"mod@user.com\", \"telefon\":\"610000000\", \"direccio\":\"Carrer Test\", \"poblacio\":\"Testland\", \"codiPostal\":\"8000\"}");
                    System.out.println(inData.nextLine());
                    String message = "";

                    while (!message.equals("exit")) {
                        message = sc.next();
                        outData.println(message);
                        if (message.equals("logout")) {
                            try {
                                System.out.println("es vol fer logout");
                            } catch (Exception e) {
                                e.getMessage();
                            } finally {
                                socket.close();
                                System.exit(0);
                            }
                        }
                        System.out.println("Informació enviada");
                    }
                    break;
                case "registerUser":
                    System.out.println("Es vol registrar");
                    outData.println("registerUser");

                    outData.println("{\"id\": \"7897\", \"usuari\": \"Gerard\", \"password\":\"1234\", \"type\":\"client\", \"valoracio\":\"5\", \"nom\":\"Gerard\", \"cognoms\":\"Pérez\", \"email\":\"mod@user.com\", \"telefon\":\"610000000\", \"direccio\":\"Carrer Test\", \"poblacio\":\"Testland\", \"codiPostal\":\"8000\"}");

                    message = "";

                    System.out.println(inData.nextLine());

                    while (!message.equals("exit")) {
                        message = sc.next();
                        outData.println(sc.next());
                    }
                    break;
                case "updateUser":
                    System.out.println("Es vol modificar");
                    outData.println("updateUser");
                    outData.println("{\"id\": \"7897\", \"usuari\": \"Gerard-Modificacio\", \"password\":\"1234\", \"type\":\"client\", \"valoracio\":\"5\", \"nom\":\"Gerard\", \"cognoms\":\"Pérez\", \"email\":\"mod@user.com\", \"telefon\":\"610000000\", \"direccio\":\"Carrer Test\", \"poblacio\":\"Testland\", \"codiPostal\":\"8000\"}");

                    message = "";

                    System.out.println(inData.nextLine());

                    while (!message.equals("exit")) {
                        message = sc.next();
                        outData.println(sc.next());
                    }
                    break;

                case "selectUsersByUsername":
                    System.out.println("Es vol seleccionar un usuari per nom d'usuari");
                    outData.println("selectUsersByUsername");
                    outData.println("John");
                    message = "";
                    System.out.println(inData.nextLine());
                    System.out.println(inData.nextLine());

                    while (!message.equals("exit")) {
                        message = sc.next();
                        outData.println(sc.next());
                    }
                    break;
                case "selectUsersByCity":
                    System.out.println("Es vol seleccionar un usuari per ciutat");
                    outData.println("selectUsersByCity");
                    outData.println("Testland");
                    message = "";
                    System.out.println(inData.nextLine());
                    System.out.println(inData.nextLine());

                    while (!message.equals("exit")) {
                        message = sc.next();
                        outData.println(sc.next());
                    }
                    break;
                case "selectUsersByAssessment":
                    System.out.println("Es vol seleccionar un usuari per puntuació");
                    outData.println("selectUsersByAssessment");
                    outData.println(5f);
                    message = "";
                    System.out.println(inData.nextLine());
                    System.out.println(inData.nextLine());

                    while (!message.equals("exit")) {
                        message = sc.next();
                        outData.println(sc.next());
                    }
                    break;

                case "registerBook":
                    System.out.println("Es vol registrar un llibre");
                    outData.println("registerBook");
                    outData.println("{\"id\": \"0189\", \"titol\": \"Programació per a Dummies\", \"autor\":\"Alexander Crane\", \"ISBN\":\"9781234567897\", \"any\":\"2009\", \"genere\":\"Programació\", \"editorial\":\"Invent\", \"disponibilitat\":\"Llogar\"}");
                    message = "";
                    System.out.println(inData.nextLine());

                    while (!message.equals("exit")) {
                        message = sc.next();
                        outData.println(sc.next());
                    }
                    break;

                case "updateBook":
                    System.out.println("Es vol modificar un llibre");
                    outData.println("updateBook");
                    outData.println("{\"id\": \"0189\", \"titol\": \"Python per a Dummies\", \"autor\":\"Alexander Crane\", \"ISBN\":\"9781234567897\", \"any\":\"2009\", \"genere\":\"Programació\", \"editorial\":\"Invent\", \"disponibilitat\":\"Llogar\"}");
                    message = "";
                    System.out.println(inData.nextLine());

                    while (!message.equals("exit")) {
                        message = sc.next();
                        outData.println(sc.next());
                    }
                    break;

                case "deleteBook":
                    System.out.println("Es vol eliminar un llibre");
                    outData.println("deleteBook");
                    outData.println("{\"id\": \"0189\", \"titol\": \"Python per a Dummies\", \"autor\":\"Alexander Crane\", \"ISBN\":\"9781234567897\", \"any\":\"2009\", \"genere\":\"Programació\", \"editorial\":\"Invent\", \"disponibilitat\":\"Llogar\"}");
                    message = "";
                    System.out.println(inData.nextLine());

                    while (!message.equals("exit")) {
                        message = sc.next();
                        outData.println(sc.next());
                    }
                    break;

                case "selectAllBooks":
                    System.out.println("Es volen seleccionar tots els llibres");
                    outData.println("selectAllBooks");
                    message = "";
                    System.out.println(inData.nextLine());
                    System.out.println(inData.nextLine());

                    while (!message.equals("exit")) {
                        message = sc.next();
                        outData.println(sc.next());
                    }
                    break;

                case "selectBooksByAuthor":
                    System.out.println("Es volen seleccionar els llibres segons autor");
                    outData.println("selectBooksByAuthor");
                    outData.println("Gerard");
                    message = "";
                    System.out.println(inData.nextLine());
                    System.out.println(inData.nextLine());

                    while (!message.equals("exit")) {
                        message = sc.next();
                        outData.println(sc.next());
                    }
                    break;

                case "selectBooksByGenere":
                    System.out.println("Es volen seleccionar els llibres segons genere");
                    outData.println("selectBooksByGenere");
                    outData.println("Terror");
                    message = "";
                    System.out.println(inData.nextLine());
                    System.out.println(inData.nextLine());

                    while (!message.equals("exit")) {
                        message = sc.next();
                        outData.println(sc.next());
                    }
                    break;
                case "selectBooksByYear":
                    System.out.println("Es volen seleccionar els llibres segons any");
                    outData.println("selectBooksByYear");
                    outData.println("2000");
                    message = "";
                    System.out.println(inData.nextLine());
                    System.out.println(inData.nextLine());

                    while (!message.equals("exit")) {
                        message = sc.next();
                        outData.println(sc.next());
                    }
                    break;

                case "selectBooksByISBN":
                    System.out.println("Es volen seleccionar els llibres segons ISBN");
                    outData.println("selectBooksByISBN");
                    outData.println("54326543");
                    message = "";
                    System.out.println(inData.nextLine());
                    System.out.println(inData.nextLine());

                    while (!message.equals("exit")) {
                        message = sc.next();
                        outData.println(sc.next());
                    }
                    break;

                case "logout":
                    try {
                    System.out.println("es vol fer logout");
                } catch (Exception e) {
                    e.getMessage();
                } finally {
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

    private Socket certificate() throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
        KeyStore ks = KeyStore.getInstance("jceks");

        ks.load(new FileInputStream(KEY), null);
        KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
        kf.init(ks, PASS.toCharArray());
        context.init(kf.getKeyManagers(), null, null);

        SocketFactory factory = context.getSocketFactory();
        Socket s = factory.createSocket(HOST, 8888);
        return s;
    }

}
