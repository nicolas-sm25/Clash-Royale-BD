package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    //Credenciales almacenadas localmente

    private static final String URL =
            "...";

    private static final String USER =
            "...";

    private static final String PASS =
            "...";

    //Metodo que valida la conexion con la BD
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASS);
    }
}
