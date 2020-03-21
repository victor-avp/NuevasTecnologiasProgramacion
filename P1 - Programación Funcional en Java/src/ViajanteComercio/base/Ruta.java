package ViajanteComercio.base;

import java.util.ArrayList;

public class Ruta {
    private ArrayList<Ciudad> ruta;
    private double coste;

    public Ruta() {
        ruta = new ArrayList<Ciudad>();
        coste = 0;
    }

    public void addCiudad(Ciudad ciudad, Problema problema) {
        if (!ruta.isEmpty()) {
            Ciudad ciudad_anterior = ruta.get(ruta.size() - 1);
            coste += problema.getDistancia(ciudad_anterior, ciudad);
        }

        ruta.add(ciudad);
    }

    public void terminarRuta(Problema problema) {
        addCiudad(ruta.get(0), problema);
    }

    public double getCoste() {
        return coste;
    }

    public ArrayList<Ciudad> getRuta() { return ruta; }

    @Override
    public String toString() {
        String cadena = "Ruta: ";
        for (Ciudad ciudad : ruta) {
            cadena = cadena + ciudad.getLabel() + " ";
        }

        cadena = cadena + "\nCoste: " + coste + "\n";

        return cadena;
    }
}
