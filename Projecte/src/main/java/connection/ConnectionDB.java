/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *Classe que inicialitza la connexió del servidor amb la base de dades
 * @author Gerard
 */
public class ConnectionDB {
    
    protected Connection connection;
    
    private final String JDBC_DRIVER = "org.postgresql.Driver";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/collective";
    
    private final String USER = "postgres";
    private final String PASS = "password";
    
    public void connectDB() throws Exception {
       
        try {
            connection= (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            Class.forName(JDBC_DRIVER);
        } catch (Exception ex) {
            System.out.println("No s'ha pogut connectar a la BBDD");
            throw ex;
        }
    }
    
    public void disconnectDB() throws Exception{
        if(connection != null){
            if(!connection.isClosed()){
                connection.close();
            }
        }
    }
    
}
