/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import model.User;

/**
 *Patró dao de la classe User
 * @author Gerard
 */
public interface DAOUser {
    
    
    /**
     * Es selecciona un usuari de la BBDD
     * @param user l'usuari del client
     * @param password la contrasenya del client
     * @return un usuari
     * @throws Exception 
     */
    public User select(String user, String password) throws Exception;
    
    /**
     * Es registra un nou usuari a la BBDD
     * @param u l'usuari que es vol registrar
     * @throws Exception 
     */
    public void register(User u) throws Exception;
    
    /**
     * Es modifica un usuari a la BBDD
     * @param u l'usuari a modificar
     * @throws Exception 
     */
    public void update(User u) throws Exception;
    
    /**
     * S'elimina un usuari de la BBDD
     * @param u l'usuari a eliminar
     * @throws Exception 
     */
    public void delete(User u) throws Exception;
    
}
