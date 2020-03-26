package ochoreinas;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        int dimension = 8;
        if (args.length == 1) {
            dimension = Integer.parseInt(args[0]);
        }

        Buscador buscador = new Buscador(dimension);


        ArrayList<Tablero> solucionesNoFuncional = buscador.resolverNoFuncional();

        System.out.println("NO FUNCIONAL------------");
        for (Tablero solucion : solucionesNoFuncional) {
            System.out.println();
            System.out.println(solucion.toString());
        }

        ArrayList<Tablero> soluciones = buscador.resolver();

        System.out.println("FUNCIONAL------------");
        for (Tablero solucion : soluciones) {
            System.out.println();
            System.out.println(solucion.toString());
        }

    }
}
