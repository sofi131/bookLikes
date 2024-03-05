package com.ceica.booklike.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Book extends ModeloBase {
    private int idlibros;
    private String title;
    private String description;
    private String author;
    private String isbn;
    private Date dateB;

    //--------------------------------------constructor vacío---------------------------
    public Book() {
    }
//---------------------------------------getters y setters--------------------------

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getIdlibros() {
        return idlibros;
    }

    public void setIdlibros(int idlibros) {
        this.idlibros = idlibros;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDateB() {
        return dateB;
    }

    public void setDateB(Date fecha_alta) {
        this.dateB = fecha_alta;
    }

    //---------método abstracto que mete en modelo base-----------------
    @Override
    protected String getNombreTabla() {
        return "book";
    }

    //-------------------------------------toString---------------------------
    @Override
    public String toString() {
        return "Book{" +
                "idlibros=" + idlibros +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", fecha_alta=" + dateB +
                '}';
    }

    //MÉTODOS -> getAll() para que coja las características de los libros
    public List<Book> getAll() {
        List<Book> bookList = new ArrayList<>();
        Book book = new Book();
        Connection conn = book.getConnection();
        String sql = "SELECT idBook, title, description, author, isbn, dateB FROM book";
        try {
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            bookList = readResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    //readResultSet
    private List<Book> readResultSet(ResultSet resultSet) throws SQLException {
        List<Book> bookList = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book();
            book.setIdlibros(resultSet.getInt("idBook"));
            book.setTitle(resultSet.getString("title"));
            book.setDescription(resultSet.getString("description"));
            book.setIsbn(resultSet.getString("isbn"));
            book.setAuthor(resultSet.getString("author"));
            book.setDateB(resultSet.getDate("Date"));
            bookList.add(book);
        }
        return bookList;
    }

}
