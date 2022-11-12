/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 * Classe usuari
 *
 * @author Gerard
 */
public class User implements Serializable {

    private String id;
    private String usuari;
    private String password;
    private String type;
    private float valoracio;
    private String nom;
    private String cognoms;
    private String email;
    private String telefon;
    private String direccio;
    private String poblacio;
    private int codiPostal;

    public User() {
    }

    public User(String id, String usuari, String password, String type) {
        this.id = id;
        this.usuari = usuari;
        this.password = password;
        this.type = type;
    }


    public User(String id, String usuari, String password, String type, float valoracio, String nom, String cognoms, String email, String telefon, String direccio, String poblacio, int codiPostal) {
        this.id = id;
        this.usuari = usuari;
        this.password = password;
        this.type = type;
        this.valoracio = valoracio;
        this.nom = nom;
        this.cognoms = cognoms;
        this.email = email;
        this.telefon = telefon;
        this.direccio = direccio;
        this.poblacio = poblacio;
        this.codiPostal = codiPostal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    public float getValoracio() {
        return valoracio;
    }

    public void setValoracio(float valoracio) {
        this.valoracio = valoracio;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getDireccio() {
        return direccio;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public int getCodiPostal() {
        return codiPostal;
    }

    public void setCodiPostal(int codiPostal) {
        this.codiPostal = codiPostal;
    }

}
