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
            ArrayList<Ciudad> ciudades_restantes = new ArrayList<Ciudad>(ciudades);
            ruta = new Ruta();
            ruta.addCiudad(ciudad_inicial, problema); // insertar ciudad inicial
            ciudades_restantes.remove(ciudad_inicial);

            Ciudad ciudad_actual = ciudad_inicial;
            while (!ciudades_restantes.isEmpty()) {
                ciudad_actual = obtenerVecinoMasCercano(
                        ciudad_actual, ciudades_restantes, problema);
                ruta.addCiudad(ciudad_actual, problema);
                ciudades_restantes.remove(ciudad_actual);
            }
            ruta.terminarRuta(problema);

            if (ruta.getCoste() < mejorCoste) {
                rutaOptima = ruta;
                mejorCoste = ruta.getCoste();
            }
        }

        return rutaOptima;
    }

    private Ciudad obtenerVecinoMasCercano(Ciudad ciudad, ArrayList<Ciudad> ciudades,
                                           Problema problema) {
        // En versión funcional, la idea es tener un array de visitadas,
        // y filtrar el flujo con las ciudades que no están visitadas.
        Ciudad vecino_mas_cercano = ciudad;
        double menorDistancia = Double.POSITIVE_INFINITY;
        double distancia;
        for (Ciudad vecino : ciudades) {
            distancia = problema.getDistancia(ciudad, vecino);
            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                vecino_mas_cercano = vecino;
            }
        }

        return vecino_mas_cercano;
    }

//    public Ruta calcularRutaOptima(Problema problema) {
//        ArrayList<Ciudad> ciudades = problema.getCiudades();
//        double mejorCoste = Double.POSITIVE_INFINITY;
//        Ruta rutaOptima = new Ruta();
//        Ruta ruta;
//
//        for (Ciudad ciudad_inicial : ciudades) {
//            ruta = new Ruta();
//            ruta.addCiudad(ciudad_inicial, problema); // insertar ciudad inicial
//            ArrayList<Ciudad> visitadas = ruta.getRuta();
//
//            Ciudad ciudad_actual = ciudad_inicial;
//            while (visitadas.size() < ciudades.size()) {
//                ciudad_actual = obtenerVecinoMasCercano2(
//                        ciudad_actual, visitadas, problema);
//                ruta.addCiudad(ciudad_actual, problema);
//            }
//
//            ruta.terminarRuta(problema);
//
//            if (ruta.getCoste() < mejorCoste) {
//                rutaOptima = ruta;
//                mejorCoste = ruta.getCoste();
//            }
//        }
//
//        return rutaOptima;
//    }
//
//    private Ciudad obtenerVecinoMasCercano2(Ciudad ciudad, ArrayList<Ciudad> visitadas,
//                                            Problema problema) {
//        Ciudad vecino_mas_cercano = ciudad;
//        double menorDistancia = Double.POSITIVE_INFINITY;
//        double distancia;
//        for (Ciudad vecino : problema.getCiudades()) {
//            if (!visitadas.contains(vecino)) {
//                distancia = problema.getDistancia(ciudad, vecino);
//                if (distancia < menorDistancia) {
//                    menorDistancia = distancia;
//                    vecino_mas_cercano = vecino;
//                }
//            }
//        }
//
//        return vecino_mas_cercano;
//    }

    public Ruta calcularRutaOptima(Problema problema) {
        ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>(problema.getCiudades());

        Stream<Ruta> flujoRutas = ciudades.stream().map(c ->
        {
            Ruta ruta = new Ruta();
            ruta.addCiudad(c, problema);
            ArrayList<Ciudad> visitadas = ruta.getRuta();

//            IntStream.range(1, ciudades.size()).forEach(i ->
//                ruta.addCiudad(
//                        ciudades.stream().filter().
//                        min(Comparator.comparing(problema.getDistancia()))
//                )
//            );

            //Function<Ciudad, Double > distancia = (Ciudad c1) -> problema.getDistancia(c1, c);

            Ciudad actual = c;

            ArrayList<Ciudad> ciudades_restantes = new ArrayList<>(ciudades);
            ciudades_restantes.remove(actual);
            while (!ciudades_restantes.isEmpty()) {
                Ciudad finalActual = actual;
                actual = ciudades_restantes.stream().
                                min(Comparator.comparing(
                                        (Ciudad c1) -> problema.getDistancia(c1, finalActual))).
                                orElseThrow(NoSuchElementException::new);
                 ruta.addCiudad(actual, problema);
                 ciudades_restantes.remove(actual);
            }

            ruta.terminarRuta(problema);

//            System.out.println(ruta);

            return ruta;
        });

        return flujoRutas.min(Comparator.comparing(Ruta::getCoste)).
                orElseThrow(NoSuchElementException::new);
    }
}
