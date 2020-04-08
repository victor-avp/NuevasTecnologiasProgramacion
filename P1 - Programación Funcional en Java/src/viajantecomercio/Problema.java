package viajantecomercio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static viajantecomercio.Utilidades.calcularDistanciaCiudades;

/**
 * Clase basica para representar un problema TSP
 */
public class Problema {
    /**
     * Ciudades que conforman el problema
     */
    private ArrayList<Ciudad> ciudades;

    /**
     * Matriz con las distancias entre todas las ciudades
     */
    private double[][] distancias;

    /**
     * Constructor de la clase usando un enfoque funcional
     *
     * @param datos Ruta del archivo con los datos del problema.
     *              Este debe tener una estructura concreta
     */
    public Problema(String datos) {
        try {
            // Lectura lineas y división por espacios
            List<String[]> lineas = Files.
                    lines(Paths.get(datos),
                            StandardCharsets.ISO_8859_1).
                    map(line -> line.split("\\s+")).
                    collect(Collectors.toList());

            // Si la primera línea es de dimensión -> leer e interpretar fichero
            if (lineas.get(0).length == 2 && lineas.get(0)[0].equals("DIMENSION:")) {
                // Leer primera línea -> Dimensión
                int dimension = Integer.parseInt(lineas.get(0)[1]);
                distancias = new double[dimension][dimension];

                // Leer resto de líneas -> Obtener array de ciudades
                ciudades = lineas.stream().filter(line -> line.length == 3).
                        map(line -> new Ciudad(
                                line[0],
                                Double.parseDouble(line[1]),
                                Double.parseDouble(line[2]))).
                        collect(Collectors.toCollection(ArrayList::new));
            }

            // Calcular distancias
            calcularDistancias();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método auxiliar para el calculo de las distancias entre las ciudades
     * en el constructor
     */
    private void calcularDistancias() {
        int dimension = ciudades.size();

        IntStream.range(0, dimension).forEach(origen ->
                IntStream.range(0, dimension).forEach(destino ->
                        distancias[origen][destino] = calcularDistanciaCiudades(
                                ciudades.get(origen), ciudades.get(destino))));
    }

    /**
     * Obtiene la distancia entre dos ciudades
     *
     * @param origen
     * @param destino
     * @return Distancia entre Origen y Destino
     */
    public double getDistancia(Ciudad origen, Ciudad destino) {
        int indOrigen = ciudades.indexOf(origen);
        int indDestino = ciudades.indexOf(destino);

        return distancias[indOrigen][indDestino];
    }

    /**
     * Obtiene el array de ciudades del problema
     */
    public ArrayList<Ciudad> getCiudades() {
        return ciudades;
    }

    /**
     * Obtiene número de ciudades del problema
     */
    public int getNumCiudades() {
        return ciudades.size();
    }
}
