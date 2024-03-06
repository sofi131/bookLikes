package com.ceica.booklike.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Like extends ModeloBase {
    private int idlike;

    public Like() {
    }

    public int getIdlike() {
        return idlike;
    }

    public void setIdlike(int idlike) {
        this.idlike = idlike;
    }

    @Override
    protected String getNombreTabla() {
        return "like";
    }

    @Override
    public String toString() {
        return "Likes{" +
                "idlike=" + idlike +
                '}';
    }

    public List<Like> getAll() {
        List<Like> likeList = new ArrayList<>();
        Like likes = new Like();
        Connection connection = likes.getConnection();
        String consulta = "SELECT book_id, COUNT(book_id) AS likes FROM `like` GROUP BY book_id";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);
            likeList = readResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return likeList;
    }

    private List<Like> readResultSet(ResultSet resultSet) throws SQLException {
        List<Like> likeList = new ArrayList<>();
        while (resultSet.next()) {
            Like like = new Like();
            like.set

            book.setIdbook(resultSet.getInt("idBook"));
            book.setTitle(resultSet.getString("title"));
            book.setDescription(resultSet.getString("description"));
            book.setIsbn(resultSet.getString("isbn"));
            book.setAuthor(resultSet.getString("author"));
            book.setDateB(resultSet.getDate("dateB"));
            book.setUser_id(resultSet.getInt("user_id"));
            likeList.add(like);
        }
        return likeList;
    }
}