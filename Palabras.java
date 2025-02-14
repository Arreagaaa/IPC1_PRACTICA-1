import java.util.*;

//CRUD DE PALABRAS
class Palabras {
    private List<String> palabras = new ArrayList<>();

    // CANTIDAD INICIAL Y CREAR PALABRAS
    public void gestionarPalabras(Scanner scanner) {
        System.out.print("Ingrese la cantidad de palabras: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidad; i++) {
            while (true) {
                System.out.print("Ingrese la palabra " + (i + 1) + ": ");
                String palabra = scanner.nextLine().toUpperCase();

                if (!validarLongitud(palabra)) {
                    System.out.println("Error: La palabra debe tener entre 3 y 8 caracteres.");
                } else {
                    palabras.add(palabra);
                    break;
                }
            }
        }
    }

    // MODIFICAR PALABRA
    public void modificarPalabra(Scanner scanner) {
        if (palabras.isEmpty()) {
            System.out.println("No hay palabras ingresadas.");
            return;
        }

        mostrarPalabras();

        System.out.print("Ingrese la palabra a modificar: ");
        String palabraAntigua = scanner.nextLine().toUpperCase();

        if (!palabras.contains(palabraAntigua)) {
            System.out.println("Error: La palabra no se encuentra en la lista.");
            return;
        }

        while (true) {
            System.out.print("Ingrese la nueva palabra: ");
            String palabraNueva = scanner.nextLine().toUpperCase();

            if (!validarLongitud(palabraNueva)) {
                System.out.println("Error: La palabra debe tener entre 3 y 8 caracteres.");
            } else {
                palabras.set(palabras.indexOf(palabraAntigua), palabraNueva);
                System.out.println("Palabra modificada correctamente.");
                break;
            }
        }
    }

    // ELIMINAR PALABRA
    public void eliminarPalabra(String palabra) {
        boolean eliminada = palabras.removeIf(p -> p.equalsIgnoreCase(palabra));

        if (eliminada) {
            System.out.println("Palabra eliminada correctamente.");
        } else {
            System.out.println("Error: La palabra no se encuentra en la lista.");
        }
    }

    private boolean validarLongitud(String palabra) {
        return palabra.length() >= 3 && palabra.length() <= 8;
    }

    public void mostrarPalabras() {
        if (palabras.isEmpty()) {
            System.out.println("No hay palabras ingresadas.");
        } else {
            System.out.println("Palabras actuales: " + palabras);
        }
    }

    public List<String> getPalabras() {
        return palabras;
    }
}
