package ui;

import dao.CartaDAO;
import model.Carta;
import java.util.List;
import java.util.Scanner;

public class Main {


    // Metodo para validar si una variable recibe un int
    public static int validarInt(Scanner leer) {

        while (!leer.hasNextInt()) {

            System.out.print("Debe ingresar un número, intente de nuevo: ");
            leer.nextLine();

        }

        int numero = leer.nextInt();
        leer.nextLine();

        return numero;
    }


    static void main() {

        CartaDAO dao = new CartaDAO();
        Scanner leer =  new Scanner(System.in);

        while (true) {

            System.out.println("\n===== BASE DE DATOS CARTAS DE CLASH ROYALE =====");
            System.out.println("1. Insertar carta a la BD");
            System.out.println("2. Mostrar BD");
            System.out.println("3. Buscar una carta");
            System.out.println("4. Filtrar");
            System.out.println("5. Salir");

            System.out.print("Opción: ");
            int opPrinc = validarInt(leer);

            switch (opPrinc) {

                case 1:

                    System.out.println("\nIngrese el nombre de la carta:");
                    String nombre = leer.nextLine();

                    System.out.println("Ingrese el costo de elixir (entre 0 y 10, 0 exclusivamente para las Tropas de torre):");

                    int elixir;

                    while (true) {

                        elixir = validarInt(leer);

                        if (elixir >= 0 && elixir <= 10) {

                            break;

                        } else {

                            System.out.println("Elixir inválido, intente de nuevo: ");

                        }
                    }

                    System.out.println("Ingrese la rareza (Común, Especial, Épica, Legendaria, Campeón):");

                    String rareza;

                    while (true) {

                        rareza = leer.nextLine();

                        if (!rareza.equals("Común") && !rareza.equals("Especial") && !rareza.equals("Épica") && !rareza.equals("Legendaria") && !rareza.equals("Campeón")) {

                            System.out.println("Rareza inválida, intente de nuevo: ");

                        } else {

                            break;

                        }
                    }

                    System.out.println("Ingrese el tipo (Tropa, Estructura, Hechizo, Tropa de torre):");

                    String tipo;

                    while (true) {

                        tipo = leer.nextLine();

                        if (!tipo.equals("Tropa") && !tipo.equals("Estructura") && !tipo.equals("Hechizo") && !tipo.equals("Tropa de torre")) {

                            System.out.println("Tipo inválido, intente de nuevo: ");
                        } else {

                            break;

                        }
                    }

                    if (elixir == 0 && !tipo.equals("Tropa de torre")) {

                        System.out.println("\nSolo las Tropas de torre pueden tener un costo de elixir de 0.");

                    } else if (elixir != 0 && tipo.equals("Tropa de torre")){

                        System.out.println("\nLas Tropas de torre no pueden tener un costo de elixir distinto a 0.");

                    } else {

                        Carta carta = new Carta(nombre, elixir, rareza, tipo);

                        while(true){

                            System.out.println("\nCarta:");
                            System.out.println(carta.toString());
                            System.out.println("(La ID se asigna automáticamente)");
                            System.out.println("\n1. Adicionar");
                            System.out.println("2. Cancelar");

                            System.out.print("Opción: ");
                            int opInsertar = validarInt(leer);

                            switch (opInsertar) {

                                case 1:

                                    System.out.println("\nCargando...");
                                    dao.agregarCarta(carta);

                                break;

                                case 2:

                                    System.out.println("\nCancelado.");

                                break;

                                default:

                                    System.out.println("\nOpción inválida, intente de nuevo.");

                                break;

                            }

                            if(opInsertar == 1 || opInsertar == 2) {

                                break;

                            }
                        }
                    }

                break;

                case 2:

                    System.out.println("\nCargando...");

                    List<Carta> listaCartas = dao.listaCartas();

                    System.out.println("\n======== TABLA COMPLETA DE CARTAS ========");

                    for (Carta listCAux : listaCartas) {

                        System.out.println(listCAux);

                    }

                break;

                case 3:

                    while(true) {

                        System.out.println("\nBuscar carta por:");
                        System.out.println("1. ID");
                        System.out.println("2. Nombre");
                        System.out.println("3. Regresar");

                        System.out.print("Opción: ");
                        int opBuscar = validarInt(leer);

                        switch (opBuscar) {

                            case 1:

                                int idAEncontrar;

                                while(true) {

                                    System.out.print("\nIngrese ID: ");
                                    idAEncontrar = validarInt(leer);

                                    if (idAEncontrar <= 0){

                                        System.out.println("\nLa ID no puede ser negativa o 0, intente de nuevo:");

                                    } else {

                                        break;

                                    }
                                }

                                System.out.println("\nCargando...");

                                Carta cartaEncontrada = dao.buscarPorId(idAEncontrar);

                                System.out.print("\n");

                                if (cartaEncontrada != null) {

                                    System.out.println(cartaEncontrada);

                                } else {

                                    System.out.println("No existe carta con ese ID.");

                                }

                            break;

                            case 2:

                                System.out.print("\nIngrese el nombre a buscar: ");
                                String nombreABuscar = leer.nextLine();

                                System.out.println("\nCargando...");

                                List<Carta> nombresEncontrados = dao.buscarPorNombre(nombreABuscar);

                                if (nombresEncontrados.isEmpty()) {

                                    System.out.println("No se encontraron cartas.");

                                } else {

                                    System.out.print("\n");

                                    for (Carta nomEncAUX : nombresEncontrados) {

                                        System.out.println(nomEncAUX);

                                    }
                                }

                            break;

                            case 3:

                                System.out.println("\nRegresando...");

                            break;

                            default:

                                System.out.println("\nOpción inválida, intente de nuevo: ");

                        }

                        if(opBuscar == 3) {

                        break;

                        }
                    }

                break;

                case 4:

                    while(true) {

                        System.out.println("\nFiltrar por:");
                        System.out.println("1. Elixir");
                        System.out.println("2. Rareza");
                        System.out.println("3. Tipo");
                        System.out.println("4. Regresar");

                        System.out.print("Opción: ");
                        int opFiltrar = validarInt(leer);

                        switch (opFiltrar) {

                            case 1:

                                String operadorElx;

                                while(true) {

                                    System.out.print("\nOperador (=, <, >, <=, >=): ");
                                    operadorElx = leer.nextLine();

                                    if (operadorElx.equals("=") || operadorElx.equals("<") || operadorElx.equals(">") || operadorElx.equals("<=") || operadorElx.equals(">=")){

                                        break;

                                    } else {

                                        System.out.println("\nOperador inválido, intente de nuevo.");

                                    }
                                }

                                int elixirABuscar;

                                while(true) {

                                    System.out.print("\nElixir " + operadorElx + " ");
                                    elixirABuscar = validarInt(leer);

                                    if (elixirABuscar < 0){

                                        System.out.println("\nEl elixir no puede ser negativo, intente de nuevo.");

                                    } else if(elixirABuscar > 10) {

                                        System.out.println("\nEl elixir no puede ser mayor a 10, intente de nuevo.");

                                    } else {

                                        break;

                                    }
                                }

                                System.out.println("\nCargando...");

                                List<Carta> elxEncontrado = dao.encontrarElixir(operadorElx, elixirABuscar);

                                if (elxEncontrado.isEmpty()) {

                                    System.out.println("\nNo se encontraron cartas.");

                                } else {

                                    System.out.print("\n");

                                    for (Carta elxEncAUX : elxEncontrado) {

                                        System.out.println(elxEncAUX);

                                    }
                                }

                            break;

                            case 2:

                                while (true) {

                                    System.out.println("\n1. Común");
                                    System.out.println("2. Especial");
                                    System.out.println("3. Épica");
                                    System.out.println("4. Legendaria");
                                    System.out.println("5. Campeón");
                                    System.out.println("6. Regresar");

                                    System.out.print("Opción: ");
                                    int opRareza = validarInt(leer);

                                    switch (opRareza) {

                                        case 1:

                                            System.out.println("\nCargando...");

                                            List<Carta> rarezaComunEncontrada = dao.encontrarRareza("Común");

                                            System.out.print("\n");

                                            for (Carta raComEncAUX : rarezaComunEncontrada) {

                                                System.out.println(raComEncAUX);

                                            }

                                        break;

                                        case 2:

                                            System.out.println("\nCargando...");

                                            List<Carta> rarezaEspecialEncontrada = dao.encontrarRareza("Especial");

                                            System.out.print("\n");

                                            for (Carta raEspEncAUX : rarezaEspecialEncontrada) {

                                                System.out.println(raEspEncAUX);

                                            }
                                        break;

                                        case 3:

                                            System.out.println("\nCargando...");

                                            List<Carta> rarezaEpicaEncontrada = dao.encontrarRareza("Épica");

                                            System.out.print("\n");

                                            for (Carta raEpcEncAUX : rarezaEpicaEncontrada) {

                                                System.out.println(raEpcEncAUX);

                                            }

                                        break;

                                        case 4:

                                            System.out.println("\nCargando...");

                                            List<Carta> rarezaLegendariaEncontrada = dao.encontrarRareza("Legendaria");

                                            System.out.print("\n");

                                            for (Carta raLegEncAUX : rarezaLegendariaEncontrada) {

                                                System.out.println(raLegEncAUX);

                                            }

                                        break;

                                        case 5:

                                            System.out.println("\nCargando...");

                                            List<Carta> rarezaCampeonEncontrada = dao.encontrarRareza("Campeón");

                                            System.out.print("\n");

                                            for (Carta raCamEncAUX : rarezaCampeonEncontrada) {

                                                System.out.println(raCamEncAUX);

                                            }

                                        break;

                                        case 6:

                                            System.out.println("\nRegresando...");

                                        break;

                                        default:

                                            System.out.println("\nOpción inválida, intente de nuevo.");

                                        break;

                                    }

                                    if (opRareza == 6) {

                                        break;

                                    }
                                }

                            break;

                            case 3:

                                while (true) {

                                    System.out.println("\n1. Tropa");
                                    System.out.println("2. Estructura");
                                    System.out.println("3. Hechizo");
                                    System.out.println("4. Tropa de torre");
                                    System.out.println("5. Regresar");

                                    System.out.print("Opción: ");
                                    int opTipo = validarInt(leer);

                                    switch (opTipo) {

                                        case 1:

                                            System.out.println("\nCargando...");

                                            List<Carta> tipoTropaEncontrada = dao.encontrarTipo("Tropa");

                                            System.out.print("\n");

                                            for (Carta tiTroEncAUX : tipoTropaEncontrada) {

                                                System.out.println(tiTroEncAUX);

                                            }

                                        break;

                                        case 2:

                                            System.out.println("\nCargando...");

                                            List<Carta> tipoEstructuraEncontrada = dao.encontrarTipo("Estructura");

                                            System.out.print("\n");

                                            for (Carta tiEstEncAUX : tipoEstructuraEncontrada) {

                                                System.out.println(tiEstEncAUX);

                                            }

                                        break;

                                        case 3:

                                            System.out.println("\nCargando...");

                                            List<Carta> tipoHechizoEncontrada = dao.encontrarTipo("Hechizo");

                                            System.out.print("\n");

                                            for (Carta tiHecEncAUX : tipoHechizoEncontrada) {

                                                System.out.println(tiHecEncAUX);

                                            }

                                        break;

                                        case 4:

                                            System.out.println("\nCargando...");

                                            List<Carta> tipoTroTorreEncontrada = dao.encontrarTipo("Tropa de torre");

                                            System.out.print("\n");

                                            for (Carta tiTdTEncAUX : tipoTroTorreEncontrada) {

                                                System.out.println(tiTdTEncAUX);

                                            }

                                        break;

                                        case 5:

                                            System.out.println("\nRegresando...");

                                        break;

                                        default:

                                            System.out.println("\nOpción inválida, intente de nuevo.");

                                        break;

                                    }

                                    if (opTipo == 5) {

                                        break;

                                    }
                                }

                            break;

                            case 4:

                                System.out.println("\nRegresando...");

                            break;

                            default:

                                System.out.println("\nOpción inválida, intente de nuevo.");

                            break;

                        }

                        if (opFiltrar == 4) {

                            break;

                        }
                    }

                break;

                case 5:

                    System.out.println("\nBye bye");

                return;

                default:

                    System.out.println("\nOpción inválida, intente de nuevo.");

                break;

            }
        }
    }
}