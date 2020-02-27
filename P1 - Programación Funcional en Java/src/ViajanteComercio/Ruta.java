package ViajanteComercio;

import java.util.ArrayList;

import static ViajanteComercio.Utilidades.calcularDistanciaCiudades;

public class Ruta {
    private ArrayList<Ciudad> ruta;
    private double coste;

    public void addCiudad(Ciudad ciudad) {
        if (!ruta.isEmpty()) {
            Ciudad ciudad_anterior = ruta.get(ruta.size() - 1);
            coste += calcularDistanciaCiudades(ciudad_anterior, ciudad);
        }

        ruta.add(ciudad);
    }

    public void terminarRuta() {
        addCiudad(ruta.get(0));
    }

//    @Override
//    public String toString() {
//
//    }
}
