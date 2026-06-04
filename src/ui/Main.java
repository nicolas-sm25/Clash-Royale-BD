package ui;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame ventanaPrinc = CreadorComponentes.crearVentana("BD cartas de Clash royale");

        ventanaPrinc.add(new Botones());
        ventanaPrinc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}