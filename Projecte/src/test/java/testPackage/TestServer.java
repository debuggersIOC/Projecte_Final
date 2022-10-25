package testPackage;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
     * @throws IOException
     * @throws Exception 
     */
    @Test
    public void registerUserToDatabase() throws IOException, Exception{
        databaseConnection();
        deleteFromDatabase("Test", "1234");
        DAOUserImpl daoUser = new DAOUserImpl();
        User userChecker = new User("8989", "Test", "1234", "client");
        daoUser.register(userChecker);
        User userTest = daoUser.select("Test", "1234");
        
        Assert.assertEquals(userTest.getId(),userChecker.getId());

        
    }
    /**
     * Selecciona un usuari de la BBDD
     * @throws Exception 
     */
    @Test
    public void getUserFromDatabase() throws Exception{
        databaseConnection();
        deleteFromDatabase("Test", "1234");
        DAOUserImpl daoUser = new DAOUserImpl();
        registerUserToDatabase();
        User userTest = daoUser.select("Test", "1234");
        User userChecker = new User("8989", "Test", "1234", "client");
        
        Assert.assertEquals(userTest.getId(),userChecker.getId());
        
    }
    /**
     * Elimina l'usuari passat per parametre
     * @param user nom de l'usuari
     * @param pass contrasenya de l'usuari
     * @throws Exception 
     */
    public void deleteFromDatabase(String user, String pass) throws Exception{
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

}
