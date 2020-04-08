package viajantecomercio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * Implementación de HeuristicaTSP con Heurística Vecino Más Cercano
 */
public class VecinoMasCercano implements HeuristicaTSP {

    /**
     * Devuelve una ruta óptima para un problema dado, utilizando Vecino Más Cercano
     * Se trata de la implementación imperativa.
     *
     * @param problema Problema para el que calcular la ruta óptima
     * @return Ruta óptima calculada
     */
    public Ruta calcularRutaOptimaNoFuncional(Problema problema) {
        ArrayList<Ciudad> ciudades = problema.getCiudades();
        double mejorCoste = Double.POSITIVE_INFINITY;
        Ruta rutaOptima = new Ruta();
        Ruta ruta;

        // Utilizando cada ciudad como punto de partida, se construye una solución greedy
        // añadiendo siempre la ciudad más cercana a la última de la ruta actual
        // Se conserva en cada momento la ruta de menor coste
        for (Ciudad ciudad_inicial : ciudades) {
            ruta = new Ruta();
            ruta.addCiudad(ciudad_inicial, problema); // insertar ciudad inicial

            // Array con las ciudades visitadas. Referencia al array de ciudades de la ruta actual
            ArrayList<Ciudad> visitadas = ruta.getRuta();

            Ciudad ciudad_actual = ciudad_inicial;

            // Mientras queden ciudades por visitar
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

    /**
     * Devuelve la ciudad de un problema más cercana a una dada, excluyendo las ya visitadas
     *
     * @param ciudad    Ciudad origen para la que obtener la más cercana
     * @param visitadas Array de ciudades ya visitadas y por tanto a ignorar
     * @param problema  Problema con el conjunto de ciudades posibles
     * @return Ciudad más cercana a la pasada como parámetro
     */
    private Ciudad obtenerVecinoMasCercano(Ciudad ciudad, ArrayList<Ciudad> visitadas,
                                           Problema problema) {
        Ciudad vecino_mas_cercano = ciudad;
        double menorDistancia = Double.POSITIVE_INFINITY;
        double distancia;

        // Para todas las ciudades del problema, comprobar las no visitadas y
        // comparar su distancia a la ciudad origen, conservando la más cercana encontrada
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

    /**
     * Devuelve una ruta óptima para un problema dado, utilizando Vecino Más Cercano
     * Se trata de la implementación funcional.
     *
     * @param problema Problema para el que calcular la ruta óptima
     * @return Ruta óptima calculada
     */
    public Ruta calcularRutaOptima(Problema problema) {
        ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>(problema.getCiudades());

        // Obtener un flujo de Rutas, uno por ciudad (que actúa como punto de partida)
        Stream<Ruta> flujoRutas = ciudades.stream().map(inicio ->
        {
            Ruta ruta = new Ruta();

            ruta.addCiudad(inicio, problema);
            ruta = calcularRutaVecinoMasCercano(ruta, problema);

            return ruta;
        });

        // Obtener la ruta de menor coste del flujo
        return flujoRutas.min(Comparator.comparing(Ruta::getCoste)).
                orElseThrow(NoSuchElementException::new);
    }

    /**
     * Partiendo de una ruta base, construye una ruta completa con la heurística
     * del vecino más cercano.
     * Se trata de una función recursiva.
     *
     * @param ruta_actual Ruta de partida
     * @param problema    Problema con el conjunto de ciudades a considerar
     * @return Ruta óptima calculada
     */
    private Ruta calcularRutaVecinoMasCercano(Ruta ruta_actual, Problema problema) {
        ArrayList<Ciudad> ciudades = problema.getCiudades();
        ArrayList<Ciudad> visitadas = ruta_actual.getRuta();
        Ruta nueva_ruta = new Ruta(ruta_actual);

        // Si la ruta dada no está completa, la recursividad continúa
        if (visitadas.size() < ciudades.size()) {
            // Se obtiene la ciudad más cercana a la última de la ruta dada,
            // excluyendo las ya visitadas.
            Ciudad anterior = visitadas.get(visitadas.size() - 1);
            Ciudad siguiente = ciudades.stream().filter(ciudad -> !visitadas.contains(ciudad)).
                    min(Comparator.comparing(
                            ciudad -> problema.getDistancia(ciudad, anterior))).
                    orElseThrow(NoSuchElementException::new);

            // Se añade la ciudad a la ruta y se procede a añadir la siguiente con una nueva llamada
            nueva_ruta.addCiudad(siguiente, problema);
            nueva_ruta = calcularRutaVecinoMasCercano(nueva_ruta, problema);


        } else { // Case base: la ruta esta completa, por lo que se cierra
            nueva_ruta.terminarRuta(problema);
        }
        return nueva_ruta;
    }

}
