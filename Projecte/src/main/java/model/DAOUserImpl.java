/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import connection.ConnectionDB;
import interfaces.DAOUser;
import java.sql.PreparedStatement;
import sun.net.www.content.text.plain;

/**
 *
 * @author isard
 */
public class DAOUserImpl extends ConnectionDB implements DAOUser{

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
            throw e;
        }
    }

    //TODO
    @Override
    public void update(User u) throws Exception {
throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //TODO
    @Override
    public void delete(User u) throws Exception {
throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //TODO
    @Override
    public void select(User u) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
