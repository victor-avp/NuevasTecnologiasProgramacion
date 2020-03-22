package viajantecomercio;

/**
 * Interfaz definiendo el metodo que implementaran todas las
 * heuristicas de solucion de este problema
 */
public interface HeuristicaTSP {
   // metodo basico de la interfaz
   public Ruta calcularRutaOptimaNoFuncional(Problema problema);
   public Ruta calcularRutaOptima(Problema problema);
}
