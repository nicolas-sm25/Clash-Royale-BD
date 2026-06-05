package dao;

import model.Carta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaDAO {

    boolean accionCompletada;

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

            accionCompletada = true;

        } catch (SQLException error) {
            accionCompletada = false;
        }
    }


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

                accionCompletada = true;

            }

        } catch (SQLException e) {
            accionCompletada = false;
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

                accionCompletada = true;

                return carta;
            }

            accionCompletada = true;

        } catch (SQLException e) {
            accionCompletada = false;
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

            }

                accionCompletada = true;

            } catch (SQLException e) {

            accionCompletada = false;

        } return listaCartasConNombre;
    }

    //Metodo para filtros combinados

    public List<Carta> filtrarCartas(String rareza, String tipo, Integer elixir){

        List<Carta> listaFiltrada = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM cartas WHERE 1=1");

        if (rareza != null){
            sql.append(" AND rareza = ?");
        }

        if (tipo != null){
            sql.append(" AND tipo = ?");
        }

        if(elixir != null){
            sql.append(" AND costo = ?");
        }

        try (Connection conn = ConexionDB.getConnection();
            PreparedStatement psFiltro = conn.prepareStatement(sql.toString())){

            int parametro = 1;

            if(rareza != null){
                psFiltro.setString(parametro++, rareza);
            }

            if(tipo != null){
                psFiltro.setString(parametro++, tipo);
            }

            if(elixir != null){
                psFiltro.setInt(parametro++, elixir);
            }

            ResultSet rsFiltro = psFiltro.executeQuery();

            while (rsFiltro.next()){

                Carta carta = new Carta(
                        rsFiltro.getString("nombre"),
                        rsFiltro.getInt("costo"),
                        rsFiltro.getString("rareza"),
                        rsFiltro.getString("tipo")
                );

                carta.setId(rsFiltro.getInt("id"));

                listaFiltrada.add(carta);
            }

            accionCompletada=true;

        } catch(SQLException e){

            accionCompletada=false;

        }
        return listaFiltrada;
    }


    public boolean getAccionCompletada() {

        return accionCompletada;

    }
}