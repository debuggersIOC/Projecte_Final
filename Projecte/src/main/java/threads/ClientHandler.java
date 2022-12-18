/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import app.MainServer;
import interfaces.DAOBook;
import interfaces.DAOUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import model.DAOUserImpl;
import model.User;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import model.Book;
import model.DAOBookImpl;
import org.json.JSONArray;

/**
 * Classe fil que gestiona els usuaris conectats.
 *
 * @author Gerard
 */
public class ClientHandler extends Thread {

    private Socket client;
    private PrintWriter outData;
    private Scanner inData;
    private MainServer server = new MainServer();

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
             * Depenent la petició que entri del client es farà una operació.
             *
             * @param request llegeix la petició del servidor
             * @throws AssertionError() en cas de que no s'envii cap petició
             * correcte
             */

            outData.println("Benvingut al servidor: ");
            while (inData.hasNext()) {
                request = inData.nextLine();
                switch (request) {
                    case "loginUser":
                        System.out.println("Login");
                        try {
                            String userToLogin = inData.nextLine();
                            User loggedUser = loginUser(userToLogin, outData);
                            server.setLoggedUser(loggedUser.getId(), loggedUser);
                        } catch (Exception e) {
                            outData.println("1_2");
                        }
                        break;
                    case "registerUser":
                        System.out.println("Es vol donar d'alta un usuari");
                        try {
                            String jsonObject = inData.nextLine();
                            registerUser(jsonObject, outData);
                        } catch (Exception e) {
                            outData.println("2_2");
                        }
                        break;
                    case "updateUser":
                        System.out.println("Es vol modificar un usuari");
                        try {
                            String jsonObject = inData.nextLine();
                            updateUser(jsonObject, outData);
                        } catch (Exception e) {
                            outData.println("4_2");
                        }
                        break;
                    case "deleteUser":
                        System.out.println("Es vol eliminar un usuari");
                        try {
                            String jsonObject = inData.nextLine();
                            deleteUser(jsonObject, outData);
                        } catch (Exception e) {
                            outData.println("5_2");
                        }
                        break;
                    case "selectAllUsers":
                        System.out.println("Es volen seleccionar tots els usuaris");
                        try {
                            selectAllUsers(outData);
                        } catch (Exception e) {
                            outData.println("6_2");
                        }
                        break;
                    case "selectUsersByUsername":
                        System.out.println("Es volen seleccionar els usuaris amb un nom d'usuari en concret");
                        try {
                            String username = inData.nextLine();
                            selectUsersByUsername(username, outData);
                        } catch (Exception e) {
                            outData.println("7_2");
                        }
                        break;
                    case "selectUsersByCity":
                        System.out.println("Es volen seleccionar els usuaris amb una ciutat en concret");
                        try {
                            String poblacio = inData.nextLine();
                            selectUsersByCity(poblacio, outData);
                        } catch (Exception e) {
                            outData.println("8_2");
                        }
                        break;
                    case "selectUsersByAssessment":
                        System.out.println("Es volen seleccionar els usuaris amb una valoració en concret");
                        try {
                            float valoracio = Float.parseFloat(inData.nextLine());
                            selectUsersByAssessment(valoracio, outData);
                        } catch (NumberFormatException e) {
                            outData.println("9_2");
                        }
                        break;

                    case "registerBook":
                        System.out.println("Es vol donar d'alta un llibre");
                        try {
                            String jsonObject = inData.nextLine();
                            insertBook(jsonObject, outData);
                        } catch (Exception e) {
                            outData.println("10_2");
                        }
                        break;
                    case "updateBook":
                        System.out.println("Es vol modificar un llibre");
                        try {
                            String jsonObject = inData.nextLine();
                            updateBook(jsonObject, outData);
                        } catch (Exception e) {
                            outData.println("11_2");
                        }
                        break;
                    case "deleteBook":
                        System.out.println("Es vol eliminar un llibre");
                        try {
                            String jsonObject = inData.nextLine();
                            deleteBook(jsonObject, outData);
                        } catch (Exception e) {
                            outData.println("12_2");
                        }
                        break;
                    case "selectAllBooks":
                        System.out.println("Es volen seleccionar tots els llibres");
                        try {
                            selectAllBooks(outData);
                        } catch (Exception e) {
                            outData.println("13_2");
                        }
                        break;
                    case "selectBooksByTitle":
                        System.out.println("Es volen seleccionar llibres segons el titol");
                        try {
                            String titol = inData.nextLine();
                            selectBooksByTitle(titol, outData);
                        } catch (Exception e) {
                            outData.println("14_2");
                        }
                        break;
                    case "selectBooksByAuthor":
                        System.out.println("Es volen seleccionar llibres segons l'autor");
                        try {
                            String autor = inData.nextLine();
                            selectBooksByAuthor(autor, outData);
                        } catch (Exception e) {
                            outData.println("15_2");
                        }
                        break;
                    case "selectBooksByISBN":
                        System.out.println("Es volen seleccionar llibres segons ISBN");
                        try {
                            String ISBN = inData.nextLine();
                            selectBooksByISBN(ISBN, outData);
                        } catch (Exception e) {
                            outData.println("16_2");
                        }
                        break;
                    case "selectBooksByGenere":
                        System.out.println("Es volen seleccionar llibres segons el genere");
                        try {
                            String genere = inData.nextLine();
                            selectBooksByGenere(genere, outData);
                        } catch (Exception e) {
                            outData.println("17_2");
                        }
                        break;
                    case "selectBooksByYear":
                        System.out.println("Es volen seleccionar llibres segons l'any");
                        try {
                            String any = inData.nextLine();
                            selectBooksByYear(Integer.parseInt(any), outData);
                        } catch (Exception e) {
                            outData.println("18_2");
                        }
                        break;
                    case "logoutUser":
                        try {
                        System.out.println("Un usuari es vol desconnectar");
                        String userToLogout = inData.nextLine();
                        server.logoutUser(userToLogout);
                        outData.print("3_1");
                    } catch (Exception e) {
                        outData.println("3_2");
                    } finally {
                        client.close();
                    }
                    break;
                    default:
                        System.out.println("No s'ha enviat una petició correcte'");
                        throw new AssertionError();
                }
            }
        } catch (IOException e) {
            e.getMessage();
        } finally {
            outData.close();
        }
    }

    //Metodes per als usuaris
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
        User u = daoUser.select(jsonUser.getString("usuari"), hexConversion(getSHA(jsonUser.getString("password"))));
        if (u.getUsuari() == null) {
            dataOut.println("1_2");
        } else {
            dataOut.println("1_1");
        }
        return u;
    }

    /**
     * Es registra un nou usuari a la BBDD
     *
     * @param stringUser l'objecte que ve creat des del client en format
     * JSONstring
     * @param outData la resposta al client
     * @throws IOException en cas de que un usuari ja estigui registrat amb
     * aquella ID
     */
    private static void registerUser(String stringUser, PrintWriter outData) {

        try {
            DAOUser daoUser = new DAOUserImpl();
            JSONObject jsonUser = new JSONObject(stringUser);

            User p = new User();
            p.setId(jsonUser.getString("id"));
            p.setUsuari(jsonUser.getString("usuari"));
            p.setPassword(hexConversion(getSHA(jsonUser.getString("password"))));
            p.setType(jsonUser.getString("type"));
            p.setValoracio(jsonUser.getFloat("valoracio"));
            p.setNom(jsonUser.getString("nom"));
            p.setCognoms(jsonUser.getString("cognoms"));
            p.setEmail(jsonUser.getString("email"));
            p.setTelefon(jsonUser.getString("telefon"));
            p.setDireccio(jsonUser.getString("direccio"));
            p.setCodiPostal(jsonUser.getInt("codiPostal"));

            daoUser.register(p);

            outData.println("2_1");

        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Es modifica usuari a la BBDD
     *
     * @param stringUser l'objecte que ve creat des del client en format
     * JSONstring
     * @param outData la resposta al client
     * @throws IOException en cas de que no existeixi
     */
    private static void updateUser(String stringUser, PrintWriter outData) {

        try {
            DAOUser daoUser = new DAOUserImpl();
            JSONObject jsonUser = new JSONObject(stringUser);

            User p = new User();
            p.setId(jsonUser.getString("id"));
            p.setUsuari(jsonUser.getString("usuari"));
            p.setPassword(jsonUser.getString("password"));
            p.setType(jsonUser.getString("type"));
            p.setValoracio(jsonUser.getFloat("valoracio"));
            p.setNom(jsonUser.getString("nom"));
            p.setCognoms(jsonUser.getString("cognoms"));
            p.setEmail(jsonUser.getString("email"));
            p.setTelefon(jsonUser.getString("telefon"));
            p.setDireccio(jsonUser.getString("direccio"));
            p.setCodiPostal(jsonUser.getInt("codiPostal"));

            daoUser.update(p);

            outData.println("4_1");

        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * S'elimina un usuari a la BBDD
     *
     * @param stringUser l'objecte que ve creat des del client en format
     * JSONstring
     * @param outData la resposta al client
     * @throws IOException en cas de l'usuari no existeixi
     */
    private static void deleteUser(String stringUser, PrintWriter outData) {

        try {
            DAOUser daoUser = new DAOUserImpl();
            JSONObject jsonUser = new JSONObject(stringUser);

            User p = new User();
            p.setId(jsonUser.getString("id"));
            p.setUsuari(jsonUser.getString("usuari"));
            p.setPassword(jsonUser.getString("password"));
            p.setType(jsonUser.getString("type"));

            daoUser.delete(p);

            outData.println("5_1");

        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Es retornen tots els usuaris de la BBDD
     *
     * @param outData la resposta al client
     * @throws IOException en cas de que no s'hagi pogut realitzar la
     * sol·licitud
     */
    private static void selectAllUsers(PrintWriter outData) {

        try {
            DAOUser daoUser = new DAOUserImpl();

            JSONArray allUsers = new JSONArray(daoUser.selectAllUsers());

            outData.println("6_1");
            outData.println(allUsers);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Es retorna un llistat d'usuaris segons el seu nom d'usuari
     *
     * @param usuari el nom de d'usuari a cercar
     * @param outData la resposta al client
     * @throws IOException en cas de que no s'hagi pogut realitzar la
     * sol·licitud
     */
    private static void selectUsersByUsername(String usuari, PrintWriter outData) {

        try {
            DAOUser daoUser = new DAOUserImpl();

            JSONArray usersByUsername = new JSONArray(daoUser.selectUsersByUsername(usuari));

            System.out.println(usersByUsername);
            outData.println("7_1");
            outData.println(usersByUsername);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Es retorna un llistat d'usuaris segons la seva població
     *
     * @param poblacio el nom de la poblacio a cercar
     * @param outData la resposta al client
     * @throws IOException en cas de que no s'hagi pogut realitzar la
     * sol·licitud
     */
    private static void selectUsersByCity(String poblacio, PrintWriter outData) {

        try {
            DAOUser daoUser = new DAOUserImpl();

            JSONArray usersByCity = new JSONArray(daoUser.selectUsersByCity(poblacio));

            outData.println("8_1");
            outData.println(usersByCity);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Es retorna un llistat d'usuaris segons la seva valoració
     *
     * @param valoracio la valoració dels usuaris a cercar
     * @param outData la resposta al client
     * @throws IOException en cas de que no s'hagi pogut realitzar la
     * sol·licitud
     */
    private static void selectUsersByAssessment(float valoracio, PrintWriter outData) {

        try {
            DAOUser daoUser = new DAOUserImpl();

            JSONArray usersByAssessment = new JSONArray(daoUser.selectUsersByAssessment(valoracio));

            outData.println("9_1");
            outData.println(usersByAssessment);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    //Metodes per als llibres
    /**
     * Insereix un llibre a la BBDD
     *
     * @param stringBook un string JSON que conté l'objecte del llibre
     * @param outData la resposta al client
     */
    private static void insertBook(String stringBook, PrintWriter outData) {

        try {
            DAOBook daoBook = new DAOBookImpl();
            JSONObject jsonBook = new JSONObject(stringBook);

            Book b = new Book();

            b.setId(jsonBook.getInt("id"));
            b.setTitol(jsonBook.getString("titol"));
            b.setAutor(jsonBook.getString("autor"));
            b.setISBN(jsonBook.getString("ISBN"));
            b.setAny(jsonBook.getInt("any"));
            b.setGenere(jsonBook.getString("genere"));
            b.setEditorial(jsonBook.getString("editorial"));
            b.setDisponibilitat(jsonBook.getString("disponibilitat"));

            daoBook.insert(b);

            outData.println("10_1");

        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Modifica un llibre a la BBDD
     *
     * @param stringBook un string JSON que conté l'objecte del llibre
     * @param outData la resposta al client
     */
    private static void updateBook(String stringBook, PrintWriter outData) {

        try {
            DAOBook daoBook = new DAOBookImpl();
            JSONObject jsonBook = new JSONObject(stringBook);

            Book b = new Book();

            b.setId(jsonBook.getInt("id"));
            b.setTitol(jsonBook.getString("titol"));
            b.setAutor(jsonBook.getString("autor"));
            b.setISBN(jsonBook.getString("ISBN"));
            b.setAny(jsonBook.getInt("any"));
            b.setGenere(jsonBook.getString("genere"));
            b.setEditorial(jsonBook.getString("editorial"));
            b.setDisponibilitat(jsonBook.getString("disponibilitat"));

            daoBook.update(b);

            outData.println("11_1");

        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Elimina un llibre a la BBDD
     *
     * @param stringBook un string JSON que conté l'objecte del llibre
     * @param outData la resposta al client
     */
    private static void deleteBook(String stringBook, PrintWriter outData) {

        try {
            DAOBook daoBook = new DAOBookImpl();
            JSONObject jsonBook = new JSONObject(stringBook);

            Book b = new Book();

            b.setId(jsonBook.getInt("id"));
            b.setTitol(jsonBook.getString("titol"));
            b.setAutor(jsonBook.getString("autor"));
            b.setISBN(jsonBook.getString("ISBN"));
            b.setAny(jsonBook.getInt("any"));
            b.setGenere(jsonBook.getString("genere"));
            b.setEditorial(jsonBook.getString("editorial"));
            b.setDisponibilitat(jsonBook.getString("disponibilitat"));

            daoBook.delete(b);

            outData.println("12_1");

        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Selecciona tots els llibres de la BBDD
     *
     * @param outData la reposta al client i un JSONArray en format string
     */
    private static void selectAllBooks(PrintWriter outData) {

        try {
            DAOBook daoBook = new DAOBookImpl();

            JSONArray allBooks = new JSONArray(daoBook.selectAllBooks());

            outData.println("13_1");
            outData.println(allBooks);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Selecciona tots els llibres per titol de la BBDD
     *
     * @param titol el titol del llibre
     * @param outData la reposta al client i un JSONArray en format string
     */
    private static void selectBooksByTitle(String titol, PrintWriter outData) {

        try {
            DAOBook daoBook = new DAOBookImpl();

            JSONArray booksByTitle = new JSONArray(daoBook.selectBooksByTitle(titol));

            outData.println("14_1");
            outData.println(booksByTitle);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Selecciona tots els llibres de la BBDD per autor
     *
     * @param autor el nom de l'autor
     * @param outData la resposta al client i un JSONArray en format string
     */
    private static void selectBooksByAuthor(String autor, PrintWriter outData) {

        try {
            DAOBook daoBook = new DAOBookImpl();

            JSONArray booksByAuthor = new JSONArray(daoBook.selectBooksByAuthor(autor));

            outData.println("15_1");
            outData.println(booksByAuthor);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Selecciona tots els llibres de la BBDD per ISBN
     *
     * @param ISBN codi ISBN
     * @param outData la resposta al client i un JSONArray en format string
     */
    private static void selectBooksByISBN(String ISBN, PrintWriter outData) {

        try {
            DAOBook daoBook = new DAOBookImpl();

            JSONArray booksByISBN = new JSONArray(daoBook.selectBooksByISBN(ISBN));

            outData.println("16_1");
            outData.println(booksByISBN);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Selecciona tots els llibres de la BBDD per genere
     *
     * @param genere el tipus de genere
     * @param outData la resposta al client i un JSONArray en format string
     */
    private static void selectBooksByGenere(String genere, PrintWriter outData) {

        try {
            DAOBook daoBook = new DAOBookImpl();

            JSONArray booksByGenere = new JSONArray(daoBook.selectBooksByGenere(genere));

            outData.println("17_1");
            outData.println(booksByGenere);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Selecciona tots els llibres de la BBDD per any
     *
     * @param any l'any del llibre
     * @param outData la resposta al client i un JSONArray en format string
     */
    private static void selectBooksByYear(int any, PrintWriter outData) {

        try {
            DAOBook daoBook = new DAOBookImpl();

            JSONArray booksByYear = new JSONArray(daoBook.selectBooksByYear(any));

            outData.println("18_1");
            outData.println(booksByYear);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static byte[] getSHA(String string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        return md.digest(string.getBytes(StandardCharsets.UTF_8));
    }

    public static String hexConversion(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}
