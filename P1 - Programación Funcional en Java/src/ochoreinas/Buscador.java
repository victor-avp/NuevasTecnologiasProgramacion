package ochoreinas;
import java.util.ArrayList;

public class Buscador {
    private int dimension;

    public Buscador(int dimension) {
        this.dimension = dimension;
    }

    public ArrayList<Tablero> ubicarReina(int fila) {
        ArrayList<Tablero> soluciones = new ArrayList<>();;
        if (fila == -1) {
            soluciones.add(new Tablero(dimension));
        }
        else {
            ArrayList<Tablero> soluciones_anteriores = ubicarReina(fila - 1);

            for (Tablero solucion_anterior : soluciones_anteriores) {
                for (int columna = 0; columna < dimension; columna++) {
                    Celda celda = new Celda(fila, columna);
                    if (solucion_anterior.posicionSegura(celda)) {
                        Tablero solucion = new Tablero(solucion_anterior);
                        solucion.ponerReina(celda.getFila(), celda.getColumna());
                        soluciones.add(solucion);
                    }
                }
            }
        }
        return soluciones;
    }

    public ArrayList<Tablero> resolver() {
        return ubicarReina(dimension - 1);
    }
}
