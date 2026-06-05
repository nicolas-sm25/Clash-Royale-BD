package ui;

import javax.swing.*;
import java.awt.*;

public class CreadorComponentes {

    //Crear ventanas

    public static JFrame crearVentana(String titulo) {

        JFrame ventana = new JFrame();

        ventana.setTitle(titulo);
        ventana.setSize(1000,600);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setVisible(true);

        return ventana;
    }

    //Crear botones

    public static JButton crearBoton(String nombre, int x, int y, int width, int height, Font fuente) {

        JButton boton = new JButton(nombre);

        boton.setBounds(x, y, width, height);
        boton.setFont(fuente);

        return boton;
    }

    //Crear labels

    public static JLabel crearLabel(String nombre, int x, int y, int width, int height, Font fuente) {

        JLabel label = new JLabel(nombre);

        label.setBounds(x, y, width, height);
        label.setFont(fuente);

        return label;
    }

    //Crear comboBox

    public static JComboBox<String> crearComboBox(String[] datos, int x, int y, int width, int height, Font fuente) {

        JComboBox<String> comboBox = new JComboBox<>(datos);

        comboBox.setBounds(x, y, width, height);
        comboBox.setFont(fuente);

        return comboBox;
    }

    //Crear textFields

    public static JTextField crearTextField(int x, int y, int width, int height, Font fuente) {

        JTextField textField = new JTextField();

        textField.setBounds(x, y, width, height);
        textField.setFont(fuente);

        return textField;
    }

    //Crear tablas con scroll

    public static JScrollPane crearTabla(Object[][] datos, String[] columnas, int x, int y, int width, int height, int row, Font fuenteTitulo, Font fuenteTexto) {

        JTable tabla = new JTable(datos, columnas);

        tabla.setRowHeight(row);
        tabla.getTableHeader().setFont(fuenteTitulo);
        tabla.setFont(fuenteTexto);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(x, y, width, height);

        return scroll;
    }

    //Designar mensajes de invalidacion

    public static JLabel invalidacion(JLabel label, String mensaje, int x, int y, int width) {

        label.setText(mensaje);
        label.setBounds(x, y, width, 35);
        label.setVisible(true);

        return label;
    }

}
