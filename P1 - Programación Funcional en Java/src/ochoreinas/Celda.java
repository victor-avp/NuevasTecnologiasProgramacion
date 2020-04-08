package ochoreinas;

/**
 * Clase basica para representar una celda
 */
public class Celda {
    /**
     * Posición de la celda
     */
    private int fila;
    private int columna;

    /**
     * Duelve un boolean que indica si dos celdas dadas están en conflicto
     *
     * @param c1 Una celda
     * @param c2 Otra celda
     * @return Boolean que indica si c1 y c2 están en conflicto
     */
    public static boolean celdasEnConflicto(Celda c1, Celda c2) {
        return c1.getFila() == c2.getFila() || c1.getColumna() == c2.getColumna()
                || Math.abs(c1.getFila() - c2.getFila())
                == Math.abs(c1.getColumna() - c2.getColumna());
    }

    /**
     * Constructor
     *
     * @param fila
     * @param columna
     */
    public Celda(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Obtiene la fila de la celda
     *
     * @return
     */
    public int getFila() {
        return fila;
    }

    /**
     * Obtiene la columna de la celda
     *
     * @return
     */
    public int getColumna() {
        return columna;
    }

}
