/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 * Classe llibre
 *
 * @author Gerard
 */
public class Book implements Serializable {

    private int id;
    private String titol;
    private String autor;
    private String ISBN;
    private int any;
    private String genere;
    private String editorial;
    private String disponibilitat;

    public Book() {
    }

    public Book(int id, String titol, String autor, String ISBN, int any, String genere, String editorial, String disponibilitat) {
        this.id = id;
        this.titol = titol;
        this.autor = autor;
        this.ISBN = ISBN;
        this.any = any;
        this.genere = genere;
        this.editorial = editorial;
        this.disponibilitat = disponibilitat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getDisponibilitat() {
        return disponibilitat;
    }

    public void setDisponibilitat(String disponibilitat) {
        this.disponibilitat = disponibilitat;
    }

}
