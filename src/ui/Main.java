package ui;

import ui.Ventanas.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame ventanaPrinc = CreadorComponentes.crearVentana("BD cartas de Clash Royale");
        ventanaPrinc.setLayout(null);

        JLabel labelTitulo = CreadorComponentes.crearLabel("BASE DE DATOS CLASH ROYALE", 250, 50, 600, 50, new Font("Arial", Font.BOLD, 28));
        ventanaPrinc.add(labelTitulo);

        JButton btnInsertar = CreadorComponentes.crearBoton("Insertar", 250, 150, 180, 70, new Font("Arial", Font.BOLD, 16));
        ventanaPrinc.add(btnInsertar);

        JButton btnMostrar = CreadorComponentes.crearBoton("Mostrar", 550, 150, 180, 70, new Font("Arial", Font.BOLD, 16));
        ventanaPrinc.add(btnMostrar);

        JButton btnBuscar = CreadorComponentes.crearBoton("Buscar", 250, 280, 180, 70, new Font("Arial", Font.BOLD, 16));
        ventanaPrinc.add(btnBuscar);

        JButton btnFiltrar = CreadorComponentes.crearBoton("Filtrar", 550, 280, 180, 70, new Font("Arial", Font.BOLD, 16));
        ventanaPrinc.add(btnFiltrar);

        JButton btnSalir = CreadorComponentes.crearBoton("Salir", 400, 450, 180, 60, new Font("Arial", Font.BOLD, 16));
        ventanaPrinc.add(btnSalir);

        btnInsertar.addActionListener(aeBtnInsertar -> {

            new VntInsertar();

        });

        btnMostrar.addActionListener(aeBtnMostrar -> {

            new VntMostrar();

        });

        btnBuscar.addActionListener(aeBtnBuscar -> {

            new VntBuscar();

        });

        btnFiltrar.addActionListener(aeBtnFiltrar -> {

            new VntFiltrar();

        });

        btnSalir.addActionListener(aeBtnSalir -> {

            System.exit(0);

        });

        ventanaPrinc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
