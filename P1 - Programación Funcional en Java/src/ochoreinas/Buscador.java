package ochoreinas;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Buscador {
    private int dimension;

    public Buscador(int dimension) {
        this.dimension = dimension;
    }

    public ArrayList<Tablero> ubicarReinaNoFuncional(int fila) {
        ArrayList<Tablero> soluciones = new ArrayList<>();
        if (fila == -1) {
            soluciones.add(new Tablero(dimension));
        } else {
            ArrayList<Tablero> soluciones_anteriores = ubicarReina(fila - 1);

            for (Tablero sol_ant : soluciones_anteriores) {
                for (int columna = 0; columna < dimension; columna++) {
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

    public ArrayList<Tablero> ubicarReina(int fila) {
        ArrayList<Tablero> soluciones = new ArrayList<>();
        if (fila == -1) {
            soluciones.add(new Tablero(dimension));
        } else {
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

    public ArrayList<Tablero> resolverNoFuncional() {
        return ubicarReinaNoFuncional(dimension - 1);
    }

    public ArrayList<Tablero> resolver() {
        return ubicarReina(dimension - 1);
    }
}
