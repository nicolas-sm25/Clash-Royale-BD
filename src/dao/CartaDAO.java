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

        String buscarPorIdSQL = "SELECT * FROM cartas WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement psBusqId = conn.prepareStatement(buscarPorIdSQL)) {

            psBusqId.setInt(1, id);
            ResultSet rsBusqId = psBusqId.executeQuery();

            if (rsBusqId.next()) {

                Carta carta = new Carta(
                        rsBusqId.getString("nombre"),
                        rsBusqId.getInt("costo"),
                        rsBusqId.getString("rareza"),
                        rsBusqId.getString("tipo"));

                carta.setId(rsBusqId.getInt("id"));
                return carta;
            }

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());
        } return null;
    }

    //Metodo para buscar por nombre
    public List<Carta> buscarPorNombre(String nombre) {

        List<Carta> listaCartasConNombre = new ArrayList<>();
        String buscarPorNombreSQL = "SELECT * FROM cartas WHERE nombre LIKE ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement psBusqNom = conn.prepareStatement(buscarPorNombreSQL)) {

            psBusqNom.setString(1, "%" + nombre + "%");
            ResultSet rsBusqNom = psBusqNom.executeQuery();

            while (rsBusqNom.next()) {

                Carta carta = new Carta(
                        rsBusqNom.getString("nombre"),
                        rsBusqNom.getInt("costo"),
                        rsBusqNom.getString("rareza"),
                        rsBusqNom.getString("tipo"));

                carta.setId(rsBusqNom.getInt("id"));
                listaCartasConNombre.add(carta);
            } } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());
        } return listaCartasConNombre;
    }


    //Metodo para buscar por Elixir = X
    public List<Carta> elixirIgual(int elixirIgualAEncontrar) {

        List<Carta> listaElixirIgual = new ArrayList<>();
        String elixirIgualSQL = "SELECT * FROM cartas WHERE costo = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement psElxIg = conn.prepareStatement(elixirIgualSQL)) {

            psElxIg.setInt(1, elixirIgualAEncontrar);
            ResultSet rsElxIg = psElxIg.executeQuery();

            while (rsElxIg.next()) {

                Carta carta = new Carta(
                        rsElxIg.getString("nombre"),
                        rsElxIg.getInt("costo"),
                        rsElxIg.getString("rareza"),
                        rsElxIg.getString("tipo"));

                carta.setId(rsElxIg.getInt("id"));
                listaElixirIgual.add(carta);
            } } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());

        } return listaElixirIgual;
    }


    //Metodo para buscar por Elixir < X
    public List<Carta> elixirMenor(int elixirMenorAEncontrar) {

        List<Carta> listaElixirMenor = new ArrayList<>();
        String elixirMenorSQL = "SELECT * FROM cartas WHERE costo < ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement psElxMen = conn.prepareStatement(elixirMenorSQL)) {

            psElxMen.setInt(1, elixirMenorAEncontrar);
            ResultSet rsElxMen = psElxMen.executeQuery();

            while (rsElxMen.next()) {

                Carta carta = new Carta(
                        rsElxMen.getString("nombre"),
                        rsElxMen.getInt("costo"),
                        rsElxMen.getString("rareza"),
                        rsElxMen.getString("tipo"));

                carta.setId(rsElxMen.getInt("id"));
                listaElixirMenor.add(carta);
            } } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());

        } return listaElixirMenor;
    }


    //Metodo para buscar por Elixir > X
    public List<Carta> elixirMayor(int elixirMayorAEncontrar) {

        List<Carta> listaElixirMayor = new ArrayList<>();
        String elixirMayorSQL = "SELECT * FROM cartas WHERE costo > ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement psElxMay = conn.prepareStatement(elixirMayorSQL)) {

            psElxMay.setInt(1, elixirMayorAEncontrar);
            ResultSet rsElxMay = psElxMay.executeQuery();

            while (rsElxMay.next()) {

                Carta carta = new Carta(
                        rsElxMay.getString("nombre"),
                        rsElxMay.getInt("costo"),
                        rsElxMay.getString("rareza"),
                        rsElxMay.getString("tipo"));

                carta.setId(rsElxMay.getInt("id"));
                listaElixirMayor.add(carta);
            } } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());

        } return listaElixirMayor;
    }


    //Metodo para buscar por rareza
    public List<Carta> encontrarRareza(String rarezaAEncontrar) {

        List<Carta> listaRareza = new ArrayList<>();
        String encontrarRarezaSQL = "SELECT * FROM cartas WHERE rareza = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement psRareza = conn.prepareStatement(encontrarRarezaSQL)) {

            psRareza.setString(1, rarezaAEncontrar);
            ResultSet rsRareza = psRareza.executeQuery();

            while (rsRareza.next()) {

                Carta carta = new Carta(
                        rsRareza.getString("nombre"),
                        rsRareza.getInt("costo"),
                        rsRareza.getString("rareza"),
                        rsRareza.getString("tipo"));

                carta.setId(rsRareza.getInt("id"));
                listaRareza.add(carta);
            } } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());

        } return listaRareza;
    }
}

