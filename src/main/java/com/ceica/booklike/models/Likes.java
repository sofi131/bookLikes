package com.ceica.booklike.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Likes extends ModeloBase {
    private int idlike;

    public Likes() {
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

    public List<Likes> getAll() {
        List<Likes> likesList = new ArrayList<>();
        Likes likes = new Likes();
        Connection connection = likes.getConnection();
        String consulta = "select idlike from like";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);
            while (resultSet.next()) {
                Likes like1 = new Likes();
                like1.setIdlike(resultSet.getInt("idlike"));
                likesList.add(like1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return likesList;
    }
}