package ui.Ventanas;

import dao.CartaDAO;
import model.Carta;
import ui.CreadorComponentes;

import java.util.List;
import javax.swing.*;
import java.awt.*;

import static ui.CreadorComponentes.invalidacion;

public class VntBuscar {

    CartaDAO dao = new CartaDAO();

    public VntBuscar(){

        JFrame ventanaBuscar = CreadorComponentes.crearVentana("Buscar una carta");
        ventanaBuscar.setLayout(null);

        JScrollPane[] tablaActual = new JScrollPane[1];

        JButton btnBusq = CreadorComponentes.crearBoton("Buscar", 320, 500, 100, 40, new Font("Arial", Font.BOLD, 14));
        ventanaBuscar.add(btnBusq);

        JButton btnExport = CreadorComponentes.crearBoton("Exportar a .txt", 450, 500, 150, 40, new Font("Arial", Font.BOLD, 14));
        ventanaBuscar.add(btnExport);

        JButton btnVolver = CreadorComponentes.crearBoton("Volver", 630, 500, 100, 40, new Font("Arial", Font.BOLD, 14));
        ventanaBuscar.add(btnVolver);

        JLabel labelBusqNombre = CreadorComponentes.crearLabel("Buscar por nombre:", 50, 30, 300, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaBuscar.add(labelBusqNombre);

        JTextField textFieldNombreABusq = CreadorComponentes.crearTextField(50, 70, 300, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaBuscar.add(textFieldNombreABusq);

        JLabel labelBusqID = CreadorComponentes.crearLabel("Buscar por ID:", 600, 30, 300, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaBuscar.add(labelBusqID);

        JTextField textFieldIdABusq = CreadorComponentes.crearTextField(600, 70, 300, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaBuscar.add(textFieldIdABusq);

        JLabel labelTitulo = CreadorComponentes.crearLabel("", 400, 120, 300, 35, new Font("Arial", Font.BOLD, 24));
        labelTitulo.setVisible(false);
        ventanaBuscar.add(labelTitulo);

        JLabel labelInval = CreadorComponentes.crearLabel("", 350, 460, 400, 35, new Font("Arial", Font.PLAIN | Font.ITALIC, 20));
        labelInval.setForeground(Color.RED);
        labelInval.setVisible(false);
        ventanaBuscar.add(labelInval);

        btnBusq.addActionListener(aeBtnBusq -> {

            int id = 0;

            labelInval.setVisible(false);

            if (textFieldNombreABusq.getText().isEmpty() && textFieldIdABusq.getText().isBlank()){

                invalidacion(labelInval, "Datos invalidos, intente de nuevo", 350, 460, 400);

            } else if (!textFieldNombreABusq.getText().isEmpty() && !textFieldIdABusq.getText().isBlank()){

                invalidacion(labelInval, "Solo se puede realizar una busqueda a la vez", 300, 460, 500);

            } else if(textFieldIdABusq.getText().isBlank()) {

                String nombreABusq = textFieldNombreABusq.getText();

                List<Carta> nombresEncontrados = dao.buscarPorNombre(nombreABusq);

                if (nombresEncontrados.isEmpty()) {

                    invalidacion(labelInval, "No se encontraron cartas", 400, 460, 400);

                } else {

                    if(tablaActual[0] != null){

                        labelTitulo.setVisible(false);

                        ventanaBuscar.remove(tablaActual[0]);

                        ventanaBuscar.revalidate();
                        ventanaBuscar.repaint();

                    }

                    String[] columnasTabla = {"ID", "Nombre", "Elixir", "Rareza", "Tipo"};

                    Object[][] cartasEnc = new Object[nombresEncontrados.size()][5];

                        for(int i = 0; i < nombresEncontrados.size(); i++){

                            Carta carta = nombresEncontrados.get(i);

                            cartasEnc[i][0] = carta.getId();
                            cartasEnc[i][1] = carta.getNombre();
                            cartasEnc[i][2] = carta.getElixir();
                            cartasEnc[i][3] = carta.getRareza();
                            cartasEnc[i][4] = carta.getTipo();
                        }

                        labelTitulo.setText("Cartas encontradas");
                        labelTitulo.setVisible(true);

                        tablaActual[0] = CreadorComponentes.crearTabla(cartasEnc,columnasTabla, 50, 160, 900, 300, 25, new Font("Arial", Font.BOLD, 14), new Font("Arial", Font.PLAIN, 13));
                        ventanaBuscar.add(tablaActual[0]);
                        ventanaBuscar.revalidate();
                        ventanaBuscar.repaint();

                    }

                } else {

                try {
                    String idAux = textFieldIdABusq.getText();
                    id = Integer.parseInt(idAux);
                } catch (NumberFormatException e){

                    invalidacion(labelInval, "Datos invalidos, intente de nuevo", 350, 460, 400);
                    return;
                }

                if (id <= 0){

                    invalidacion(labelInval, "ID invalida, intente de nuevo", 350, 460, 400);

                } else {

                    if(tablaActual[0] != null){

                        labelTitulo.setVisible(false);

                        ventanaBuscar.remove(tablaActual[0]);

                        ventanaBuscar.revalidate();
                        ventanaBuscar.repaint();

                    }

                    Carta idEncontrada = dao.buscarPorId(id);

                    if (idEncontrada == null) {

                        invalidacion(labelInval, "No se encontró carta con esa ID", 370, 460, 400);

                    } else {

                        String[] columnasTabla = {"ID", "Nombre", "Elixir", "Rareza", "Tipo"};

                        Object[][] idEnc = new Object[1][5];
                        idEnc[0][0] = idEncontrada.getId();
                        idEnc[0][1] = idEncontrada.getNombre();
                        idEnc[0][2] = idEncontrada.getElixir();
                        idEnc[0][3] = idEncontrada.getRareza();
                        idEnc[0][4] = idEncontrada.getTipo();

                        labelTitulo.setText("Carta encontrada");
                        labelTitulo.setVisible(true);

                        tablaActual[0] = CreadorComponentes.crearTabla(idEnc,columnasTabla, 50, 250, 900, 49, 25, new Font("Arial", Font.BOLD, 14), new Font("Arial", Font.PLAIN, 13));
                        ventanaBuscar.add(tablaActual[0]);
                        ventanaBuscar.revalidate();
                        ventanaBuscar.repaint();

                    }
                }
            }

        });

        btnExport.addActionListener(aeBtnExport -> {

            //Despues lo agrego xdd

        });

        btnVolver.addActionListener(aeBtnVolver -> {

            ventanaBuscar.dispose();

        });
    }
}
