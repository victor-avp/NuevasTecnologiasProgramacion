package ViajanteComercio;

public class Utilidades {
    public static double calcularDistanciaCiudades(Ciudad ciudad1, Ciudad ciudad2) {
        double distancia_x = ciudad1.getX() - ciudad2.getX();
        double distancia_y = ciudad1.getY() - ciudad2.getY();

        return Math.sqrt(distancia_x*distancia_x + distancia_y*distancia_y);
    }
}
