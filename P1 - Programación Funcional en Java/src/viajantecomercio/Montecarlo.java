package viajantecomercio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Implementación de HeuristicaTSP con Simulación Monte Carlo
 */
public class Montecarlo implements HeuristicaTSP {

    /**
     * Número de simulaciones Monte Carlo asignadas, con valor por defecto
     */
    private int veces = 10000;

    /**
     * Constructor por defecto
     */
    public Montecarlo() {
    }

    /**
     * Constructor
     *
     * @param veces Número de simulaciones Monte Carlo asignadas
     */
    public Montecarlo(int veces) {
        this.veces = veces;
    }

    /**
     * Devuelve una ruta óptima para un problema dado, utilizando Simulación Monte Carlo.
     * Se trata de la implementación imperativa.
     *
     * @param problema Problema para el que calcular la ruta óptima
     * @return Ruta óptima calculada
     */
    public Ruta calcularRutaOptimaNoFuncional(Problema problema) {

        ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>(problema.getCiudades());
        double mejorCoste = Double.POSITIVE_INFINITY;
        Ruta rutaOptima = new Ruta();
        Ruta ruta;

        // Se genera una ruta aleatoria un número concreto de veces,
        // conservando la ruta de menor coste
        for (int i = 0; i < veces; i++) {
            ruta = new Ruta();
            Collections.shuffle(ciudades);
            for (Ciudad ciudad : ciudades) {
                ruta.addCiudad(ciudad, problema);
            }
            ruta.terminarRuta(problema);

            if (ruta.getCoste() < mejorCoste) {
                rutaOptima = ruta;
                mejorCoste = ruta.getCoste();
            }
        }

        return rutaOptima;
    }

    /**
     * Devuelve una ruta óptima para un problema dado, utilizando Simulación Monte Carlo.
     * Se trata de la implementación funcional.
     *
     * @param problema Problema para el que calcular la ruta óptima
     * @return Ruta óptima calculada
     */
    public Ruta calcularRutaOptima(Problema problema) {
        ArrayList<Ciudad> ciudades = new ArrayList<>(problema.getCiudades());

        // Se genera un flujo de rutas aleatorias
        Stream<Ruta> flujoRutas = IntStream.range(0, veces).mapToObj(i ->
        {
            Ruta ruta = new Ruta();
            Collections.shuffle(ciudades);
            ciudades.stream().forEach(c -> ruta.addCiudad(c, problema));
            ruta.terminarRuta(problema);
            return ruta;
        });

        // Al final, se obtiene del flujo la ruta con menor coste
        return flujoRutas.min(Comparator.comparing(Ruta::getCoste)).
                orElseThrow(NoSuchElementException::new);
    }
}
