/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.sql.ResultSet;
import java.util.List;
import model.User;

/**
 * Patró dao de la classe User
 *
 * @author Gerard
 */
public interface DAOUser {

    /**
     * Es selecciona un usuari de la BBDD
     *
     * @param user l'usuari del client
     * @param password la contrasenya del client
     * @return un usuari
     * @throws Exception
     */
    public User select(String user, String password) throws Exception;

    /**
     * Es registra un nou usuari a la BBDD
     *
     * @param u l'usuari que es vol registrar
     * @throws Exception
     */
    public void register(User u) throws Exception;

    /**
     * Es modifica un usuari a la BBDD
     *
     * @param u l'usuari a modificar
     * @throws Exception
     */
    public void update(User u) throws Exception;

    /**
     * S'elimina un usuari de la BBDD
     *
     * @param u l'usuari a eliminar
     * @throws Exception
     */
    public void delete(User u) throws Exception;

    /**
     * Llistat de tots els usuaris que hi ha a la BBDD
     *
     * @return Un llistat de tots els usuaris
     * @throws Exception
     */
    public List<User> selectAllUsers() throws Exception;

    /**
     * Selecció d'un llistat amb tots els usuaris segons un nom d'usuari
     * enregistrat
     *
     * @param usuari el nom d'usuari
     * @return un llistat d'usuaris
     * @throws Exception
     */
    public List<User> selectUsersByUsername(String usuari) throws Exception;

    /**
     * Selecció de tots els usuaris d'una determinada població
     *
     * @param poblacio
     * @return un llistat d'usuaris
     * @throws Exception
     */
    public List<User> selectUsersByCity(String poblacio) throws Exception;

    /**
     * Selecció de tots els usuaris d'una determinada valoració
     *
     * @param valoracio
     * @return un llistat d'usuaris
     * @throws Exception
     */
    public List<User> selectUsersByAssessment(String valoracio) throws Exception;

    /**
     * Es cerca a la BBDD un usuari
     *
     * @param rs ResultSet de l'usuari a trobar
     * @return un usuari
     * @throws Exception
     */
    public User selectUsers(ResultSet rs) throws Exception;

}
