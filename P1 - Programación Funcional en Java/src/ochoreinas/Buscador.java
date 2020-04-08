package ochoreinas;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase que busca soluciones para el problema n-reinas
 */
public class Buscador {
    /**
     * Dimensión del tablero (cuadrado), y por tanto número de reinas a colocar
     */
    private int dimension;

    /**
     * Constructor
     *
     * @param dimension
     */
    public Buscador(int dimension) {
        this.dimension = dimension;
    }

    /**
     * Devuelve la lista de tableros (soluciones) posibles rellenados hasta una fila dada
     * Es una función recursiva
     * Implementación imperativa
     *
     * @param fila Fila hasta la que rellenar los tableros
     * @return Array de Tableros
     */
    public ArrayList<Tablero> ubicarReinaNoFuncional(int fila) {
        ArrayList<Tablero> soluciones = new ArrayList<>();

        // Caso base con fila -1: se crea un tablero vacío como base para el resto
        // de soluciones
        if (fila == -1) {
            soluciones.add(new Tablero(dimension));
        } else { // Si es un nº de filas válido, crear soluciones con nº filas inmediatamente inferior
            ArrayList<Tablero> soluciones_anteriores = ubicarReinaNoFuncional(fila - 1);

            // Para cada solución de las anteriores
            for (Tablero sol_ant : soluciones_anteriores) {
                // Para cada columna
                for (int columna = 0; columna < dimension; columna++) {
                    // Comprobar si la posición es segura en la solución, creando
                    // una nueva solución en su caso
                    Celda celda = new Celda(fila, columna);
                    if (sol_ant.posicionSegura(celda)) {
                        Tablero solucion = new Tablero(sol_ant);
                        solucion.ponerReina(celda.getFila(), celda.getColumna());
                        soluciones.add(solucion);
                    }
                }
            }
        }
        return soluciones;
    }

    /**
     * Devuelve la lista de tableros (soluciones) posibles rellenados hasta una fila dada
     * Es una función recursiva
     * Implementación funcional
     *
     * @param fila Fila hasta la que rellenar los tableros
     * @return Array de Tableros
     */
    public ArrayList<Tablero> ubicarReina(int fila) {
        ArrayList<Tablero> soluciones = new ArrayList<>();

        // Caso base con fila -1: se crea un tablero vacío como base para el resto
        // de soluciones
        if (fila == -1) {
            soluciones.add(new Tablero(dimension));
        } else { // Si es un nº de filas válido, crear soluciones con nº filas inmediatamente inferior

            // A partir de las soluciones con fila-1, se crean celdas para la fila actual,
            // se filtran para obtener las seguras y se crean nuevas soluciones con estas
            soluciones = (ArrayList<Tablero>) ubicarReina(fila - 1).stream().
                    flatMap(sol_ant -> IntStream.range(0, dimension).
                            mapToObj(col -> new Celda(fila, col)).
                            filter(sol_ant::posicionSegura).map(celda -> {
                        Tablero tablero = new Tablero(sol_ant);
                        tablero.ponerReina(celda.getFila(), celda.getColumna());
                        return tablero;
                    })).collect(Collectors.toList());

        }

        return soluciones;
    }

    /**
     * Devuelve la lista de soluciones para el tablero
     * Implementación imperativa
     *
     * @return Array de soluciones (tableros)
     */
    public ArrayList<Tablero> resolverNoFuncional() {
        return ubicarReinaNoFuncional(dimension - 1);
    }

    /**
     * Devuelve la lista de soluciones para el tablero
     * Implementación funcional
     *
     * @return Array de soluciones (tableros)
     */
    public ArrayList<Tablero> resolver() {
        return ubicarReina(dimension - 1);
    }
}
