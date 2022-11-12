/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.sql.ResultSet;
import java.util.List;
import model.Book;

/**
 *
 * @author Gerard
 */
public interface DAOBook {

    /**
     * Es cerca a la BBDD un llibre
     *
     * @param rs ResultSet
     * @return el llibre seleccionat
     * @throws Exception
     */
    public Book select(ResultSet rs) throws Exception;

    /**
     * Seleccionar tots els llibres segons el titol
     *
     * @param titol El titol del llibre
     * @return Un llistat de tots els llibres segons el titol
     * @throws Exception
     */
    public List<Book> selectBooksByTitle(String titol) throws Exception;

    /**
     * Seleccionar tots els llibres segons l'autor
     *
     * @param autor L'autor del llibre
     * @return Un llistat de tots els llibres segons l'autor
     * @throws Exception
     */
    public List<Book> selectBooksByAuthor(String autor) throws Exception;

    /**
     * Seleccionar tots els llibres segons l'ISBN
     *
     * @param ISBN L'ISBN del llibre
     * @return Un llistat de tots els llibres segons l'ISBN
     * @throws Exception
     */
    public List<Book> selectBooksByISBN(String ISBN) throws Exception;

    /**
     * Seleccionar tots els llibres segons l'any
     *
     * @param any L'any del llibre
     * @return Un llistat de tots els llibres segons l'any
     * @throws Exception
     */
    public List<Book> selectBooksByYear(String any) throws Exception;

    /**
     * Seleccionar tots els llibres segons el genere
     *
     * @param genere El genere del llibre
     * @return Un llistat de tots els llibres segons el genere
     * @throws Exception
     */
    public List<Book> selectBooksByGenere(String genere) throws Exception;

    /**
     * Selecció de tots els llibres de la BBDD
     *
     * @return Un llistat de tots els llibres
     * @throws Exception
     */
    public List<Book> selectAllBooks() throws Exception;

    /**
     * S'insereix un llibre a la BBDD
     *
     * @param book El llibre a inserir
     * @throws Exception
     */
    public void insert(Book book) throws Exception;

    /**
     * Es modifica un llibre de la BBDD
     *
     * @param book El llibre a modificar
     * @throws Exception
     */
    public void update(Book book) throws Exception;

    /**
     * S'elimina un llibre a la BBDD
     *
     * @param book El llibre a eliminar
     * @throws Exception
     */
    public void delete(Book book) throws Exception;

}
