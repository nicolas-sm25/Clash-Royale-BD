package ui;

import javax.swing.*;
import java.awt.*;

public class Botones extends JPanel {
    JButton btnInsertar;
    JButton btnMostrar;
    JButton btnBuscar;
    JButton btnFiltrar;
    JButton btnExportar;

    JPanel botonesPrinc;

    public Botones() {

        setLayout(new BorderLayout());

        botonesPrinc = new JPanel();

        btnInsertar = new JButton("Insertar");
        btnMostrar = new JButton("Mostrar");
        btnBuscar = new JButton("Buscar");
        btnFiltrar = new JButton("Filtrar");
        btnExportar = new JButton("Exportar");

        botonesPrinc.add(btnInsertar);
        botonesPrinc.add(btnMostrar);
        botonesPrinc.add(btnBuscar);
        botonesPrinc.add(btnFiltrar);
        botonesPrinc.add(btnExportar);

        add(botonesPrinc, BorderLayout.NORTH);

        //Boton insertar

        btnInsertar.addActionListener(e -> {

            JFrame ventanaInsert = CreadorVentanas.creadorVentanas("Insertar una carta");

        });

    }
}
