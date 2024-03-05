package com.ceica.booklike.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User extends ModeloBase{
    //Atributos de User
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

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getPassword() {
        return pass;
    }

    public void setPassword(String pass) {
        this.pass = pass;
    }

    public User login(String username, String pass) {
        User user = new User();
        Connection conn = user.getConnection();
        String sql = "select iduser,name from " +
                "where username=? and pass=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, pass);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                user.iduser = resultSet.getInt("iduser");
                user.name = resultSet.getString("username");

                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPass (String pass){
        this.pass = pass;
    }
    public String getPass () {
        return pass;
    }

    @Override
    protected String getNombreTabla() {
        return null;
    }

    @Override
    public String toString() {
        return "User{" +
                "iduser=" + iduser +
                ", username='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        Connection conn = user.getConnection();
        String consulta = "SELECT iduser, name, pass, DateU FROM user";
        try {
            Statement stm = conn.createStatement();
            ResultSet resultSet = stm.executeQuery(consulta);
            while (resultSet.next()) {
                User user1 = new User();
                user1.setIduser(resultSet.getInt("iduser"));
                user1.setName(resultSet.getString("name"));
                user1.setPass(resultSet.getString("pass"));
                //user1.setDateU(resultSet.getDate("DateU"));
                userList.add(user1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

}