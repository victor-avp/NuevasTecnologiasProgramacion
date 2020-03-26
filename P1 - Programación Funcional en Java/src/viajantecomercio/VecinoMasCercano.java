package viajantecomercio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class VecinoMasCercano implements HeuristicaTSP {

    public Ruta calcularRutaOptimaNoFuncional(Problema problema) {
        ArrayList<Ciudad> ciudades = problema.getCiudades();
        double mejorCoste = Double.POSITIVE_INFINITY;
        Ruta rutaOptima = new Ruta();
        Ruta ruta;

        for (Ciudad ciudad_inicial : ciudades) {
            ruta = new Ruta();
            ruta.addCiudad(ciudad_inicial, problema); // insertar ciudad inicial
            ArrayList<Ciudad> visitadas = ruta.getRuta();

            Ciudad ciudad_actual = ciudad_inicial;
            while (visitadas.size() < ciudades.size()) {
                ciudad_actual = obtenerVecinoMasCercano(
                        ciudad_actual, visitadas, problema);
                ruta.addCiudad(ciudad_actual, problema);
            }

            ruta.terminarRuta(problema);

            if (ruta.getCoste() < mejorCoste) {
                rutaOptima = ruta;
                mejorCoste = ruta.getCoste();
            }
        }

        return rutaOptima;
    }

    private Ciudad obtenerVecinoMasCercano(Ciudad ciudad, ArrayList<Ciudad> visitadas,
                                            Problema problema) {
        Ciudad vecino_mas_cercano = ciudad;
        double menorDistancia = Double.POSITIVE_INFINITY;
        double distancia;
        for (Ciudad vecino : problema.getCiudades()) {
            if (!visitadas.contains(vecino)) {
                distancia = problema.getDistancia(ciudad, vecino);
                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    vecino_mas_cercano = vecino;
                }
            }
        }

        return vecino_mas_cercano;
    }

    public Ruta calcularRutaOptima(Problema problema) {
        ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>(problema.getCiudades());

        Stream<Ruta> flujoRutas = ciudades.stream().map(inicio ->
        {
            Ruta ruta = new Ruta();

            ruta.addCiudad(inicio, problema);
            ruta = calcularRutaVecinoMasCercano(ruta, problema);

            return ruta;
        });

        return flujoRutas.min(Comparator.comparing(Ruta::getCoste)).
                orElseThrow(NoSuchElementException::new);
    }

    private Ruta calcularRutaVecinoMasCercano(Ruta ruta_actual, Problema problema) {
        ArrayList<Ciudad> ciudades  = problema.getCiudades();
        ArrayList<Ciudad> visitadas = ruta_actual.getRuta();
        Ruta nueva_ruta = new Ruta(ruta_actual);

        if (visitadas.size() < ciudades.size()) {
            Ciudad anterior = visitadas.get(visitadas.size() - 1);
            Ciudad siguiente = ciudades.stream().filter(ciudad -> !visitadas.contains(ciudad)).
                    min(Comparator.comparing(
                            ciudad -> problema.getDistancia(ciudad, anterior))).
                    orElseThrow(NoSuchElementException::new);

            nueva_ruta.addCiudad(siguiente, problema);
            nueva_ruta = calcularRutaVecinoMasCercano(nueva_ruta, problema);

        }
        else {
            nueva_ruta.terminarRuta(problema);
        }
        return nueva_ruta;
    }

}
