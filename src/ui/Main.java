package ui;

import dao.CartaDAO;
import model.Carta;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main(String[] args ) {
        CartaDAO dao = new CartaDAO();
        Scanner leer =  new Scanner(System.in);

        while (true) {
            System.out.println("\n===== BASE DE DATOS CARTAS DE CLASH ROYALE =====");
            System.out.println("1. Insertar carta");
            System.out.println("2. Consultar todas");
            System.out.println("3. Buscar una carta");
            System.out.println("4. Buscar con filtros");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            int op1 = leer.nextInt();
            leer.nextLine();

            switch (op1) {

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
                    dao.agregarCarta(carta);
                    break;

                case 2:
                    List<Carta> cartas = dao.listarCartas();
                    System.out.println("\n=== TABLA COMPLETA DE CARTAS ===");
                    for (Carta c : cartas) {
                        System.out.println(c);
                    }
                    break;

                case 3:
                    System.out.println("\nBuscar carta por:");
                    System.out.println("1. ID");
                    System.out.println("2. Nombre");
                    System.out.print("Opción: ");

                    int op2 = leer.nextInt();
                    leer.nextLine();

                    switch (op2) {
                        case 1:
                            System.out.print("Ingrese ID: ");
                            int idAEncontrar = leer.nextInt();
                            leer.nextLine();
                            Carta c = dao.buscarPorId(idAEncontrar);
                            if (c != null) {
                                System.out.println(c);
                            } else {
                                System.out.println("No existe carta con ese ID");
                            }
                            break;

                        case 2:
                            System.out.print("Ingrese nombre: ");
                            String nombreABuscar = leer.nextLine();
                            List<Carta> resultados = dao.buscarPorNombre(nombreABuscar);

                            if (resultados.isEmpty()) {
                                System.out.println("No se encontraron cartas");
                            } else {
                                for (Carta cartaEncontrada : resultados) {
                                    System.out.println(cartaEncontrada);
                                }
                            }
                            break;

                        default:
                            System.out.println("Opción inválida");
                    }
                    break;

                case 4:

                break;

                case 5:
                    System.out.println("Bye bye");
                    return;

                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
    }
}