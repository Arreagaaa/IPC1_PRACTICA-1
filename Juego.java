import java.util.*;

public class Juego {
    private Scanner scanner = new Scanner(System.in);
    private Tablero tablero;
    private Palabras palabras;
    private Jugador jugador;

    public Juego() {
        this.palabras = new Palabras();
        this.tablero = new Tablero();

        CargasAnimaciones.mostrarBienvenida();
    }

    public void mostrarMenu() {
        while (true) {
            try {
                System.out.println("\n--- MENU PRINCIPAL ---");
                System.out.println("1. Nueva Partida");
                System.out.println("2. Historial de Partidas");
                System.out.println("3. Mostrar Puntuaciones Más Altas");
                System.out.println("4. Mostrar Información del Estudiante");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Por favor, ingrese un número válido.");
                    // LIMPIAR INPUT INVALIDO
                    scanner.next();
                    continue;
                }

                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1 -> nuevaPartida();
                    case 2 -> Historial.mostrarHistorial();
                    case 3 -> Historial.mostrarPuntuacionesAltas();
                    case 4 -> mostrarInformacionEstudiante();
                    case 5 -> {
                        CargasAnimaciones.mostrarCarga("Saliendo...");
                        return;
                    }
                    default -> System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error: Entrada inválida. Intente de nuevo.");
                // LIMPIAR INPUT INVALIDO
                scanner.nextLine();
            }
        }
    }

    private void nuevaPartida() {
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        this.jugador = new Jugador(nombre);

        // LLAMAR METODO PARA GESTIONAR PALABRAS (CRUD)
        gestionarPalabras();

        if (palabras.getCount() == 0) {
            System.out.println("No hay palabras ingresadas. Regresando al menú...");
            return;
        }

        tablero.generarTablero(palabras.getPalabras());
        tablero.imprimirTablero();
        jugar();
    }

    // CRUD DE PALABRAS EN EL JUEGO (PRIVATE)
    private void gestionarPalabras() {
        while (true) {
            System.out.println("\n--- MENU DE PALABRAS ---");
            System.out.println("1. Insertar palabras");
            System.out.println("2. Modificar palabra");
            System.out.println("3. Eliminar palabra");
            System.out.println("4. Mostrar palabras");
            System.out.println("5. Volver o empezar a jugar");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> palabras.gestionarPalabras(scanner); // CASO PARA INSERTAR PALABRAS (PALABRAS.JAVA - public)
                case 2 -> palabras.modificarPalabra(scanner);
                case 3 -> {
                    System.out.print("Ingrese la palabra a eliminar: ");
                    String palabra = scanner.nextLine().toUpperCase();
                    palabras.eliminarPalabra(palabra);
                }
                case 4 -> palabras.mostrarPalabras();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }

        }
    }

    private void jugar() {
        if (palabras.getCount() == 0) {
            System.out.println("No hay palabras para jugar.");
            return;
        }
        int errores = 0;
        jugador.aumentarPuntuacion(25);
        // NUMERO DE PALABRAS INICIALES
        int totalPalabras = palabras.getCount();

        while (errores < 4) {
            System.out.print("Ingrese una palabra: ");
            String palabra = scanner.nextLine().toUpperCase();

            if (Arrays.asList(palabras.getPalabras()).contains(palabra)) {
                System.out.println("¡Palabra encontrada!");
                jugador.aumentarPuntuacion(palabra.length());
                palabras.eliminarPalabra(palabra);
                tablero.reemplazarPalabra(palabra);
                tablero.imprimirTablero();
            } else {
                errores++;
                System.out.println("Palabra incorrecta. Llevas " + errores + "/4 errores.");
                jugador.aumentarPuntuacion(-5);
            }

            // PROGRESO DEL JUEGO
            System.out.println("Palabras encontradas: " + (totalPalabras - palabras.getCount()));
            System.out.println("Palabras pendientes: " + palabras.getCount());

            if (palabras.getCount() == 0) {
                System.out.println("¡Ganaste! Puntuación final: " + jugador.getPuntuacion());
                Historial.agregarJugador(jugador, errores, totalPalabras);
                return;
            }
        }

        System.out.println("¡Perdiste! Puntuación final: " + jugador.getPuntuacion());
        Historial.agregarJugador(jugador, errores, totalPalabras - palabras.getCount());
    }

    private void mostrarInformacionEstudiante() {
        System.out.println("Nombre: Christian Javier Rivas Arreaga");
        System.out.println("Carnet: 202303204");
        System.out.println("Sección: B");
    }
}
