/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import connection.ConnectionDB;
import interfaces.DAOUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
         try {
            this.connectDB();
            PreparedStatement st = this.connection.prepareStatement("UPDATE usuaris.usuaris SET usuari = ?, contrasenya = ?, rol = ?, valoracio = ?, nom = ?, cognoms = ?, email = ?,telefon = ?,  direccio = ?, poblacio = ?, codiPostal = ? WHERE id = ?");
            st.setString(1, u.getUsuari());
            st.setString(2, u.getPassword());
            st.setString(3, u.getType());
            st.setFloat(4, u.getValoracio());
            st.setString(5, u.getNom());
            st.setString(6, u.getCognoms());
            st.setString(7, u.getEmail());
            st.setString(8, u.getTelefon());
            st.setString(9, u.getDireccio());
            st.setString(10, u.getPoblacio());
            st.setInt(11, u.getCodiPostal());
            st.setString(12, u.getId());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("No es pot modificar l'usuari");
            throw e;
        }
    }
    /**
     * S'elimina un usuari de la BBDD
     * @param u l'usuari a eliminar
     * @throws Exception 
     */
    @Override
    public void delete(User u) throws Exception {
        try {
            this.connectDB();
            PreparedStatement st = this.connection.prepareStatement("DELETE FROM usuaris.usuaris WHERE id = ?");
            st.setString(1, u.getId());
            if (st.executeUpdate() == 0) {
                System.out.println("No existeix l'usuari");
            }
        } catch (Exception e) {
            System.out.println("No es pot eliminar l'usuari");
            throw e;
        }
    }
    
    /**
     * Es cerca a la BBDD un usuari
     *
     * @param rs ResultSet de l'usuari a trobar
     * @return un usuari
     * @throws Exception
     */
    @Override
    public User selectUsers(ResultSet rs) throws Exception {
        try {
            User u = new User();
            
            u.setId(rs.getString("id"));
            u.setUsuari(rs.getString("usuari"));
            u.setPassword(rs.getString("contrasenya"));
            u.setType(rs.getString("rol"));
            u.setValoracio(rs.getFloat("valoracio"));
            u.setNom(rs.getString("nom"));
            u.setCognoms(rs.getString("cognoms"));
            u.setEmail(rs.getString("email"));
            u.setTelefon(rs.getString("telefon"));
            u.setDireccio(rs.getString("direccio"));
            u.setPoblacio(rs.getString("poblacio"));
            u.setCodiPostal(rs.getInt("codi_postal"));

            return u;

        } catch (SQLException e) {
            System.out.println("No s'ha trobat cap llibre");
            throw e;
        }
    }

    /**
     * Llistat de tots els usuaris que hi ha a la BBDD
     *
     * @return Un llistat de tots els usuaris
     * @throws Exception
     */
    @Override
    public List<User> selectAllUsers() throws Exception {
        try {
            this.connectDB();
            List<User> userList = new ArrayList<>();
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM usuaris.usuaris");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(selectUsers(rs));
            }
            return userList;
        } catch (Exception e) {
            System.out.println("No s'ha trobat cap usuari");
            throw e;
        }
    }

    @Override
    public List<User> selectUsersByUsername(String usuari) throws Exception {
        try {
            this.connectDB();
            List<User> userList = new ArrayList<>();
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM usuaris.usuaris WHERE usuari = ?");
            ps.setString(1, usuari);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(selectUsers(rs));
            }
            return userList;
        } catch (Exception e) {
            System.out.println("No s'ha trobat cap usuari");
            throw e;
        }
    }

    @Override
    public List<User> selectUsersByCity(String poblacio) throws Exception {
        try {
            this.connectDB();
            List<User> userList = new ArrayList<>();
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM usuaris.usuaris WHERE poblacio = ?");
            ps.setString(1, poblacio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(selectUsers(rs));
            }
            return userList;
        } catch (Exception e) {
            System.out.println("No s'ha trobat cap usuari");
            throw e;
        }
    }

    @Override
    public List<User> selectUsersByAssessment(String valoracio) throws Exception {
        try {
            this.connectDB();
            List<User> userList = new ArrayList<>();
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM usuaris.usuaris WHERE valoracio = ?");
            ps.setString(1, valoracio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(selectUsers(rs));
            }
            return userList;
        } catch (Exception e) {
            System.out.println("No s'ha trobat cap usuari");
            throw e;
        }
    }
}
