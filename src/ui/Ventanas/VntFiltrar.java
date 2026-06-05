package ui.Ventanas;

import dao.CartaDAO;
import model.Carta;
import ui.CreadorComponentes;
import util.ExportadorTXT;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static ui.CreadorComponentes.invalidacion;

public class VntFiltrar {

    CartaDAO dao = new CartaDAO();
    List<Carta>[] listaCartasExprt = new List[1];

    public VntFiltrar() {

        JFrame ventanaFiltrar = CreadorComponentes.crearVentana("Filtrar cartas");
        ventanaFiltrar.setLayout(null);

        JScrollPane[] tablaActual = new JScrollPane[1];

        JLabel titulo = CreadorComponentes.crearLabel("FILTRAR CARTAS", 250, 20, 500, 40, new Font("Arial", Font.BOLD, 28));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        ventanaFiltrar.add(titulo);


        JLabel labelRareza = CreadorComponentes.crearLabel("Rareza:", 80, 90, 200, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaFiltrar.add(labelRareza);

        String[] rarezas = {"Cualquiera", "Común", "Especial", "Épica", "Legendaria", "Campeón"};
        JComboBox<String> menuRareza = CreadorComponentes.crearComboBox(rarezas, 80, 130, 220, 35, new Font("Arial", Font.PLAIN, 14));
        ventanaFiltrar.add(menuRareza);


        JLabel labelTipo = CreadorComponentes.crearLabel("Tipo:", 390, 90, 200, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaFiltrar.add(labelTipo);

        String[] tipos = {"Cualquiera", "Tropa", "Estructura", "Hechizo", "Tropa de torre"};
        JComboBox<String> menuTipo = CreadorComponentes.crearComboBox(tipos, 390, 130, 220, 35, new Font("Arial", Font.PLAIN, 14));
        ventanaFiltrar.add(menuTipo);


        JLabel labelElixir = CreadorComponentes.crearLabel("Elixir:", 700, 90, 200, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaFiltrar.add(labelElixir);

        String[] listElixir = {"Cualquiera", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox<String> menuElixir = CreadorComponentes.crearComboBox(listElixir, 700, 130, 220, 35, new Font("Arial", Font.PLAIN, 14));
        ventanaFiltrar.add(menuElixir);


        JLabel labelInval = CreadorComponentes.crearLabel("", 100, 460, 800, 35, new Font("Arial", Font.PLAIN | Font.ITALIC, 18));
        labelInval.setForeground(Color.RED);
        labelInval.setHorizontalAlignment(SwingConstants.CENTER);
        labelInval.setVisible(false);
        ventanaFiltrar.add(labelInval);


        JLabel labelExport = CreadorComponentes.crearLabel("Archivo exportado correctamente", 100, 460, 800, 35, new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        labelExport.setForeground(Color.GREEN);
        labelExport.setHorizontalAlignment(SwingConstants.CENTER);
        labelExport.setVisible(false);
        ventanaFiltrar.add(labelExport);


        JButton btnFiltrar = CreadorComponentes.crearBoton("Filtrar", 250, 510, 150, 40, new Font("Arial", Font.BOLD, 14));
        ventanaFiltrar.add(btnFiltrar);

        JButton btnExport = CreadorComponentes.crearBoton("Exportar a .txt", 430, 510, 160, 40, new Font("Arial", Font.BOLD, 14));
        ventanaFiltrar.add(btnExport);

        JButton btnVolver = CreadorComponentes.crearBoton("Volver", 620, 510, 150, 40, new Font("Arial", Font.BOLD, 14));
        ventanaFiltrar.add(btnVolver);


        btnFiltrar.addActionListener(aeBtnFiltrar -> {

            labelInval.setVisible(false);
            labelExport.setVisible(false);

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
            listaCartasExprt[0] = cartas;

            if(!dao.getAccionCompletada()){

                invalidacion(labelInval, "Error al conectar con la BD", 100, 460, 800);

            } else if(cartas.isEmpty()){

                invalidacion(labelInval, "No se encontraron cartas", 100, 460, 800);

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


                tablaActual[0] = CreadorComponentes.crearTabla(datos, columnas, 50, 220, 900, 220, 25, new Font("Arial", Font.BOLD, 14), new Font("Arial", Font.PLAIN, 13));
                ventanaFiltrar.add(tablaActual[0]);

                ventanaFiltrar.revalidate();
                ventanaFiltrar.repaint();

            }

        });

        btnExport.addActionListener(aeBtnExport -> {

            labelExport.setVisible(false);
            labelInval.setVisible(false);

            if(listaCartasExprt[0] == null || listaCartasExprt[0].isEmpty()){

                invalidacion(labelInval, "Primero realiza una búsqueda valida", 100, 460, 800);

            } else {

                ExportadorTXT.exportar(listaCartasExprt[0]);

                if(ExportadorTXT.getExportacionCompleta()){

                    labelExport.setVisible(true);

                } else {

                    invalidacion(labelInval, "Error al exportar archivo", 100, 460, 800);

                }
            }

            listaCartasExprt[0] = null;

        });

        btnVolver.addActionListener(aeBtnVolver -> {

            ventanaFiltrar.dispose();

        });

    }

}
