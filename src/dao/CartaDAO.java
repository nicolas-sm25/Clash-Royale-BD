package dao;

import dao.ConexionDB;
import model.Carta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaDAO {

    //Metodo para adicionar una carta a la BD
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


    //Metodo para mostrar la tabla completa
    public List<Carta> listarCartas() {

        List<Carta> lista = new ArrayList<>();

        String sql = "SELECT * FROM cartas";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Carta carta = new Carta(
                        rs.getString("nombre"),
                        rs.getInt("costo"),
                        rs.getString("rareza"),
                        rs.getString("tipo")
                );

                carta.setId(rs.getInt("id"));
                lista.add(carta);
            }

        } catch (SQLException e) {
            System.out.println("Error listando cartas: " + e.getMessage());
        }

        return lista;
    }
}

