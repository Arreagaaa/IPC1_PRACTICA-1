import java.util.*;

class Tablero {
    private char[][] tablero;
    private int tamaño;
    private Random random = new Random();

    public void generarTablero(List<String> palabras) {

        CargasAnimaciones.mostrarCarga("Creando el tablero...");

        // SIZE SEGUN TABLA 1
        this.tamaño = 15;
        this.tablero = new char[tamaño][tamaño];

        for (int i = 0; i < tamaño; i++) {
            Arrays.fill(tablero[i], ' ');
        }

        for (String palabra : palabras) {
            colocarPalabra(palabra);
        }

        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (tablero[i][j] == ' ') {
                    tablero[i][j] = (char) ('A' + random.nextInt(26));
                }
            }
        }
    }

    private void colocarPalabra(String palabra) {
        int longitud = palabra.length();
        boolean colocada = false;

        while (!colocada) {
            int fila = random.nextInt(tamaño);
            int columna = random.nextInt(tamaño);
            int direccion = random.nextInt(2);

            if (direccion == 0 && columna + longitud <= tamaño) {
                if (espacioDisponible(fila, columna, longitud, direccion)) {
                    for (int i = 0; i < longitud; i++) {
                        tablero[fila][columna + i] = palabra.charAt(i);
                    }
                    colocada = true;
                }
            } else if (direccion == 1 && fila + longitud <= tamaño) {
                if (espacioDisponible(fila, columna, longitud, direccion)) {
                    for (int i = 0; i < longitud; i++) {
                        tablero[fila + i][columna] = palabra.charAt(i);
                    }
                    colocada = true;
                }
            }
        }
    }

    private boolean espacioDisponible(int fila, int columna, int longitud, int direccion) {
        if (direccion == 0) {
            for (int i = 0; i < longitud; i++) {
                if (tablero[fila][columna + i] != ' ')
                    return false;
            }
        } else {
            for (int i = 0; i < longitud; i++) {
                if (tablero[fila + i][columna] != ' ')
                    return false;
            }
        }
        return true;
    }

    public void imprimirTablero() {
        System.out.println("\n--- TABLERO ---");
        for (char[] fila : tablero) {
            for (char c : fila) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public void reemplazarPalabra(String palabra) {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (buscarYReemplazar(i, j, palabra)) {
                    return;
                }
            }
        }
    }

    private boolean buscarYReemplazar(int fila, int columna, String palabra) {
        int longitud = palabra.length();

        // BUSCAR PALABRA HORIZONTALMENTE
        if (columna + longitud <= tamaño) {
            boolean encontrada = true;
            for (int i = 0; i < longitud; i++) {
                if (tablero[fila][columna + i] != palabra.charAt(i)) {
                    encontrada = false;
                    break;
                }
            }
            if (encontrada) {
                for (int i = 0; i < longitud; i++) {
                    tablero[fila][columna + i] = '#';
                }
                return true;
            }
        }

        // BUSCAR PALABRA VERTICALMENTE
        if (fila + longitud <= tamaño) {
            boolean encontrada = true;
            for (int i = 0; i < longitud; i++) {
                if (tablero[fila + i][columna] != palabra.charAt(i)) {
                    encontrada = false;
                    break;
                }
            }
            if (encontrada) {
                for (int i = 0; i < longitud; i++) {
                    tablero[fila + i][columna] = '#';
                }
                return true;
            }
        }

        return false;
    }

}
