package viajantecomercio;

public class Principal {
    public static void main(String[] args) {
        HeuristicaTSP heuristica;
        Problema problema = new Problema(args[0]);
        String cadena = args[1];

        if (cadena.equals("MC")) {
            heuristica = new Montecarlo();
        }
        else {
            heuristica = new VecinoMasCercano();
        }

        Ruta solucion = heuristica.calcularRutaOptima(problema);
        System.out.println("Solución Funcional: " +
                 solucion.toString());

        Ruta solucionNoFuncional = heuristica.calcularRutaOptimaNoFuncional(problema);
        System.out.println("Solución No Funcional: " + solucionNoFuncional.toString());
    }
}
