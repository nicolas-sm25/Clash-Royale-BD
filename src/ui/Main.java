package ui;

import dao.CartaDAO;
import model.Carta;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CartaDAO dao = new CartaDAO();

        Scanner leer =  new Scanner(System.in);

        System.out.println("Ingrese el nombre de la carta");
        String nombre = leer.nextLine();
        System.out.println("Ingrese el costo de elixir");
        int elixir = leer.nextInt();
        leer.nextLine();
        System.out.println("Ingrese la rareza");
        String rareza = leer.nextLine();
        System.out.println("Ingrese el tipo");
        String tipo = leer.nextLine();
        Carta carta = new Carta(nombre, elixir, rareza, tipo);

        dao.insertarCarta(carta);
    }

}