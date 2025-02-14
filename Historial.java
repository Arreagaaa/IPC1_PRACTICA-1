import java.util.ArrayList;
import java.util.List;

public class Historial {
    private static List<Jugador> jugadores = new ArrayList<>();

    public static void agregarJugador(Jugador jugador, int fallos, int palabrasEncontradas) {
        jugador.setFallos(fallos);
        jugador.setPalabrasEncontradas(palabrasEncontradas);
        jugadores.add(jugador);
    }

    // ARMANDO LA TABLA DE RESULTADOS REUTILIZABLE
    private static void mostrarTabla(List<Jugador> lista, String titulo) {
        if (lista.isEmpty()) {
            System.out.println("No hay datos para mostrar.");
            return;
        }
        System.out.println("\n--- " + titulo + " ---");
        System.out.printf("%-15s %-10s %-10s %-10s%n", "Nombre", "Puntos", "Fallos", "Encontradas");
        System.out.println("--------------------------------------------------");

        for (Jugador j : lista) {
            System.out.printf("%-15s %-10d %-10d %-10d%n",
                    j.getNombre(), j.getPuntuacion(), j.getFallos(), j.getPalabrasEncontradas());
        }
    }

    public static void mostrarHistorial() {
        mostrarTabla(jugadores, "HISTORIAL DE PARTIDAS");
    }

    public static void mostrarPuntuacionesAltas() {
        List<Jugador> topJugadores = new ArrayList<>(jugadores);
        topJugadores.sort((a, b) -> Integer.compare(b.getPuntuacion(), a.getPuntuacion()));
        mostrarTabla(topJugadores.subList(0, Math.min(3, topJugadores.size())), "TOP 3 JUGADORES");
    }
}
