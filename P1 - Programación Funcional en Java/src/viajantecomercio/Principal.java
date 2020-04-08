package viajantecomercio;

public class Principal {
    public static void main(String[] args) {
        HeuristicaTSP heuristica;

        // Lectura de parámetros: el primero es el fichero del problema, y el
        // segundo es la heurística (MC o VMC)
        Problema problema = new Problema(args[0]);
        String cadena = args[1];

        if (cadena.equals("MC")) {
            heuristica = new Montecarlo();
        }
        else {
            heuristica = new VecinoMasCercano();
        }

        // Obtener e imprimir solución funcional
        Ruta solucion = heuristica.calcularRutaOptima(problema);
        System.out.println("Solución Funcional: " +
                 solucion.toString());

        // Obtener e imprimir solución imperativa
        Ruta solucionNoFuncional = heuristica.calcularRutaOptimaNoFuncional(problema);
        System.out.println("Solución No Funcional: " + solucionNoFuncional.toString());
    }
}
