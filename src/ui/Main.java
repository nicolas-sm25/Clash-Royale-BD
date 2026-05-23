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
            System.out.println("4. Filtrar");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            int opPrinc = leer.nextInt();
            leer.nextLine();

            switch (opPrinc) {

                case 1:

                    System.out.println("\nIngrese el nombre de la carta");
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

                    List<Carta> listaCartas = dao.listaCartas();

                    System.out.println("\n=== TABLA COMPLETA DE CARTAS ===");

                    for (Carta listCAux : listaCartas) {
                        System.out.println(listCAux);
                    }

                    break;

                case 3:
                    System.out.println("\nBuscar carta por:");
                    System.out.println("1. ID");
                    System.out.println("2. Nombre");
                    System.out.print("Opción: ");

                    int opBuscar = leer.nextInt();
                    leer.nextLine();

                    switch (opBuscar) {

                        case 1:

                            System.out.print("\nIngrese ID: ");
                            int idAEncontrar = leer.nextInt();
                            leer.nextLine();

                            Carta cartaEncontrada = dao.buscarPorId(idAEncontrar);
                            System.out.print("\n");

                            if (cartaEncontrada != null) {

                                System.out.println(cartaEncontrada);

                            } else {

                                System.out.println("\nNo existe carta con ese ID");

                            }

                            break;


                        case 2:

                            System.out.print("\nIngrese el nombre a buscar: ");
                            String nombreABuscar = leer.nextLine();

                            List<Carta> nombresEncontrados = dao.buscarPorNombre(nombreABuscar);

                            if (nombresEncontrados.isEmpty()) {

                                System.out.println("\nNo se encontraron cartas");

                            } else {

                                System.out.print("\n");

                                for (Carta nomEncAUX : nombresEncontrados) {

                                    System.out.println(nomEncAUX);

                                } }

                            break;

                        default:
                            System.out.println("\nOpción inválida");
                    }
                    break;

                case 4:

                    System.out.println("\nFiltrar por:");
                    System.out.println("1. Elixir");
                    System.out.println("2. Rareza");
                    System.out.println("3. Tipo");
                    System.out.print("Opción: ");

                    int opFiltrar = leer.nextInt();
                    leer.nextLine();

                    switch (opFiltrar) {

                        case 1:

                            System.out.print("\nOperador (=, <, >, <=, >=): ");
                            String operadorElx = leer.nextLine();

                            if(!operadorElx.equals("=") && !operadorElx.equals("<") && !operadorElx.equals(">") && !operadorElx.equals("<=") && !operadorElx.equals(">=")){

                                System.out.println("\nOperador inválido");

                                break;
                            }

                            System.out.print("Elixir " + operadorElx + " ");
                            int ElixirABuscar = leer.nextInt();
                            leer.nextLine();

                            List<Carta> elxEncontrado = dao.encontrarElixir(operadorElx, ElixirABuscar);

                            if (elxEncontrado.isEmpty()) {

                                System.out.println("\nNo se encontraron cartas");

                            } else {

                                System.out.print("\n");

                                for (Carta elxIgEncAUX : elxEncontrado) {

                                    System.out.println(elxIgEncAUX);

                                } }

                         break;

                        case 2:

                            System.out.println("\n1. Común");
                            System.out.println("2. Especial");
                            System.out.println("3. Épica");
                            System.out.println("4. Legendaria");
                            System.out.println("5. Campeón");
                            System.out.print("Opción: ");

                            int opRareza = leer.nextInt();
                            leer.nextLine();

                            switch (opRareza) {

                                case 1:

                                    List<Carta> rarezaComúnEncontrada = dao.encontrarRareza("Común");

                                    System.out.print("\n");

                                        for (Carta raComEncAUX : rarezaComúnEncontrada) {

                                            System.out.println(raComEncAUX);

                                        }
                                    break;


                                case 2:

                                    List<Carta> rarezaEspecialEncontrada = dao.encontrarRareza("Especial");

                                    System.out.print("\n");

                                    for (Carta raEspEncAUX : rarezaEspecialEncontrada) {

                                        System.out.println(raEspEncAUX);

                                    }
                                    break;



                                case 3:

                                    List<Carta> rarezaEpicaEncontrada = dao.encontrarRareza("Épica");

                                    System.out.print("\n");

                                    for (Carta raEpcEncAUX : rarezaEpicaEncontrada) {

                                        System.out.println(raEpcEncAUX);

                                    }
                                    break;



                                case 4:

                                    List<Carta> rarezaLegendariaEncontrada = dao.encontrarRareza("Legendaria");

                                    System.out.print("\n");

                                    for (Carta raLegEncAUX : rarezaLegendariaEncontrada) {

                                        System.out.println(raLegEncAUX);

                                    }
                                    break;


                                case 5:

                                    List<Carta> rarezaCampeonEncontrada = dao.encontrarRareza("Campeón");

                                    System.out.print("\n");

                                    for (Carta raCamEncAUX : rarezaCampeonEncontrada) {

                                        System.out.println(raCamEncAUX);

                                    }
                                    break;


                                default:
                                    System.out.println("\nOpción inválida, intente de nuevo.");
                            }

                                break;

                        case 3:

                            System.out.println("\n1. Tropa");
                            System.out.println("2. Estructura");
                            System.out.println("3. Hechizo");
                            System.out.println("4. Tropa de torre");
                            System.out.print("Opción: ");

                            int opTipo = leer.nextInt();
                            leer.nextLine();

                            switch (opTipo) {

                                case 1:

                                    List<Carta> tipoTropaEncontrada = dao.encontrarTipo("Tropa");

                                    System.out.print("\n");

                                    for (Carta tiTroEncAUX : tipoTropaEncontrada) {

                                        System.out.println(tiTroEncAUX);

                                    }

                                    break;


                                case 2:

                                    List<Carta> tipoEstructuraEncontrada = dao.encontrarTipo("Estructura");

                                    System.out.print("\n");

                                    for (Carta tiEstEncAUX : tipoEstructuraEncontrada) {

                                        System.out.println(tiEstEncAUX);

                                    }

                                    break;



                                case 3:

                                    List<Carta> tipoHechizoEncontrada = dao.encontrarTipo("Hechizo");

                                    System.out.print("\n");

                                    for (Carta tiHecEncAUX : tipoHechizoEncontrada) {

                                        System.out.println(tiHecEncAUX);

                                    }

                                    break;



                                case 4:

                                    List<Carta> tipoTroTorreEncontrada = dao.encontrarTipo("Tropa de torre");

                                    System.out.print("\n");

                                    for (Carta tiTdTEncAUX : tipoTroTorreEncontrada) {

                                        System.out.println(tiTdTEncAUX);

                                    }

                                    break;



                                default:
                                    System.out.println("\nOpción inválida, intente de nuevo.");
                            }

                            break;


                        default:
                            System.out.println("\nOpción inválida, intente de nuevo.");
                    }

                    break;

                case 5:

                    System.out.println("\nBye bye");

                    return;


                default:
                    System.out.println("\nOpción inválida, intente de nuevo.");
            }
        }
    }
}