package ViajanteComercio.base;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ViajanteComercio.base.Utilidades.calcularDistanciaCiudades;

/**
 * Clase basica para representar un problema TSP
 */
public class Problema {
    private ArrayList<Ciudad> ciudades;
    private double[][] distancias;

    public Problema(String datos) {
        try {
            // Lectura lineas y división por espacios
            List<String[]> lineas = Files.
                    lines(Paths.get(datos),
                            StandardCharsets.ISO_8859_1).
                    map(line -> line.split("\\s+")).
                    collect(Collectors.toList());

            // Si la primera línea es de dimensión -> leer interpretar fichero
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

    private void calcularDistancias() {
        int dimension = ciudades.size();
        for (int origen = 0; origen < dimension; origen++) {
            for (int destino = 0; destino < dimension; destino++) {
                distancias[origen][destino] = calcularDistanciaCiudades(
                        ciudades.get(origen), ciudades.get(destino));
            }
        }
    }

    public double getDistancia(Ciudad Origen, Ciudad Destino) {
        int indOrigen = ciudades.indexOf(Origen);
        int indDestino = ciudades.indexOf(Destino);

        return distancias[indOrigen][indDestino];
    }

    public ArrayList<Ciudad> getCiudades() {
        return ciudades;
    }

    public int getNumCiudades() {
        return ciudades.size();
    }
}
