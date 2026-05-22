package dao;

import model.Carta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaDAO {

    //Metodo para agregar una carta a la BD
    public void agregarCarta(Carta carta){

        String agregarCartaSQL = "INSERT INTO cartas (nombre, costo, rareza, tipo) VALUES (?,?,?,?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement psAggCarta = conn.prepareStatement(agregarCartaSQL)) {

            psAggCarta.setString(1,carta.getNombre());
            psAggCarta.setInt(2,carta.getElixir());
            psAggCarta.setString(3,carta.getRareza());
            psAggCarta.setString(4, carta.getTipo());
            psAggCarta.executeUpdate();

            System.out.println("Carta adicionada correctamente");

        } catch (SQLException error) {
            System.err.println("Error: " + error.getMessage());
        }}


    //Metodo para mostrar la tabla completa
    public List<Carta> listaCartas() {

        List<Carta> listaCartas = new ArrayList<>();
        String listaCartasSQL = "SELECT * FROM cartas";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement psListCartas = conn.prepareStatement(listaCartasSQL);
             ResultSet rsListCartas = psListCartas.executeQuery()) {

            while (rsListCartas.next()) {

                Carta carta = new Carta(
                        rsListCartas.getString("nombre"),
                        rsListCartas.getInt("costo"),
                        rsListCartas.getString("rareza"),
                        rsListCartas.getString("tipo"));

                carta.setId(rsListCartas.getInt("id"));
                listaCartas.add(carta);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } return listaCartas;
    }

    //Metodo para buscar por ID
    public Carta buscarPorId(int id) {
        String sql = "SELECT * FROM cartas WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Carta carta = new Carta(
                        rs.getString("nombre"),
                        rs.getInt("costo"),
                        rs.getString("rareza"),
                        rs.getString("tipo")
                );
                carta.setId(rs.getInt("id"));
                return carta;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    //Metodo para buscar por nombre
    public List<Carta> buscarPorNombre(String nombre) {
        List<Carta> lista = new ArrayList<>();
        String sql = "SELECT * FROM cartas WHERE nombre LIKE ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
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
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}

