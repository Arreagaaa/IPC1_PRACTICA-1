class Jugador {
    private String nombre;
    private int puntuacion;
    private int fallos;
    private int palabrasEncontradas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntuacion = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void aumentarPuntuacion(int puntos) {
        this.puntuacion += puntos;
    }

    public int getFallos() {
        return fallos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }

    public int getPalabrasEncontradas() {
        return palabrasEncontradas;
    }

    public void setPalabrasEncontradas(int palabrasEncontradas) {
        this.palabrasEncontradas = palabrasEncontradas;
    }
}
