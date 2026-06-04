package ui;

import dao.CartaDAO;
import model.Carta;

import javax.swing.*;
import java.awt.*;

public class Botones extends JPanel {

    CartaDAO dao = new CartaDAO();

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

            JFrame ventanaInsert = CreadorVentanas.creadorVentanas("Insertar una carta");

            ventanaInsert.setLayout(null);

            JButton btnInsCarta = new JButton("Insertar carta");
            btnInsCarta.setBounds(300, 500, 200, 40);
            btnInsCarta.setFont(new Font("Arial", Font.BOLD, 14));
            ventanaInsert.add(btnInsCarta);

            JButton btnCancelInsCarta = new JButton("Volver");
            btnCancelInsCarta.setBounds(600, 500, 100, 40);
            btnCancelInsCarta.setFont(new Font("Arial", Font.BOLD, 14));
            ventanaInsert.add(btnCancelInsCarta);


            JLabel labelNombreCarta = new JLabel("Nombre de la carta:");
            labelNombreCarta.setFont(new Font("Arial", Font.PLAIN, 20));
            labelNombreCarta.setBounds(50, 30, 300, 35);
            ventanaInsert.add(labelNombreCarta);

            JTextField nombreCarta = new JTextField();
            nombreCarta.setBounds(50, 70, 300, 35);
            nombreCarta.setFont(new Font("Arial", Font.PLAIN, 20));
            ventanaInsert.add(nombreCarta);

            JLabel labelElixir = new JLabel("Costo de elixir:");
            labelElixir.setFont(new Font("Arial", Font.PLAIN, 20));
            labelElixir.setBounds(50, 200, 300, 35);
            ventanaInsert.add(labelElixir);

            String[] costos = {"Selecciona una opción...", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "0 (Solo tropas de torre)"};
            JComboBox<String> menuElixir = new JComboBox<>(costos);

            menuElixir.setBounds(50, 240, 300, 35);
            menuElixir.setFont(new Font("Arial", Font.PLAIN, 14));
            ventanaInsert.add(menuElixir);

            JLabel labelRareza = new JLabel("Rareza:");
            labelRareza.setFont(new Font("Arial", Font.PLAIN, 20));
            labelRareza.setBounds(600, 30, 300, 35);
            ventanaInsert.add(labelRareza);

            String[] rarezas = {"Selecciona una opción...", "Común", "Especial", "Épica", "Legendaria", "Campeón"};
            JComboBox<String> menuRareza = new JComboBox<>(rarezas);

            menuRareza.setBounds(600, 70, 300, 35);
            menuRareza.setFont(new Font("Arial", Font.PLAIN, 14));
            ventanaInsert.add(menuRareza);

            JLabel labelTipo = new JLabel("Tipo:");
            labelTipo.setFont(new Font("Arial", Font.PLAIN, 20));
            labelTipo.setBounds(600, 200, 300, 35);
            ventanaInsert.add(labelTipo);

            String[] tipos = {"Selecciona una opción...", "Tropa", "Estructura", "Hechizo", "Tropa de torre"};
            JComboBox<String> menuTipos = new JComboBox<>(tipos);

            JLabel labelInval = new JLabel("");
            labelInval.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
            labelInval.setForeground(Color.RED);
            labelInval.setBounds(350, 450, 400, 35);
            labelInval.setVisible(false);
            ventanaInsert.add(labelInval);

            JLabel labelCargando = new JLabel("Cargando...");
            labelCargando.setFont(new Font("Arial", Font.ITALIC, 20));
            labelCargando.setBounds(350, 450, 400, 35);
            labelCargando.setVisible(false);
            ventanaInsert.add(labelCargando);

            JLabel labelCartaAgg = new JLabel("Carta agregada correctamente");
            labelCartaAgg.setFont(new Font("Arial", Font.BOLD |Font.ITALIC, 20));
            labelCartaAgg.setBounds(350, 450, 400, 35);
            labelCartaAgg.setForeground(Color.GREEN);
            labelCartaAgg.setVisible(false);
            ventanaInsert.add(labelCartaAgg);

            JLabel labelError = new JLabel("Error al agregar la carta");
            labelError.setFont(new Font("Arial", Font.BOLD |Font.ITALIC, 20));
            labelError.setBounds(350, 450, 400, 35);
            labelError.setForeground(Color.RED);
            labelError.setVisible(false);
            ventanaInsert.add(labelError);

            menuTipos.setBounds(600, 240, 300, 35);
            menuTipos.setFont(new Font("Arial", Font.PLAIN, 14));
            ventanaInsert.add(menuTipos);

            btnInsCarta.addActionListener(aeBtnInsCarta -> {

                String nombre = nombreCarta.getText();

                labelInval.setVisible(false);
                labelCartaAgg.setVisible(false);
                labelError.setVisible(false);

                if(nombre.isBlank() || menuElixir.getSelectedIndex() == 0 || menuRareza.getSelectedIndex() == 0 || menuTipos.getSelectedIndex() == 0){

                    labelInval.setText("Datos invalidos, intente de nuevo");
                    labelInval.setBounds(350, 450, 400, 35);
                    labelInval.setVisible(true);

                } else if (menuElixir.getSelectedIndex() == 11 && menuTipos.getSelectedIndex() != 4) {

                    labelInval.setText("Solo las Tropas de torre pueden tener un costo de elixir de 0");
                    labelInval.setBounds(230, 450, 600, 35);
                    labelInval.setVisible(true);

                } else if (menuElixir.getSelectedIndex() != 11 && menuTipos.getSelectedIndex() == 4) {

                    labelInval.setText("Las Tropas de torre no pueden tener un costo de elixir distinto a 0");
                    labelInval.setBounds(200, 450, 700, 35);
                    labelInval.setVisible(true);

                } else {

                    int elixir = 0;

                    if(menuElixir.getSelectedIndex() != 11){
                        elixir = menuElixir.getSelectedIndex();
                    }

                    String rareza = (String) menuRareza.getSelectedItem();
                    String tipo = (String) menuTipos.getSelectedItem();

                    labelCargando.setVisible(true);

                    Carta carta = new Carta(nombre, elixir, rareza, tipo);
                    dao.agregarCarta(carta);

                    labelCargando.setVisible(false);
                    labelCartaAgg.setVisible(dao.getAccionCompletada());
                    labelError.setVisible(!dao.getAccionCompletada());

                    nombreCarta.setText("");
                    menuElixir.setSelectedIndex(0);
                    menuRareza.setSelectedIndex(0);
                    menuTipos.setSelectedIndex(0);

                }
            });


            btnCancelInsCarta.addActionListener(aeBtnCancelInsCarta -> {

                ventanaInsert.dispose();

            });

        });

    }
}
