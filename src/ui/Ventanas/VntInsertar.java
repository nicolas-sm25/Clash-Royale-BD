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

        JButton btnInsCarta = CreadorComponentes.crearBoton("Insertar carta", 300, 500, 200, 40, new Font("Arial", Font.BOLD, 14));
        ventanaInsert.add(btnInsCarta);

        JButton btnVolver = CreadorComponentes.crearBoton("Volver", 600, 500, 100, 40, new Font("Arial", Font.BOLD, 14));
        ventanaInsert.add(btnVolver);


        JLabel labelNombreCarta = CreadorComponentes.crearLabel("Nombre de la carta:", 50, 30, 300, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaInsert.add(labelNombreCarta);

        JTextField textFieldNombreCarta = CreadorComponentes.crearTextField(50, 70, 300, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaInsert.add(textFieldNombreCarta);

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

        JLabel labelCartaAgg = CreadorComponentes.crearLabel("Carta agregada correctamente", 350, 450, 400, 35, new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        labelCartaAgg.setForeground(Color.GREEN);
        labelCartaAgg.setVisible(false);
        ventanaInsert.add(labelCartaAgg);

        btnInsCarta.addActionListener(aeBtnInsCarta -> {

            labelInval.setVisible(false);
            labelCartaAgg.setVisible(false);

            if(textFieldNombreCarta.getText().isBlank() || menuElixir.getSelectedIndex() == 0 || menuRareza.getSelectedIndex() == 0 || menuTipos.getSelectedIndex() == 0){

                invalidacion(labelInval, "Datos invalidos, intente de nuevo", 350, 450, 400);

            } else if (menuElixir.getSelectedIndex() == 11 && menuTipos.getSelectedIndex() != 4) {

                invalidacion(labelInval, "Solo las Tropas de torre pueden tener un costo de elixir de 0", 230, 450, 600);

            } else if (menuElixir.getSelectedIndex() != 11 && menuTipos.getSelectedIndex() == 4) {

                invalidacion(labelInval, "Las Tropas de torre no pueden tener un costo de elixir distinto a 0", 200, 450, 700);

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

                    invalidacion(labelInval, "Error al agregar carta", 350, 450, 400);

                }

            }
        });

        btnVolver.addActionListener(aeBtnVolver -> {

            ventanaInsert.dispose();

        });
    }
}
