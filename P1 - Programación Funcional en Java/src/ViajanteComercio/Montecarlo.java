package ViajanteComercio;

import java.util.ArrayList;
import java.util.Collections;

public class Montecarlo implements HeuristicaTSP {

    private int veces = 10000;

    public Montecarlo() {}

    public Montecarlo(int veces) {
        this.veces = veces;
    }

    public Ruta calcularRutaOptima(Problema problema) {

        ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>(problema.getCiudades());
        double mejorCoste = Double.POSITIVE_INFINITY;
        Ruta rutaOptima = new Ruta();
        Ruta ruta;

        for (int i = 0; i < veces; i++) {
            ruta = new Ruta();
            Collections.shuffle(ciudades);
            for (Ciudad ciudad: ciudades) {
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
}
