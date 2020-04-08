package ochoreinas;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase basica para representar un tablero cuadrado
 */
public class Tablero {
    /**
     * Dimensión del tablero: longitud de un lado
     */
    private int dimension;

    /**
     * Array con las celdas ocupadas del tablero
     */
    private ArrayList<Celda> contenido;

    /**
     * Constructor
     *
     * @param dimension Dimensión del tablero
     */
    public Tablero(int dimension) {
        this.dimension = dimension;
        contenido = new ArrayList<>();
    }

    /**
     * Constructor de copia
     *
     * @param tablero Tablero a copiar
     */
    public Tablero(Tablero tablero) {
        dimension = tablero.dimension;
        contenido = new ArrayList<>(tablero.contenido);
    }

    /**
     * Coloca una reina en una posición del tablero. No se comprueba si está ocupada.
     *
     * @param fila    Fila de la posición
     * @param columna Columna de la posición
     */
    public void ponerReina(int fila, int columna) {
        contenido.add(new Celda(fila, columna));
    }

    /**
     * Duelve un boolean que indica si la posición de una celda es segura,
     * es decir, no está en conflicto con ninguna otra
     * Se usa enfoque funcional
     *
     * @param celda Celda a analizar
     * @return boolean que indica si la posición es segura
     */
    public boolean posicionSegura(Celda celda) {
        return contenido.stream().
                noneMatch(celda_ocupada -> Celda.celdasEnConflicto(celda, celda_ocupada));
    }

    /**
     * Duelve un boolean que indica si la posición dada está ocupada
     *
     * @param fila    Fila de la posición
     * @param columna Columna de la posición
     * @return boolean que indica si la posición está ocupada
     */
    private boolean posicionOcupada(int fila, int columna) {
        return contenido.stream().
                noneMatch(c -> c.getFila() == fila && c.getColumna() == columna);
    }

    /**
     * Duelve un String con tantos "-" como se indique
     *
     * @param longitud Número de "-"
     * @return String con sucesión de "-"
     */
    private String lineaHorizontal(int longitud) {
        return IntStream.range(0, longitud).mapToObj(i -> "-").
                collect(Collectors.joining()) + "\n";
    }

    @Override
    public String toString() {

        String cadena = "Celdas ocupadas: " + contenido.size() + "\n";
        String borde = lineaHorizontal(2 + dimension + (dimension + 1) * 2);

        cadena += borde;

        // toString de las celdas con enfoque funcional: para cada fila, se crea
        // un string. Dicho string, se crea generando otro string por cada columna,
        // aplicando una función map y concatenando con los separadores correspondientes
        cadena += IntStream.range(0, dimension).mapToObj(fil ->
                "|  " + IntStream.range(0, dimension).mapToObj(col ->
                        {
                            if (posicionOcupada(fil, col))
                                return "R";
                            else
                                return "X";
                        }
                ).collect(Collectors.joining("  "))
        ).collect(Collectors.joining("  |\n"));

        cadena += "  |\n" + borde;

        return cadena;

    }
}
