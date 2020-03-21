package ViajanteComercio.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Montecarlo implements HeuristicaTSP {

    private int veces = 10000;

    public Montecarlo() {
    }

    public Montecarlo(int veces) {
        this.veces = veces;
    }

    public Ruta calcularRutaOptimaNoFuncional(Problema problema) {

        ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>(problema.getCiudades());
        double mejorCoste = Double.POSITIVE_INFINITY;
        Ruta rutaOptima = new Ruta();
        Ruta ruta;

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

    public Ruta calcularRutaOptima(Problema problema) {
        ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>(problema.getCiudades());

        Stream<Ruta> flujoRutas = IntStream.range(0, veces).mapToObj(i ->
        {
            Ruta ruta = new Ruta();
            Collections.shuffle(ciudades);
            ciudades.stream().forEach(c -> ruta.addCiudad(c, problema));
            ruta.terminarRuta(problema);
            return ruta;
        });

        Ruta ruta = flujoRutas.min(Comparator.comparing(Ruta::getCoste)).
                orElseThrow(NoSuchElementException::new);

        return ruta;
    }
}
