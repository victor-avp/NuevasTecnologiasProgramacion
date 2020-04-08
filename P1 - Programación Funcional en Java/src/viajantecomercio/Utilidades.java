package viajantecomercio;

/**
 * Clase de funciones auxiliares
 */
public class Utilidades {

    /**
     * Calcula y devuelve la distancia euclídea entre dos ciudades
     *
     * @param ciudad1
     * @param ciudad2
     * @return distancia entre ciudad1 y ciudad2
     */
    public static double calcularDistanciaCiudades(Ciudad ciudad1, Ciudad ciudad2) {
        double distancia_x = ciudad1.getX() - ciudad2.getX();
        double distancia_y = ciudad1.getY() - ciudad2.getY();

        return Math.sqrt(distancia_x * distancia_x + distancia_y * distancia_y);
    }
}
