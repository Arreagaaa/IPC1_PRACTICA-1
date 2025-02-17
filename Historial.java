import java.util.Arrays;

public class Historial {
    private static Jugador[] jugadores = new Jugador[100]; // Tamaño máximo del arreglo
    private static int count = 0;

    public static void agregarJugador(Jugador jugador, int fallos, int palabrasEncontradas) {
        jugador.setFallos(fallos);
        jugador.setPalabrasEncontradas(palabrasEncontradas);
        if (count < jugadores.length) {
            jugadores[count++] = jugador;
        } else {
            System.out.println("No se pueden agregar más jugadores.");
        }
    }

    // ARMANDO LA TABLA DE RESULTADOS REUTILIZABLE
    private static void mostrarTabla(Jugador[] lista, int size, String titulo) {
        if (size == 0) {
            System.out.println("No hay datos para mostrar.");
            return;
        }
        System.out.println("\n--- " + titulo + " ---");
        System.out.printf("%-15s %-10s %-10s %-10s%n", "Nombre", "Puntos", "Fallos", "Encontradas");
        System.out.println("--------------------------------------------------");

        for (int i = 0; i < size; i++) {
            Jugador j = lista[i];
            System.out.printf("%-15s %-10d %-10d %-10d%n",
                    j.getNombre(), j.getPuntuacion(), j.getFallos(), j.getPalabrasEncontradas());
        }
    }

    public static void mostrarHistorial() {
        mostrarTabla(jugadores, count, "HISTORIAL DE PARTIDAS");
    }

    public static void mostrarPuntuacionesAltas() {
        Jugador[] topJugadores = Arrays.copyOf(jugadores, count);
        Arrays.sort(topJugadores, 0, count, (a, b) -> Integer.compare(b.getPuntuacion(), a.getPuntuacion()));
        mostrarTabla(topJugadores, Math.min(3, count), "TOP 3 JUGADORES");
    }
}
