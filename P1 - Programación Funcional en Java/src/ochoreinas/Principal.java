package ochoreinas;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        int dimension = 2;
        if (args.length == 1) {
            dimension = Integer.parseInt(args[0]);
        }

        Buscador buscador = new Buscador(dimension);
        ArrayList<Tablero> soluciones = buscador.resolver();

        for (Tablero solucion : soluciones) {
            System.out.println();
            System.out.println(solucion.toString());
        }
    }
}
