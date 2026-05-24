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

            System.out.println("\nCarta adicionada correctamente");

        } catch (SQLException error) {
            System.err.println("\nError: " + error.getMessage());
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
            System.out.println("\nError: " + e.getMessage());
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

            System.out.println("\nError: " + e.getMessage());
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

            System.out.println("\nError: " + e.getMessage());
        } return listaCartasConNombre;
    }


    //Metodo para buscar por elixir
    public List<Carta> encontrarElixir(String operador, int elixirAEncontrar) {

        List<Carta> listaElixir = new ArrayList<>();
        String encontrarElixirSQL = "SELECT * FROM cartas WHERE costo " + operador + " ? ORDER BY costo";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement psEncElx = conn.prepareStatement(encontrarElixirSQL)) {

            psEncElx.setInt(1, elixirAEncontrar);
            ResultSet rsEncElx = psEncElx.executeQuery();

            while (rsEncElx.next()) {

                Carta carta = new Carta(
                        rsEncElx.getString("nombre"),
                        rsEncElx.getInt("costo"),
                        rsEncElx.getString("rareza"),
                        rsEncElx.getString("tipo"));

                carta.setId(rsEncElx.getInt("id"));
                listaElixir.add(carta);
            } } catch (SQLException e) {

            System.out.println("\nError: " + e.getMessage());

        } return listaElixir;
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

            System.out.println("\nError: " + e.getMessage());

        } return listaRareza;
    }


    //Metodo para buscar por tipo
    public List<Carta> encontrarTipo(String tipoAEncontrar) {

        List<Carta> listaTipo = new ArrayList<>();
        String encontrarTipoSQL = "SELECT * FROM cartas WHERE tipo = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement psTipo = conn.prepareStatement(encontrarTipoSQL)) {

            psTipo.setString(1, tipoAEncontrar);
            ResultSet rsTipo = psTipo.executeQuery();

            while (rsTipo.next()) {

                Carta carta = new Carta(
                        rsTipo.getString("nombre"),
                        rsTipo.getInt("costo"),
                        rsTipo.getString("rareza"),
                        rsTipo.getString("tipo"));

                carta.setId(rsTipo.getInt("id"));
                listaTipo.add(carta);
            } } catch (SQLException e) {

            System.out.println("\nError: " + e.getMessage());

        } return listaTipo;
    }
}


