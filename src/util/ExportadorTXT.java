package util;

import model.Carta;

import java.io.*;
import java.util.List;

public class ExportadorTXT {

    private static boolean exportacionCompleta;

    public static void exportar(List<Carta> listaCartas){

        exportacionCompleta = false;

        File archivo = new File("CartasExportadas.txt");

        try(BufferedWriter escritor =
                    new BufferedWriter(
                            new FileWriter(archivo))){

            escritor.write("===== BD CARTAS CLASH ROYALE =====");
            escritor.newLine();
            escritor.newLine();

            for(Carta carta : listaCartas){

                escritor.write(carta.toString());
                escritor.newLine();

            }

            exportacionCompleta = true;

        } catch(IOException e){

            exportacionCompleta = false;

        }
    }

    public static boolean getExportacionCompleta(){

        return exportacionCompleta;

    }
}
