package Ejercicios

/**
 * Punto 6: Búsqueda binaria genérica
 */
object BusquedaBinaria {

  /**
   * Devuelve la posición en la que se encuentra la primera ocurrencia del elemento
   * a buscar dentro de la colección, usando una Búsqueda Binaria.
   * Si el elemento no está en la colección, se devuelve -1.
   * Función genérica: se pueden usar colecciones de cualquier tipo que tenga orden.
   * Precondicion: la colección debe estar ordenada
   *
   * @param coleccion Array de tipo genérico ordenado
   * @param aBuscar   Valor a buscar dentro de la colección
   * @param criterio  Criterio de ordenación del array
   * @tparam A
   * @return Índice de la primera ocurrencia de aBuscar en coleccion. Si no hay
   *         ninguna ocurrencia, -1.
   */
  def busquedaBinaria[A](coleccion: Array[A], aBuscar: A,
                         criterio: (A, A) => Boolean): Int = {

    @scala.annotation.tailrec
    def go(inf: Int, sup: Int): Int = {
      if (inf < sup) {
        val medio = (inf + sup) / 2
        // Si aBuscar > elemento medio en intervalo: pasar a mitad derecha
        if (criterio(aBuscar, coleccion(medio))) go(medio + 1, sup)
        else go(inf, medio) // En caso contrario, mitad izquierda (medio inclusive)
      } // Caso base: cruce entre if y sup en el que se ha llegado al elemento
      else if (coleccion(inf) == aBuscar) inf
      // cruce entre if y sup pero con elemento distinto -> coleccion no contiene aBuscar
      else -1
    }

    // Punto de partida: se considera el array completo
    go(0, coleccion.length - 1)
  }

}
