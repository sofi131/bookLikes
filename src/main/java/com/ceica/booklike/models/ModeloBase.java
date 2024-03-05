package com.ceica.booklike.models;

import java.sql.Connection;

public abstract class ModeloBase {
    protected abstract String getNombreTabla();

    protected Connection getConnection() {

    }
}
