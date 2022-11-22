/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import connection.ConnectionDB;
import interfaces.DAOBook;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isard
 */
public class DAOBookImpl extends ConnectionDB implements DAOBook {

    /**
     * Es cerca a la BBDD un llibre
     *
     * @param rs ResultSet
     * @return el llibre seleccionat
     * @throws Exception
     */
    @Override
    public Book select(ResultSet rs) throws Exception {

        try {
            Book b = new Book();

            b.setId(rs.getInt("id"));
            b.setTitol(rs.getString("titol"));
            b.setAutor(rs.getString("autor"));
            b.setISBN(rs.getString("isbn"));
            b.setAny(rs.getInt("year"));
            b.setGenere(rs.getString("genere"));
            b.setEditorial(rs.getString("editorial"));
            b.setDisponibilitat(rs.getString("disponibilitat"));

            return b;

        } catch (SQLException e) {
            System.out.println("No s'ha trobat cap llibre");
            throw e;
        }
    }
    
    /**
     * Selecciona un llibre mitjançant el seu ID
     * @param id identificador del llibre
     * @return el llibre trobat
     * @throws Exception 
     */
    public Book selectID(int id) throws Exception{
        try {
            this.connectDB();
            Book bookList = new Book();
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM llibres.llibres WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookList = select(rs);
            }
            return bookList;
        } catch (Exception e) {
            System.out.println("No s'ha trobat cap llibre amb aquest ISBN");
            throw e;
        }
    }

    /**
     * Seleccionar tots els llibres segons el titol
     *
     * @param titol El titol del llibre
     * @return Un llistat de tots els llibres segons el titol
     * @throws Exception
     */
    @Override
    public List<Book> selectBooksByTitle(String titol) throws Exception {
        try {
            this.connectDB();
            List<Book> bookList = new ArrayList<>();
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM llibres.llibres WHERE titol = ?");
            ps.setString(1, titol);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookList.add(select(rs));
            }
            return bookList;
        } catch (Exception e) {
            System.out.println("No s'ha trobat cap llibre amb aquest títol");
            throw e;
        }
    }

    /**
     * Seleccionar tots els llibres segons l'autor
     *
     * @param autor L'autor del llibre
     * @return Un llistat de tots els llibres segons l'autor
     * @throws Exception
     */
    @Override
    public List<Book> selectBooksByAuthor(String autor) throws Exception {
        try {
            this.connectDB();
            List<Book> bookList = new ArrayList<>();
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM llibres.llibres WHERE autor = ?");
            ps.setString(1, autor);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookList.add(select(rs));
            }
            return bookList;
        } catch (Exception e) {
            System.out.println("No s'ha trobat cap llibre amb aquest autor");
            throw e;
        }
    }

    /**
     * Seleccionar tots els llibres segons l'ISBN
     *
     * @param ISBN L'ISBN del llibre
     * @return Un llistat de tots els llibres segons l'ISBN
     * @throws Exception
     */
    @Override
    public List<Book> selectBooksByISBN(String ISBN) throws Exception {
        try {
            this.connectDB();
            List<Book> bookList = new ArrayList<>();
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM llibres.llibres WHERE isbn = ?");
            ps.setString(1, ISBN);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookList.add(select(rs));
            }
            return bookList;
        } catch (Exception e) {
            System.out.println("No s'ha trobat cap llibre amb aquest ISBN");
            throw e;
        }
    }

    /**
     * Seleccionar tots els llibres segons l'any
     *
     * @param any L'any del llibre
     * @return Un llistat de tots els llibres segons l'any
     * @throws Exception
     */
    @Override
    public List<Book> selectBooksByYear(int any) throws Exception {
        try {
            this.connectDB();
            List<Book> bookList = new ArrayList<>();
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM llibres.llibres WHERE year = ?");
            ps.setInt(1, any);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookList.add(select(rs));
            }
            return bookList;
        } catch (Exception e) {
            System.out.println("No s'ha trobat cap llibre amb aquest any");
            throw e;
        }
    }

    /**
     * Seleccionar tots els llibres segons el genere
     *
     * @param genere El genere del llibre
     * @return Un llistat de tots els llibres segons el genere
     * @throws Exception
     */
    @Override
    public List<Book> selectBooksByGenere(String genere) throws Exception {
        try {
            this.connectDB();
            List<Book> bookList = new ArrayList<>();
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM llibres.llibres WHERE genere = ?");
            ps.setString(1, genere);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookList.add(select(rs));
            }
            return bookList;
        } catch (Exception e) {
            System.out.println("No s'ha trobat cap llibre amb aquest genere");
            throw e;
        }
    }

    /**
     * S'insereix un llibre a la BBDD
     *
     * @param book El llibre a inserir
     * @throws Exception
     */
    @Override
    public void insert(Book book) throws Exception {
        try {
            this.connectDB();
            PreparedStatement st = this.connection.prepareStatement("INSERT INTO llibres.llibres (id, titol, autor, isbn, year, genere, editorial, disponibilitat) VALUES(?,?,?,?,?,?,?,?)");
            st.setInt(1, book.getId());
            st.setString(2, book.getTitol());
            st.setString(3, book.getAutor());
            st.setString(4, book.getISBN());
            st.setInt(5, book.getAny());
            st.setString(6, book.getGenere());
            st.setString(7, book.getEditorial());
            st.setString(8, book.getDisponibilitat());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("No es pot inserir el llibre");
            throw e;
        }
    }

    /**
     * Es modifica un llibre de la BBDD
     *
     * @param book El llibre a modificar
     * @throws Exception
     */
    @Override
    public void update(Book book) throws Exception {
        try {
            this.connectDB();
            PreparedStatement st = this.connection.prepareStatement("UPDATE llibres.llibres SET titol = ?, autor = ?, isbn = ?, year = ?, genere = ?, editorial = ?, disponibilitat = ? WHERE id = ?");
            st.setString(1, book.getTitol());
            st.setString(2, book.getAutor());
            st.setString(3, book.getISBN());
            st.setInt(4, book.getAny());
            st.setString(5, book.getGenere());
            st.setString(6, book.getEditorial());
            st.setString(7, book.getDisponibilitat());
            st.setInt(8, book.getId());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("No es pot modificar el llibre");
            throw e;
        }
    }

    /**
     * S'elimina un llibre a la BBDD
     *
     * @param book El llibre a eliminar
     * @throws Exception
     */
    @Override
    public void delete(Book book) throws Exception {
        try {
            this.connectDB();
            PreparedStatement st = this.connection.prepareStatement("DELETE FROM llibres.llibres WHERE id = ?");
            st.setInt(1, book.getId());
            if (st.executeUpdate() == 0) {
                System.out.println("No existeix el llibre");
            }
        } catch (Exception e) {
            System.out.println("No es pot eliminar el llibre");
            throw e;
        }
    }

    /**
     * Selecció de tots els llibres de la BBDD
     *
     * @return Un llistat de tots els llibres
     * @throws Exception
     */
    @Override
    public List<Book> selectAllBooks() throws Exception {
        try {
            this.connectDB();
            List<Book> bookList = new ArrayList<>();
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM llibres.llibres");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookList.add(select(rs));
            }
            return bookList;
        } catch (Exception e) {
            System.out.println("No s'ha trobat cap llibre");
            throw e;
        }
    }
}
