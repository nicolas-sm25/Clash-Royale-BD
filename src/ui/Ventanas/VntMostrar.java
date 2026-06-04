package ui.Ventanas;

import dao.CartaDAO;
import model.Carta;
import ui.CreadorComponentes;

import java.util.List;
import javax.swing.*;
import java.awt.*;

public class VntMostrar {

    CartaDAO dao = new CartaDAO();

    public VntMostrar(){

        JFrame ventanaMostrar = CreadorComponentes.crearVentana("Mostrar toda la BD");

        ventanaMostrar.setLayout(null);

        JLabel labelCargando = CreadorComponentes.crearLabel("Cargando...", 450, 350, 400, 35, new Font("Arial", Font.ITALIC, 20));
        ventanaMostrar.add(labelCargando);

        JButton btnExport = CreadorComponentes.crearBoton("Exportar a .txt", 350, 500, 150, 40, new Font("Arial", Font.BOLD, 14));
        ventanaMostrar.add(btnExport);

        JButton btnVolver = CreadorComponentes.crearBoton("Volver", 600, 500, 100, 40, new Font("Arial", Font.BOLD, 14));
        ventanaMostrar.add(btnVolver);

        JLabel labelTitulo = CreadorComponentes.crearLabel("BD CARTAS", 450, 30, 300, 35, new Font("Arial", Font.BOLD, 24));
        ventanaMostrar.add(labelTitulo);

        List<Carta> listaCartas = dao.listaCartas();

        String[] columnasTabla = {"ID", "Nombre", "Elixir", "Rareza", "Tipo"};

        Object[][] datosCartas = new Object[listaCartas.size()][5];

        for(int i = 0; i < listaCartas.size(); i++){

            Carta carta = listaCartas.get(i);

            datosCartas[i][0] = carta.getId();
            datosCartas[i][1] = carta.getNombre();
            datosCartas[i][2] = carta.getElixir();
            datosCartas[i][3] = carta.getRareza();
            datosCartas[i][4] = carta.getTipo();
        }

        JTable tablaCartas = new JTable(datosCartas,columnasTabla);
        tablaCartas.setRowHeight(25);
        tablaCartas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaCartas.setFont(new Font("Arial", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(tablaCartas);
        scroll.setBounds(50,70,900,400);
        ventanaMostrar.add(scroll);

        labelCargando.setVisible(false);




        btnVolver.addActionListener(aeBtnVolver -> {

            ventanaMostrar.dispose();

        });

    }
}
