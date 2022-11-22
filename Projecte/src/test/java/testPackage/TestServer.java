package testPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import model.Book;
import model.DAOBookImpl;
import model.DAOUserImpl;
import model.User;
import org.junit.Assert;
import org.junit.Test;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Classe per fer els test del servidor
 *
 * @author Gerard
 */
public class TestServer {

    protected Connection connection;

    private final String JDBC_DRIVER = "org.postgresql.Driver";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/collective";

    private final String USER = "postgres";
    private final String PASS = "password";

    @Test
    public void databaseConnection() throws Exception {

        boolean connected = false;
        try {
            connection = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            Class.forName(JDBC_DRIVER);
            connected = true;
        } catch (Exception ex) {
            throw ex;
        }
        Assert.assertTrue(connected);
    }

    /**
     * Enregistra un usuari a la BBDD
     *
     * @throws IOException
     * @throws Exception
     */
    @Test
    public void registerUserToDatabase() throws IOException, Exception {
        databaseConnection();
        deleteUserFromDatabase("Test", "1234");
        DAOUserImpl daoUser = new DAOUserImpl();
        User userChecker = new User("01", "Test", "1234", "client");
        daoUser.register(userChecker);
        User userTest = daoUser.select("Test", "1234");

        Assert.assertEquals(userTest.getId(), userChecker.getId());

    }

    /**
     * Selecciona un usuari de la BBDD
     *
     * @throws Exception
     */
    @Test
    public void getUserFromDatabase() throws Exception {
        databaseConnection();
        deleteUserFromDatabase("Test", "1234");
        DAOUserImpl daoUser = new DAOUserImpl();
        registerUserToDatabase();
        User userTest = daoUser.select("Test", "1234");
        User userChecker = new User("01", "Test", "1234", "client");

        Assert.assertEquals(userTest.getId(), userChecker.getId());

    }

