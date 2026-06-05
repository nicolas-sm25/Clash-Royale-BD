package ui.Ventanas;

import dao.CartaDAO;
import model.Carta;
import ui.CreadorComponentes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static ui.CreadorComponentes.invalidacion;

public class VntFiltrar {

    CartaDAO dao = new CartaDAO();

    public VntFiltrar() {

        JFrame ventanaFiltrar = CreadorComponentes.crearVentana("Filtrar cartas");
        ventanaFiltrar.setLayout(null);

        JScrollPane[] tablaActual = new JScrollPane[1];

        JLabel titulo = CreadorComponentes.crearLabel("Filtros", 450, 30, 200, 35, new Font("Arial", Font.BOLD, 24));
        ventanaFiltrar.add(titulo);

        JLabel labelRareza = CreadorComponentes.crearLabel("Rareza:", 50, 100, 150, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaFiltrar.add(labelRareza);

        String[] rarezas = {"Cualquiera", "Común", "Especial", "Épica", "Legendaria", "Campeón"};
        JComboBox<String> menuRareza = CreadorComponentes.crearComboBox(rarezas, 50, 140, 250, 35, new Font("Arial", Font.PLAIN, 14));
        ventanaFiltrar.add(menuRareza);

        JLabel labelTipo = CreadorComponentes.crearLabel("Tipo:", 370, 100, 150, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaFiltrar.add(labelTipo);

        String[] tipos = {"Cualquiera", "Tropa", "Estructura", "Hechizo", "Tropa de torre"};
        JComboBox<String> menuTipo = CreadorComponentes.crearComboBox(tipos, 370, 140, 250, 35, new Font("Arial", Font.PLAIN, 14));
        ventanaFiltrar.add(menuTipo);

        JLabel labelElixir = CreadorComponentes.crearLabel("Elixir:", 690, 100, 150, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaFiltrar.add(labelElixir);

        String[] listElixir = {"Cualquiera", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox<String> menuElixir = CreadorComponentes.crearComboBox(listElixir, 690, 140, 250, 35, new Font("Arial", Font.PLAIN, 14));
        ventanaFiltrar.add(menuElixir);

        JLabel labelInval = CreadorComponentes.crearLabel("", 350, 460, 400, 35, new Font("Arial", Font.PLAIN | Font.ITALIC, 20));
        labelInval.setForeground(Color.RED);
        labelInval.setVisible(false);
        ventanaFiltrar.add(labelInval);

        JButton btnFiltrar = CreadorComponentes.crearBoton("Filtrar", 320, 500, 120, 40, new Font("Arial", Font.BOLD, 14));
        ventanaFiltrar.add(btnFiltrar);

        JButton btnVolver = CreadorComponentes.crearBoton("Volver", 650, 500, 120, 40, new Font("Arial", Font.BOLD, 14));
        ventanaFiltrar.add(btnVolver);

        JButton btnExport = CreadorComponentes.crearBoton("Exportar a .txt", 450, 500, 150, 40, new Font("Arial", Font.BOLD, 14));
        ventanaFiltrar.add(btnExport);


        btnFiltrar.addActionListener(aeBtnFiltrar -> {

            labelInval.setVisible(false);

            if(tablaActual[0] != null){

                ventanaFiltrar.remove(tablaActual[0]);

                ventanaFiltrar.revalidate();
                ventanaFiltrar.repaint();
            }

            String rareza = null;
            String tipo = null;
            Integer elixir = null;


            if(menuRareza.getSelectedIndex() != 0){

                rareza = (String) menuRareza.getSelectedItem();
            }

            if(menuTipo.getSelectedIndex()!=0){

                tipo = (String) menuTipo.getSelectedItem();
            }

            if(menuElixir.getSelectedIndex()!=0){

                elixir = Integer.parseInt((String) menuElixir.getSelectedItem());
            }


            List<Carta> cartas = dao.filtrarCartas(rareza, tipo, elixir);

            if(!dao.getAccionCompletada()){

                invalidacion(labelInval, "Error al conectar con la BD", 390, 460, 400);

            } else if(cartas.isEmpty()){

                invalidacion(labelInval, "No se encontraron cartas", 390, 460, 400);

            } else {

                String[] columnas={"ID", "Nombre", "Elixir", "Rareza", "Tipo"};

                Object[][] datos= new Object[cartas.size()][5];

                for(int i=0;i<cartas.size();i++){

                    Carta carta=cartas.get(i);

                    datos[i][0]=carta.getId();
                    datos[i][1]=carta.getNombre();
                    datos[i][2]=carta.getElixir();
                    datos[i][3]=carta.getRareza();
                    datos[i][4]=carta.getTipo();
                }


                tablaActual[0]= CreadorComponentes.crearTabla(datos, columnas, 50, 220, 900, 220, 25, new Font("Arial", Font.BOLD, 14), new Font("Arial", Font.PLAIN, 13));

                ventanaFiltrar.add(tablaActual[0]);

                ventanaFiltrar.revalidate();
                ventanaFiltrar.repaint();

            }

        });

        btnExport.addActionListener(aeBtnExport -> {

            //Despues lo agrego xdd

        });

        btnVolver.addActionListener(aeBtnVolver -> {

            ventanaFiltrar.dispose();

        });

    }

}