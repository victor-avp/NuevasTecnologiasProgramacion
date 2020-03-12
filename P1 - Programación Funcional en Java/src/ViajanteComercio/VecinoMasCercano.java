package ViajanteComercio;

import java.util.ArrayList;

public class VecinoMasCercano implements HeuristicaTSP {
    public Ruta calcularRutaOptima(Problema problema) {
        ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>(problema.getCiudades());
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
            System.out.println(ruta);

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
}