    /**
     * Elimina l'usuari passat per parametre
     *
     * @param user nom de l'usuari
     * @param pass contrasenya de l'usuari
     * @throws Exception
     */
    public void deleteUserFromDatabase(String user, String pass) throws Exception {
        try {
            databaseConnection();
            PreparedStatement st = this.connection.prepareStatement("DELETE FROM usuaris.usuaris WHERE usuari = ? AND contrasenya = ?");
            st.setString(1, user);
            st.setString(2, pass);
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Selecciona usuaris de la BBDD segons el seu username
     * @throws Exception 
     */
    @Test
    public void selectUserByUsernameFromDatabase() throws Exception {

        databaseConnection();
        DAOUserImpl daoUser = new DAOUserImpl();
        List<User> users = daoUser.selectUsersByUsername("Gerard");
        boolean userFound = false;
        for (User user : users) {
            if (user.getUsuari().contains("Gerard")) {
                userFound = true;
            }
        }
        Assert.assertTrue(userFound);
    }
    
    /**
     * Selecciona usuaris de la BBDD segons la seva ciutat
     * @throws Exception 
     */
    @Test
    public void selectUserByCitiesFromDatabase() throws Exception {

        databaseConnection();
        DAOUserImpl daoUser = new DAOUserImpl();
        List<User> users = daoUser.selectUsersByCity("Testland");
        boolean userFound = false;
        for (User user : users) {
            if (user.getPoblacio().contains("Testland")) {
                userFound = true;
            }
        }
        Assert.assertTrue(userFound);
    }
    
    /**
     * Selecciona usuaris de la BBDD segons la seva puntuació
     * @throws Exception 
     */
    @Test
    public void selectUserByAssestmentFromDatabase() throws Exception {

        databaseConnection();
        DAOUserImpl daoUser = new DAOUserImpl();
        List<User> users = daoUser.selectUsersByAssessment(5f);
        boolean userFound = false;
        for (User user : users) {
            if (user.getValoracio() == 5f) {
                userFound = true;
            }
        }
        Assert.assertTrue(userFound);
    }
    
    /**
     * Registra un llibre a la BBDD
     * @throws Exception 
     */
    @Test
    public void registerBookToDatabase() throws Exception {
        databaseConnection();
        deleteBookFromDatabase("AoT");
        deleteBookFromDatabase("ToA");
        DAOBookImpl daoBook = new DAOBookImpl();
        Book bookChecker = new Book(1, "AoT", "Gerard", "87325364287", 2000, "Ficció", "Planeta", "Lloguer");
        daoBook.insert(bookChecker);

        Assert.assertEquals(1, bookChecker.getId());

    }
    
    /**
     * Elimina un llibre a la BBDD
     * @throws Exception 
     */
    public void deleteBookFromDatabase(String titol) throws Exception {
        try {
            databaseConnection();
            PreparedStatement st = this.connection.prepareStatement("DELETE FROM llibres.llibres WHERE titol = ?");
            st.setString(1, titol);
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Modifica un llibre a la BBDD
     * @throws Exception 
     */
    @Test
    public void updateBook() throws Exception {
        databaseConnection();
        deleteBookFromDatabase("AoT");
        deleteBookFromDatabase("ToA");
        DAOBookImpl daoBook = new DAOBookImpl();
        Book newBook = new Book(1, "AoT", "Gerard", "87325364287", 2000, "Ficció", "Planeta", "Lloguer");
        daoBook.insert(newBook);
        Book updatedBook = new Book(1, "ToA", "Gerard", "87325364287", 2000, "Ficció", "Planeta", "Lloguer");
        daoBook.update(updatedBook);
        Book newUpdatedBook = daoBook.selectID(1);

        Assert.assertEquals(1, newUpdatedBook.getId());
    }
    
    
    /**
     * Selecciona llibres de la BBDD segons el seu titol
     * @throws Exception 
     */
    @Test
    public void selectBookByTtitle() throws Exception {

        databaseConnection();
        DAOBookImpl daoBook = new DAOBookImpl();
        List<Book> books = daoBook.selectBooksByTitle("Test");
        boolean bookFound = false;
        for (Book book : books) {
            if (book.getTitol().contains("Test")) {
                bookFound = true;
            }
        }
        Assert.assertTrue(bookFound);
    }
    
    /**
     * Selecciona llibres de la BBDD segons el seu autor
     * @throws Exception 
     */
    @Test
    public void selectBookByAuthor() throws Exception {

        databaseConnection();
        DAOBookImpl daoBook = new DAOBookImpl();
        List<Book> books = daoBook.selectBooksByAuthor("Testing");
        boolean bookFound = false;
        for (Book book : books) {
            if (book.getAutor().contains("Testing")) {
                bookFound = true;
            }
        }
        Assert.assertTrue(bookFound);
    }
    
    /**
     * Selecciona llibres de la BBDD segons el seu ISBN
     * @throws Exception 
     */
    @Test
    public void selectBookByISBN() throws Exception {

        databaseConnection();
        DAOBookImpl daoBook = new DAOBookImpl();
        List<Book> books = daoBook.selectBooksByISBN("54326543");
        boolean bookFound = false;
        for (Book book : books) {
            if (book.getISBN().contains("54326543")) {
                bookFound = true;
            }
        }
        Assert.assertTrue(bookFound);
    }
    
    /**
     * Selecciona llibres de la BBDD segons el seu genere
     * @throws Exception 
     */
    @Test
    public void selectBookByGenere() throws Exception {

        databaseConnection();
        DAOBookImpl daoBook = new DAOBookImpl();
        List<Book> books = daoBook.selectBooksByGenere("Terror");
        boolean bookFound = false;
        for (Book book : books) {
            if (book.getGenere().contains("Terror")) {
                bookFound = true;
            }
        }
        Assert.assertTrue(bookFound);
    }
    
    /**
     * Selecciona llibres de la BBDD segons el seu any
     * @throws Exception 
     */
    @Test
    public void selectBookByYear() throws Exception {

        databaseConnection();
        DAOBookImpl daoBook = new DAOBookImpl();
        List<Book> books = daoBook.selectBooksByYear(2022);
        boolean bookFound = false;
        for (Book book : books) {
            if (book.getAny()== 2022) {
                bookFound = true;
            }
        }
        Assert.assertTrue(bookFound);
    }
    
    
}
