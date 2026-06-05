package ui;

import dao.CartaDAO;
import model.Carta;
import ui.Ventanas.*;

import javax.swing.*;
import java.awt.*;

public class Botones extends JPanel {

    JButton btnInsertar;
    JButton btnMostrar;
    JButton btnBuscar;
    JButton btnFiltrar;
    JButton btnSalir;

    JPanel botonesPrinc;

    public Botones() {

        setLayout(new BorderLayout());

        botonesPrinc = new JPanel();

        btnInsertar = new JButton("Insertar");
        btnMostrar = new JButton("Mostrar");
        btnBuscar = new JButton("Buscar");
        btnFiltrar = new JButton("Filtrar");
        btnSalir = new JButton("Salir");

        botonesPrinc.add(btnInsertar);
        botonesPrinc.add(btnMostrar);
        botonesPrinc.add(btnBuscar);
        botonesPrinc.add(btnFiltrar);
        botonesPrinc.add(btnSalir);

        add(botonesPrinc, BorderLayout.NORTH);

        //Boton insertar

        btnInsertar.addActionListener(aeBtnInsertar -> {

            new VntInsertar();

        });

        //Boton mostrar

        btnMostrar.addActionListener(aeBtnMostrar -> {

            new VntMostrar();

        });

        //Boton buscar

        btnBuscar.addActionListener(aeBtnBuscar -> {

            new VntBuscar();

        });

        //Boton filtrar

        btnFiltrar.addActionListener(aeBtnFiltrar -> {

            new VntFiltrar();

        });





        //Boton salir

        btnSalir.addActionListener(aeBtnSalir -> {

            System.exit(0);

        });

    }
}
