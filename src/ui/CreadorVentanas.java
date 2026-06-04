package ui;

import javax.swing.JFrame;

public class CreadorVentanas extends JFrame {

    //Crear ventanas

    public static JFrame creadorVentanas(String titulo) {

        JFrame ventana = new JFrame();

        ventana.setTitle(titulo);
        ventana.setSize(1000,600);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setVisible(true);

        return ventana;
    }
}
