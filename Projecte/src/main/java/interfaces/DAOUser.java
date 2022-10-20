/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import model.User;

/**
 *
 * @author Gerard
 */
public interface DAOUser {
    
    public boolean select(String user, String password) throws Exception;
    public void register(User u) throws Exception;
    public void update(User u) throws Exception;
    public void delete(User u) throws Exception;
    
}
