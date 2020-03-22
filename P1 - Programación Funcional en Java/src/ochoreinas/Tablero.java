package ochoreinas;

import java.util.ArrayList;

public class Tablero {
    private int dimension;
    private ArrayList<Celda> contenido;

    public Tablero(int dimension) {
        this.dimension = dimension;
        contenido = new ArrayList<>();
    }

    public Tablero(Tablero tablero) {
        dimension = tablero.dimension;
        contenido = new ArrayList<>(tablero.contenido);
    }

    public void ponerReina(int fila, int columna) {
        contenido.add(new Celda(fila, columna));
    }

    public boolean posicionSegura(Celda celda) {
        for (Celda celda_ocupada : contenido) {
            if (Celda.celdasEnConflicto(celda, celda_ocupada)) {
                return false;
            }
        }

        return true;
    }

    private boolean posicionOcupada(int fila, int columna) {
        for (Celda celda : contenido) {
            if (celda.getFila() == fila && celda.getColumna() == columna) {
                return true;
            }
        }

        return false;
    }

    private String lineaHorizontal(int longitud) {
        String cadena = "";
        for (int i = 0; i < longitud; i++) {
            cadena += "-";
        }

        cadena += "\n";

        return cadena;
    }

    @Override
    public String toString() {

        String cadena = "Celdas ocupadas: " + contenido.size() + "\n";
        String borde = lineaHorizontal(2 + dimension + (dimension+1) * 2);

        cadena += borde;

        for (int fil = 0; fil < dimension; fil++) {
            cadena += "|  ";
            for (int col = 0; col < dimension; col++) {
                if (posicionOcupada(fil, col)) {
                    cadena += "R  ";
                } else {
                    cadena += "X  ";
                }
            }
            cadena += "|\n";
        }

        cadena += borde;

        return cadena;

    }
}
