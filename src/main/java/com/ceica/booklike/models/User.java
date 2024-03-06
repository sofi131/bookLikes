package com.ceica.booklike.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends ModeloBase{
    private int iduser;
    private String name;
    private String pass;

    //Constructor vacio
    public User() {
    }

    //getter and setters
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setPass (String pass){
        this.pass = pass;
    }
    public String getPass () {
        return pass;
    }

    @Override
    protected String getNombreTabla() {
        return "user";
    }

    @Override
    public String toString() {
        return "User{" +
                "iduser=" + iduser +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public User login(String name, String pass) {
        User user = new User();
        Connection conn = user.getConnection();
        String sql = "select iduser,name from user where name=? and pass=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, pass);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                user.iduser = resultSet.getInt("iduser");
                user.name = resultSet.getString("name");
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}