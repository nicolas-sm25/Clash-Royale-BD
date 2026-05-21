package ui;

import dao.CartaDAO;
import model.Carta;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CartaDAO dao = new CartaDAO();
        Scanner leer =  new Scanner(System.in);

        while (true) {
            System.out.println("\n===== BASE DE DATOS CARTAS DE CLASH ROYALE =====");
            System.out.println("1. Insertar carta");
            System.out.println("2. Consultar todas");
            System.out.println("3. Filtrar");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            int opcion = leer.nextInt();
            leer.nextLine();
            switch (opcion) {
                case 1:
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
                    break;

                case 2:
                    List<Carta> cartas = dao.listarCartas();

                    System.out.println("\n=== TABLA COMPLETA DE CARTAS ===");

                    for (Carta c : cartas) {
                        System.out.println(c);
                    }


                    break;

                case 3:

                    break;

                case 4:
                    System.out.println("Bye bye");
                    return;

                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }


    }

}