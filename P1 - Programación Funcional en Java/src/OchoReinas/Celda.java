package OchoReinas;

public class Celda {
    private int fila;
    private int columna;

    public static boolean celdasEnConflicto(Celda c1, Celda c2) {
        return c1.getFila() != c2.getFila() && c1.getColumna() != c2.getColumna()
                && Math.abs(c1.getFila() - c2.getFila())
                != Math.abs(c1.getColumna() - c2.getColumna());
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }


}
