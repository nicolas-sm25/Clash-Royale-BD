package ui.Ventanas;

import dao.CartaDAO;
import model.Carta;
import ui.CreadorComponentes;
import util.ExportadorTXT;

import java.util.List;
import javax.swing.*;
import java.awt.*;

import static ui.CreadorComponentes.invalidacion;

public class VntBuscar {

    CartaDAO dao = new CartaDAO();
    List<Carta>[] listaCartasExprt = new List[1];

    public VntBuscar(){

        JFrame ventanaBuscar = CreadorComponentes.crearVentana("Buscar una carta");
        ventanaBuscar.setLayout(null);

        JScrollPane[] tablaActual = new JScrollPane[1];

        JButton btnBusq = CreadorComponentes.crearBoton("Buscar", 250, 510, 150, 40, new Font("Arial", Font.BOLD, 14));
        ventanaBuscar.add(btnBusq);

        JButton btnExport = CreadorComponentes.crearBoton("Exportar a .txt", 430, 510, 160, 40, new Font("Arial", Font.BOLD, 14));
        ventanaBuscar.add(btnExport);

        JButton btnVolver = CreadorComponentes.crearBoton("Volver", 620, 510, 150, 40, new Font("Arial", Font.BOLD, 14));
        ventanaBuscar.add(btnVolver);


        JLabel labelTituloVentana = CreadorComponentes.crearLabel("BUSCAR CARTAS", 250, 20, 500, 40, new Font("Arial", Font.BOLD, 28));
        labelTituloVentana.setHorizontalAlignment(SwingConstants.CENTER);
        ventanaBuscar.add(labelTituloVentana);


        JLabel labelBusqNombre = CreadorComponentes.crearLabel("Buscar por nombre:", 100, 90, 250, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaBuscar.add(labelBusqNombre);

        JTextField textFieldNombreABusq = CreadorComponentes.crearTextField(100, 130, 300, 35, new Font("Arial", Font.PLAIN, 18));
        ventanaBuscar.add(textFieldNombreABusq);


        JLabel labelBusqID = CreadorComponentes.crearLabel("Buscar por ID:", 600, 90, 250, 35, new Font("Arial", Font.PLAIN, 20));
        ventanaBuscar.add(labelBusqID);

        JTextField textFieldIdABusq = CreadorComponentes.crearTextField(600, 130, 300, 35, new Font("Arial", Font.PLAIN, 18));
        ventanaBuscar.add(textFieldIdABusq);


        JLabel labelTitulo = CreadorComponentes.crearLabel("", 250, 190, 500, 35, new Font("Arial", Font.BOLD, 22));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setVisible(false);
        ventanaBuscar.add(labelTitulo);


        JLabel labelInval = CreadorComponentes.crearLabel("", 100, 460, 800, 35, new Font("Arial", Font.PLAIN | Font.ITALIC, 18));
        labelInval.setForeground(Color.RED);
        labelInval.setHorizontalAlignment(SwingConstants.CENTER);
        labelInval.setVisible(false);
        ventanaBuscar.add(labelInval);


        JLabel labelExport = CreadorComponentes.crearLabel("Archivo exportado correctamente", 100, 460, 800, 35, new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        labelExport.setForeground(Color.GREEN);
        labelExport.setHorizontalAlignment(SwingConstants.CENTER);
        labelExport.setVisible(false);
        ventanaBuscar.add(labelExport);

        btnBusq.addActionListener(aeBtnBusq -> {

            int id = 0;

            labelExport.setVisible(false);
            labelInval.setVisible(false);

            if (textFieldNombreABusq.getText().isEmpty() && textFieldIdABusq.getText().isBlank()){

                invalidacion(labelInval, "Datos invalidos, intente de nuevo", 100, 460, 800);

            } else if (!textFieldNombreABusq.getText().isEmpty() && !textFieldIdABusq.getText().isBlank()){

                invalidacion(labelInval, "Solo se puede realizar una busqueda a la vez", 100, 460, 800);
                textFieldNombreABusq.setText("");
                textFieldIdABusq.setText("");

            } else if(textFieldIdABusq.getText().isBlank()) {

                String nombreABusq = textFieldNombreABusq.getText();

                List<Carta> nombresEncontrados = dao.buscarPorNombre(nombreABusq);
                listaCartasExprt[0] = nombresEncontrados;

                if(dao.getAccionCompletada()){

                    if (nombresEncontrados.isEmpty()) {

                        invalidacion(labelInval, "No se encontraron cartas", 100, 460, 800);
                        textFieldNombreABusq.setText("");

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

                        tablaActual[0] = CreadorComponentes.crearTabla(cartasEnc, columnasTabla, 50, 230, 900, 200, 25, new Font("Arial", Font.BOLD, 14), new Font("Arial", Font.PLAIN, 13));                        ventanaBuscar.add(tablaActual[0]);
                        ventanaBuscar.revalidate();
                        ventanaBuscar.repaint();

                        textFieldNombreABusq.setText("");

                    }

                } else {

                    invalidacion(labelInval, "Error al mostrar cartas", 100, 460, 800);

                }

                } else {

                try {
                    String idAux = textFieldIdABusq.getText();
                    id = Integer.parseInt(idAux);
                } catch (NumberFormatException e){

                    invalidacion(labelInval, "Datos invalidos, intente de nuevo", 100, 460, 800);
                    textFieldIdABusq.setText("");
                    return;
                }

                if (id <= 0){

                    invalidacion(labelInval, "ID invalida, intente de nuevo", 100, 460, 800);
                    textFieldIdABusq.setText("");

                } else {

                    if(tablaActual[0] != null){

                        labelTitulo.setVisible(false);

                        ventanaBuscar.remove(tablaActual[0]);

                        ventanaBuscar.revalidate();
                        ventanaBuscar.repaint();

                    }

                    Carta idEncontrada = dao.buscarPorId(id);


                    if (dao.getAccionCompletada()){

                        if (idEncontrada == null) {

                            invalidacion(labelInval, "No se encontró carta con esa ID", 100, 460, 800);
                            textFieldIdABusq.setText("");

                        } else {

                            listaCartasExprt[0] = List.of(idEncontrada);

                            String[] columnasTabla = {"ID", "Nombre", "Elixir", "Rareza", "Tipo"};

                            Object[][] idEnc = new Object[1][5];
                            idEnc[0][0] = idEncontrada.getId();
                            idEnc[0][1] = idEncontrada.getNombre();
                            idEnc[0][2] = idEncontrada.getElixir();
                            idEnc[0][3] = idEncontrada.getRareza();
                            idEnc[0][4] = idEncontrada.getTipo();

                            labelTitulo.setText("Carta encontrada");
                            labelTitulo.setVisible(true);

                            tablaActual[0] = CreadorComponentes.crearTabla(idEnc, columnasTabla, 50, 260, 900, 50, 25, new Font("Arial", Font.BOLD, 14), new Font("Arial", Font.PLAIN, 13));                            ventanaBuscar.add(tablaActual[0]);
                            ventanaBuscar.revalidate();
                            ventanaBuscar.repaint();

                            textFieldIdABusq.setText("");

                        }

                    } else {

                        invalidacion(labelInval, "Error al mostrar cartas", 100, 460, 800);

                    }
                }
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

            ventanaBuscar.dispose();

        });
    }
}