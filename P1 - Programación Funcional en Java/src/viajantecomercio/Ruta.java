package viajantecomercio;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Clase ruta, que representa una solución para el problema TSP
 */
public class Ruta {
    /**
     * Array ordenado de ciudades con la ruta
     */
    private ArrayList<Ciudad> ruta;

    /**
     * Distancia total de la ruta
     */
    private double coste;

    /**
     * Constructor por defecto, inicializa datos miembro
     */
    public Ruta() {
        ruta = new ArrayList<>();
        coste = 0;
    }

    /**
     * Constructor de copia
     *
     * @param otra_ruta ruta a copiar
     */
    public Ruta(Ruta otra_ruta) {
        ruta = new ArrayList<>(otra_ruta.ruta);
        coste = otra_ruta.getCoste();
    }

    /**
     * Añade una ciudad a la ruta actual
     *
     * @param ciudad   ciudad a añadir
     * @param problema ámbito de la ruta. Necesario para calcular el coste entre
     *                 la última ciudad y la nueva
     */
    public void addCiudad(Ciudad ciudad, Problema problema) {
        // Si la ruta no está vacía, hay que actualizar el coste
        if (!ruta.isEmpty()) {
            Ciudad ciudad_anterior = ruta.get(ruta.size() - 1);
            coste += problema.getDistancia(ciudad_anterior, ciudad);
        }

        ruta.add(ciudad);
    }

    /**
     * Cierra la ruta, incorporando la primera ciudad de esta
     *
     * @param problema ámbito de la ruta. Necesario para poder actualizar el coste
     */
    public void terminarRuta(Problema problema) {
        addCiudad(ruta.get(0), problema);
    }

    /**
     * Obtiene el coste
     *
     * @return
     */
    public double getCoste() {
        return coste;
    }

    /**
     * Obtiene el array de ciudades ordenado que forman la ruta
     *
     * @return
     */
    public ArrayList<Ciudad> getRuta() {
        return ruta;
    }

    @Override
    public String toString() {
        String cadena = "Ruta: ";

        // Enfoque funcional para construir el string de ciudades
        cadena += ruta.stream().map(Ciudad::getLabel).
                collect(Collectors.joining(" "));

        cadena = cadena + "\nCoste: " + coste + "\n";

        return cadena;
    }
}
