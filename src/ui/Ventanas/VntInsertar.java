package ui.Ventanas;

import dao.CartaDAO;
import model.Carta;
import ui.CreadorComponentes;

import javax.swing.*;
import java.awt.*;

import static ui.CreadorComponentes.invalidacion;

public class VntInsertar {

    CartaDAO dao = new CartaDAO();

    public VntInsertar(){

        JFrame ventanaInsert = CreadorComponentes.crearVentana("Insertar una carta");
        ventanaInsert.setLayout(null);

        JLabel labelTitulo = CreadorComponentes.crearLabel("INSERTAR CARTA", 350, 20, 400, 40, new Font("Arial", Font.BOLD, 28));
        ventanaInsert.add(labelTitulo);

        JLabel labelNombreCarta = CreadorComponentes.crearLabel("Nombre de la carta:", 50, 80, 300, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaInsert.add(labelNombreCarta);

        JTextField textFieldNombreCarta = CreadorComponentes.crearTextField(50, 120, 300, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaInsert.add(textFieldNombreCarta);

        JLabel labelElixir = CreadorComponentes.crearLabel("Costo de elixir:", 50, 250, 300, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaInsert.add(labelElixir);

        String[] costos = {"Selecciona una opción...", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "0 (Solo tropas de torre)"};
        JComboBox<String> menuElixir = CreadorComponentes.crearComboBox(costos, 50, 290, 300, 35, new Font("Arial", Font.PLAIN, 14));
        ventanaInsert.add(menuElixir);

        JLabel labelRareza = CreadorComponentes.crearLabel("Rareza:", 650, 80, 300, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaInsert.add(labelRareza);

        String[] rarezas = {"Selecciona una opción...", "Común", "Especial", "Épica", "Legendaria", "Campeón"};
        JComboBox<String> menuRareza = CreadorComponentes.crearComboBox(rarezas, 650, 120, 250, 35, new Font("Arial", Font.PLAIN, 14));
        ventanaInsert.add(menuRareza);

        JLabel labelTipo = CreadorComponentes.crearLabel("Tipo:", 650, 250, 300, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaInsert.add(labelTipo);

        String[] tipos = {"Selecciona una opción...", "Tropa", "Estructura", "Hechizo", "Tropa de torre"};
        JComboBox<String> menuTipos = CreadorComponentes.crearComboBox(tipos, 650, 290, 250, 35, new Font("Arial", Font.PLAIN, 14));
        ventanaInsert.add(menuTipos);

        JLabel labelInval = CreadorComponentes.crearLabel("", 200, 400, 600, 35, new Font("Arial", Font.PLAIN | Font.ITALIC, 18));
        labelInval.setForeground(Color.RED);
        labelInval.setHorizontalAlignment(SwingConstants.CENTER);
        labelInval.setVisible(false);
        ventanaInsert.add(labelInval);

        JLabel labelCartaAgg = CreadorComponentes.crearLabel("Carta agregada correctamente", 100, 400, 800, 35, new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        labelCartaAgg.setForeground(Color.GREEN);
        labelInval.setHorizontalAlignment(SwingConstants.CENTER);
        labelCartaAgg.setVisible(false);
        ventanaInsert.add(labelCartaAgg);

        JButton btnInsCarta = CreadorComponentes.crearBoton("Insertar carta", 300, 490, 170, 45, new Font("Arial", Font.BOLD, 14));
        ventanaInsert.add(btnInsCarta);

        JButton btnVolver = CreadorComponentes.crearBoton("Volver", 530, 490, 170, 45, new Font("Arial", Font.BOLD, 14));
        ventanaInsert.add(btnVolver);

        btnInsCarta.addActionListener(aeBtnInsCarta -> {

            labelInval.setVisible(false);
            labelCartaAgg.setVisible(false);

            if(textFieldNombreCarta.getText().isBlank() || menuElixir.getSelectedIndex() == 0 || menuRareza.getSelectedIndex() == 0 || menuTipos.getSelectedIndex() == 0){

                invalidacion(labelInval, "Datos invalidos, intente de nuevo", 100, 400, 800);

            } else if (menuElixir.getSelectedIndex() == 11 && menuTipos.getSelectedIndex() != 4) {

                invalidacion(labelInval, "Solo las Tropas de torre pueden tener un costo de elixir de 0", 100, 400, 800);

            } else if (menuElixir.getSelectedIndex() != 11 && menuTipos.getSelectedIndex() == 4) {

                invalidacion(labelInval, "Las Tropas de torre no pueden tener un costo de elixir distinto a 0", 100, 400, 800);

            } else {

                String nombre = textFieldNombreCarta.getText();
                int elixir = 0;

                if(menuElixir.getSelectedIndex() != 11){
                    elixir = menuElixir.getSelectedIndex();
                }

                String rareza = (String) menuRareza.getSelectedItem();
                String tipo = (String) menuTipos.getSelectedItem();


                Carta carta = new Carta(nombre, elixir, rareza, tipo);
                dao.agregarCarta(carta);

                if (dao.getAccionCompletada()){

                    labelCartaAgg.setVisible(true);

                    textFieldNombreCarta.setText("");
                    menuElixir.setSelectedIndex(0);
                    menuRareza.setSelectedIndex(0);
                    menuTipos.setSelectedIndex(0);

                } else {

                    invalidacion(labelInval, "Error al agregar carta", 100, 400, 800);
                }

            }
        });

        btnVolver.addActionListener(aeBtnVolver -> {

            ventanaInsert.dispose();

        });
    }
}