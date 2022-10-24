/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import connection.ConnectionDB;
import interfaces.DAOUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sun.net.www.content.text.plain;

/**
 *Classe on s'emmagatzemen les peticions SQL a la base de dades
 * @author Gerard
 */
public class DAOUserImpl extends ConnectionDB implements DAOUser {
    
    /**
     * Es selecciona un usuari de la BBDD
     * @param user l'usuari del client
     * @param password la contrasenya del client
     * @return un usuari
     * @throws Exception 
     */
    @Override
    public User select(String user, String password) throws Exception {
        try {
            this.connectDB();
            PreparedStatement st = this.connection.prepareStatement("SELECT id, usuari, contrasenya, rol FROM usuaris.usuaris WHERE usuari = ? AND contrasenya = ?");
            st.setString(1, user);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            User u = new User();
            while (rs.next()){

                u.setId(rs.getString("id"));
                u.setUsuari(rs.getString("usuari"));
                u.setPassword(rs.getString("contrasenya"));
                u.setType(rs.getString("rol"));    
            }
            return u;
        } catch (Exception e) {
            System.out.println("No s'ha trobat l'usuari");
            throw e;
        }
    }
    
    /**
     * Es registra un nou usuari a la BBDD
     * @param u l'usuari que es vol registrar
     * @throws Exception 
     */
    @Override
    public void register(User u) throws Exception {
        try {
            this.connectDB();
            PreparedStatement st = this.connection.prepareStatement("INSERT INTO usuaris.usuaris (id, usuari, contrasenya, rol) VALUES(?,?,?,?)");
            st.setString(1, u.getId());
            st.setString(2, u.getUsuari());
            st.setString(3, u.getPassword());
            st.setString(4, u.getType());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Ja existeix un usuari igual");
            throw e;
        }
    }

     /**
     * Es modifica un usuari a la BBDD
     * @param u l'usuari a modificar
     * @throws Exception 
     */
    @Override
    public void update(User u) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * S'elimina un usuari de la BBDD
     * @param u l'usuari a eliminar
     * @throws Exception 
     */
    @Override
    public void delete(User u) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
