package com.ceica.booklike.models;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public abstract class ModeloBase {

    private static final String CONFIG_FILE = "config.properties";
    protected static String URL;
    protected static String USUARIO;
    protected static String PASSWORD;

    static {
        cargarConfiguracion();
    }

    private static void cargarConfiguracion() {
        Properties propiedades = new Properties();
        try (FileInputStream entrada = new FileInputStream(CONFIG_FILE)) {
            propiedades.load(entrada);
            URL = propiedades.getProperty("db.url");
            USUARIO = propiedades.getProperty("db.user");
            PASSWORD = propiedades.getProperty("db.pass");
        } catch (IOException e) {
            URL = "jdbc:mysql://localhost:3306/booklikes";
            USUARIO = "booklikes";
            PASSWORD = "1234";
        }
    }

    // Método genérico para ejecutar consultas SQL
    private boolean ejecutarQuery(String sql, Object... parametros) {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            // Establecer los valores de los parámetros
            for (int i = 0; i < parametros.length; i++) {
                preparedStatement.setObject(i + 1, parametros[i]);
            }

            // Ejecutar la consulta
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Método que devuelve la conexion a la bbdd
    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método abstracto para obtener el nombre de la tabla
    protected abstract String getNombreTabla();

    // Métodos para CRUD
    public boolean create(String sql, Object... parametros) {
        sql = "insert into " + getNombreTabla() + " " + sql;
        return ejecutarQuery(sql, parametros);
    }

    public boolean read(String sql, Object... parametros) {
        return true;
    }

    public boolean update(String sql, Object... parametros) {
        sql = "update " + getNombreTabla() + " set " + sql;
        return ejecutarQuery(sql, parametros);
    }

    public boolean delete(String sql, Object... parametros) {
        sql = "delete from " + getNombreTabla() + " where " + sql;
        return ejecutarQuery(sql, parametros);
    }

}