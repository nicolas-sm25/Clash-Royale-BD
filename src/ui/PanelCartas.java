package ui;

import javax.swing.*;
import java.awt.*;

public class PanelCartas extends JPanel {
    JButton btnInsertar;
    JButton btnMostrar;
    JButton btnBuscar;
    JButton btnFiltrar;
    JButton btnExportar;

    JPanel panelBotones;

    public PanelCartas() {

        setLayout(new BorderLayout());

        panelBotones = new JPanel();

        btnInsertar = new JButton("Insertar");
        btnMostrar = new JButton("Mostrar");
        btnBuscar = new JButton("Buscar");
        btnFiltrar = new JButton("Filtrar");
        btnExportar = new JButton("Exportar");

        panelBotones.add(btnInsertar);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnFiltrar);
        panelBotones.add(btnExportar);

        add(panelBotones, BorderLayout.NORTH);

    }
}
