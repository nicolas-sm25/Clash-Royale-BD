package ui;

import dao.ConexionDB;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {


        //Prueba de coneccion con la BD
        try {

            Connection conn = ConexionDB.getConnection();

            if(conn != null){
                System.out.println("Conectado correctamente");
            }

        } catch(SQLException e){

            System.out.println("Error de conexión");
            System.out.println(e.getMessage());

        }

    }

}