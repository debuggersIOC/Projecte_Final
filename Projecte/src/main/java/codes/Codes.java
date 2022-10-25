/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codes;

/**
 * Classe on s'emagatzemen tots els codis que s'envien el client
 * @author Gerard
 */
public class Codes {
    
    public static final String LOG_ACCEPT = "1_1";
    public static final String LOG_DENIED = "1_2";
    public static final String REGISTER_ACCEPT = "2_1";
    public static final String REGISTER_DENIED = "2_2";
    public static final String LOGOUT_ACCEPT = "3_1";
    public static final String LOGOUT_DENIED = "3_2";

    public static String getLOG_ACCEPT() {
        return LOG_ACCEPT;
    }

    public static String getLOGOUT_ACCEPT() {
        return LOGOUT_ACCEPT;
    }

    public static String getLOGOUT_DENIED() {
        return LOGOUT_DENIED;
    }

    public static String getLOG_DENIED() {
        return LOG_DENIED;
    }

    public static String getREGISTER_ACCEPT() {
        return REGISTER_ACCEPT;
    }

    public static String getREGISTER_DENIED() {
        return REGISTER_DENIED;
    }

    
}
