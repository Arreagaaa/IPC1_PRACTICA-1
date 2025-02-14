public class CargasAnimaciones {
    // EFECTO DE CARGA
    public static void mostrarCarga(String mensaje) {
        System.out.print(mensaje);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
                System.out.print(".");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Error en la animación de carga.");
            }
        }
        System.out.println();
    }

    // MENSAJE DE BIENVENIDA
    public static void mostrarBienvenida() {
        String mensaje = "BIENVENIDO AL JUEGO";
        String[] efectos = { "|", "/", "-", "\\" }; // Animación giratoria
        int duracion = 150; // Tiempo entre frames en milisegundos

        System.out.print("\n");
        for (int i = 0; i < mensaje.length(); i++) {
            try {
                Thread.sleep(duracion);
                System.out.print(mensaje.charAt(i)); // Muestra letra por letra
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("\nError en la animación.");
                return;
            }
        }

        System.out.println("\nCargando...");

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(duracion * 2);
                System.out.print("\r[" + efectos[i % 4] + "] "); // Animación giratoria
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("\nError en la animación.");
                return;
            }
        }

        System.out.println("\r¡Listo para jugar!");
    }
}
