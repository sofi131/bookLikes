package com.ceica.booklike.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Book extends ModeloBase {
    private int idbook;
    private String title;
    private String description;
    private String author;
    private String isbn;
    private Date dateB;
    private int user_id;

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

    public int getIdbook() {
        return idbook;
    }

    public void setIdbook(int idlibros) {
        this.idbook = idlibros;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
                "idbook=" + idbook +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", dateB=" + dateB +
                ", user_id=" + user_id +
                '}';
    }

    //MÉTODOS -> getAll() para que coja las características de los libros
    public List<Book> getAll() {
        List<Book> bookList = new ArrayList<>();
        Book book = new Book();
        Connection connection = book.getConnection();
        String sql = "SELECT idBook, title, description, author, isbn, dateB, user_id FROM book";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            bookList = readResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    public List<Book> getAllByUser(int iduser) {
        List<Book> bookList = new ArrayList<>();
        Book book1 = new Book();
        Connection connection = book1.getConnection();
        String sql = "SELECT idbook,title,description,author,isbn,dateB,user_id from book where user_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, iduser);
            ResultSet resultSet = preparedStatement.executeQuery();
            bookList = readResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    private List<Book> readResultSet(ResultSet resultSet) throws SQLException {
        List<Book> bookList = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book();
            book.setIdbook(resultSet.getInt("idBook"));
            book.setTitle(resultSet.getString("title"));
            book.setDescription(resultSet.getString("description"));
            book.setIsbn(resultSet.getString("isbn"));
            book.setAuthor(resultSet.getString("author"));
            book.setDateB(resultSet.getDate("dateB"));
            book.setUser_id(resultSet.getInt("user_id"));
            bookList.add(book);
        }
        return bookList;
    }
}