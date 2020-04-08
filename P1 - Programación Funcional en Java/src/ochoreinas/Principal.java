package ochoreinas;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {

        // Lectura de parámetros: por defecto, el tablero es de dimensión 8,
        // pero puede indicarse un valor distinto
        int dimension = 8;
        if (args.length == 1) {
            dimension = Integer.parseInt(args[0]);
        }

        Buscador buscador = new Buscador(dimension);

        // Obtener e imprimir solución funcional
        ArrayList<Tablero> soluciones = buscador.resolver();

        System.out.println("FUNCIONAL------------");
        soluciones.forEach(sol -> System.out.println("\n" + sol.toString()));

        // Obtener e imprimir solución imperativa
        ArrayList<Tablero> solucionesNoFuncional = buscador.resolverNoFuncional();

        System.out.println("NO FUNCIONAL------------");
        solucionesNoFuncional.forEach(sol -> System.out.println("\n" + sol.toString()));

    }
}
