package dao;

import dao.ConexionDB;
import model.Carta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaDAO {

    public void insertarCarta(Carta carta){
        String add_carta = "INSERT INTO cartas (nombre, costo, rareza, tipo) VALUES (?,?,?,?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement query_insert = conn.prepareStatement(add_carta)
        ) {
            query_insert.setString(1,carta.getNombre());
            query_insert.setInt(2,carta.getElixir());
            query_insert.setString(3,carta.getRareza());
            query_insert.setString(4, carta.getTipo());
            query_insert.executeUpdate();

            System.out.println("Carta adicionada correctamente");
        }
        catch (SQLException e)
        {
            System.err.println("Error: "+e.getMessage());
        }

    }



}