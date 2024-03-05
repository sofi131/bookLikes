package com.ceica.booklike.controller;

import com.ceica.booklike.models.Book;
import com.ceica.booklike.models.User;

import java.time.LocalDate;
import java.util.List;

public class BookController {
    public User userLogged;

    public boolean login(String username, String password) {
        User user = new User();
        userLogged = user.login(username, password);
        return userLogged != null;
    }

    public boolean createUser(String username, String pass) {
        User user = new User();
        return user.create("(username,password) values (?,?)", username, pass);
    }

//    public boolean editPassword(String username, String password) {
//        User user = new User();
//        return user.update("password=? where username=?", password, username);
//    }

    public boolean createBook(String title, String description, String author, LocalDate createDate) {
        Book book = new Book();
        return book.create("(title,description,author,createDate) values (?,?,?,?)", title, description, author, createDate);
    }

    public List<Book> getAllBooksByUser() {
        Book book = new Book();
        return null;
    }

    public List<Book> getAllBooks() {
        Book book = new Book();
        return book.getAll();
    }

    public boolean addFavoriteBook(int idBook) {
        return false;
    }

    public boolean removeFavoriteBook(int idBook) {
        return false;
    }

    public List<Book> getFavoriteBooks() {
        return null;
    }
//esto está en el método User -> getAll() -> User.java
    public List<User> getAllUsers() {
        User user = new User();
        return user.getAll();
    }
}
