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

            JFrame ventanaInsert = CreadorComponentes.crearVentana("Insertar una carta");

            ventanaInsert.setLayout(null);

            JButton btnInsCarta = CreadorComponentes.crearBoton("Insertar carta", 300, 500, 200, 40, new Font("Arial", Font.BOLD, 14));
            ventanaInsert.add(btnInsCarta);

            JButton btnCancelInsCarta = CreadorComponentes.crearBoton("Volver", 600, 500, 100, 40, new Font("Arial", Font.BOLD, 14));
            ventanaInsert.add(btnCancelInsCarta);


            JLabel labelNombreCarta = CreadorComponentes.crearLabel("Nombre de la carta:", 50, 30, 300, 35, new Font("Arial", Font.PLAIN, 20));
            ventanaInsert.add(labelNombreCarta);

            JTextField nombreCarta = new JTextField();
            nombreCarta.setBounds(50, 70, 300, 35);
            nombreCarta.setFont(new Font("Arial", Font.PLAIN, 20));
            ventanaInsert.add(nombreCarta);

            JLabel labelElixir = CreadorComponentes.crearLabel("Costo de elixir:", 50, 200, 300, 35, new Font("Arial", Font.PLAIN, 20));
            ventanaInsert.add(labelElixir);

            String[] costos = {"Selecciona una opción...", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "0 (Solo tropas de torre)"};
            JComboBox<String> menuElixir = CreadorComponentes.crearComboBox(costos, 50, 240, 300, 35, new Font("Arial", Font.PLAIN, 14));
            ventanaInsert.add(menuElixir);

            JLabel labelRareza = CreadorComponentes.crearLabel("Rareza:", 600, 30, 300, 35, new Font("Arial", Font.PLAIN, 20));
            ventanaInsert.add(labelRareza);

            String[] rarezas = {"Selecciona una opción...", "Común", "Especial", "Épica", "Legendaria", "Campeón"};
            JComboBox<String> menuRareza = CreadorComponentes.crearComboBox(rarezas, 600, 70, 300, 35, new Font("Arial", Font.PLAIN, 14));
            ventanaInsert.add(menuRareza);

            JLabel labelTipo = CreadorComponentes.crearLabel("Tipo:", 600, 200, 300, 35, new Font("Arial", Font.PLAIN, 20));
            ventanaInsert.add(labelTipo);

            String[] tipos = {"Selecciona una opción...", "Tropa", "Estructura", "Hechizo", "Tropa de torre"};
            JComboBox<String> menuTipos = CreadorComponentes.crearComboBox(tipos, 600, 240, 300, 35, new Font("Arial", Font.PLAIN, 14));
            ventanaInsert.add(menuTipos);

            JLabel labelInval = CreadorComponentes.crearLabel("", 350, 450, 400, 35, new Font("Arial", Font.PLAIN | Font.ITALIC, 20));
            labelInval.setForeground(Color.RED);
            labelInval.setVisible(false);
            ventanaInsert.add(labelInval);

            JLabel labelCargando = CreadorComponentes.crearLabel("Cargando...", 350, 450, 400, 35, new Font("Arial", Font.ITALIC, 20));
            labelCargando.setVisible(false);
            ventanaInsert.add(labelCargando);

            JLabel labelCartaAgg = CreadorComponentes.crearLabel("Carta agregada correctamente", 350, 450, 400, 35, new Font("Arial", Font.BOLD | Font.ITALIC, 20));
            labelCartaAgg.setForeground(Color.GREEN);
            labelCartaAgg.setVisible(false);
            ventanaInsert.add(labelCartaAgg);

            JLabel labelError = CreadorComponentes.crearLabel("Error al agregar carta", 350, 450, 400, 35, new Font("Arial", Font.BOLD | Font.ITALIC, 20));
            labelError.setForeground(Color.RED);
            labelError.setVisible(false);
            ventanaInsert.add(labelError);

            btnInsCarta.addActionListener(aeBtnInsCarta -> {

                labelInval.setVisible(false);
                labelCartaAgg.setVisible(false);
                labelError.setVisible(false);

                if(nombreCarta.getText().isBlank() || menuElixir.getSelectedIndex() == 0 || menuRareza.getSelectedIndex() == 0 || menuTipos.getSelectedIndex() == 0){

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

                    String nombre = nombreCarta.getText();
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
