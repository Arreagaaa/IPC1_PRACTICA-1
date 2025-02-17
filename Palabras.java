import java.util.Scanner;
import java.util.Arrays;

public class Palabras {
    private String[] palabras;
    private int count;

    public Palabras() {
        this.palabras = new String[100]; // Tamaño máximo del arreglo
        this.count = 0;
    }

    public String[] getPalabras() {
        return Arrays.copyOf(palabras, count);
    }

    public int getCount() {
        return count;
    }

    public void gestionarPalabras(Scanner scanner) {
        System.out.print("Ingrese la cantidad de palabras: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        for (int i = 0; i < cantidad; i++) {
            System.out.print("Ingrese una palabra: ");
            String palabra = scanner.nextLine().toUpperCase();
            if (count < palabras.length) {
                palabras[count++] = palabra;
            } else {
                System.out.println("No se pueden agregar más palabras.");
                break;
            }
        }
    }

    public void modificarPalabra(Scanner scanner) {
        System.out.print("Ingrese la palabra a modificar: ");
        String palabra = scanner.nextLine().toUpperCase();
        for (int i = 0; i < count; i++) {
            if (palabras[i].equals(palabra)) {
                System.out.print("Ingrese la nueva palabra: ");
                palabras[i] = scanner.nextLine().toUpperCase();
                return;
            }
        }
        System.out.println("Palabra no encontrada.");
    }

    public void eliminarPalabra(String palabra) {
        for (int i = 0; i < count; i++) {
            if (palabras[i].equals(palabra)) {
                for (int j = i; j < count - 1; j++) {
                    palabras[j] = palabras[j + 1];
                }
                palabras[--count] = null;
                return;
            }
        }
        System.out.println("Palabra no encontrada.");
    }

    public void mostrarPalabras() {
        for (int i = 0; i < count; i++) {
            System.out.println(palabras[i]);
        }
    }
}
