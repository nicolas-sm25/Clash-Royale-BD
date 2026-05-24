package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    //Credenciales almacenadas localmente

    private static final String URL =
            "jdbc:postgresql://ep-summer-cloud-aq401oln-pooler.c-8.us-east-1.aws.neon.tech/neondb?sslmode=require";

    private static final String USER =
            "neondb_owner";

    private static final String PASS =
            "npg_mazsDQ6eoR8Z";


    //Metodo que valida la conexion con la BD
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASS);
    }
}