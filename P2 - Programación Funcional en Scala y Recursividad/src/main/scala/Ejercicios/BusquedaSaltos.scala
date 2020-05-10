package Ejercicios

/**
 * Punto 7: Búsqueda binaria a saltos
 */
object BusquedaSaltos {

  /**
   * Devuelve la posición en la que se encuentra la primera ocurrencia del elemento
   * a buscar dentro de la colección, usando una Búsqueda a Saltos.
   * Si el elemento no está en la colección, se devuelve -1.
   * Función genérica: se pueden usar colecciones de cualquier tipo que tenga orden.
   * Precondicion: la colección debe estar ordenada
   *
   * @param coleccion Array de tipo genérico ordenado
   * @param aBuscar   Valor a buscar dentro de la colección
   * @param criterio  Criterio para comparar dos elementos del tipo dado,
   *                  debe indicar si el primer elemento es mayor que el segundo
   * @tparam A
   * @return Índice de la primera ocurrencia de aBuscar en coleccion. Si no hay
   *         ninguna ocurrencia, -1.
   */
  def busquedaSaltos[A](coleccion: Array[A], aBuscar: A,
                        criterio: (A, A) => Boolean): Int = {

    // Cálculo del tamaño del bloque
    val tam_bloque = math.sqrt(coleccion.length).toInt

    // Función recursiva anidada que, en primer lugar, busca el bloque (inf, sup)
    // en el que puede encontrarse el elemento, y después busca el elemento dentro del bloque
    @scala.annotation.tailrec
    def go(inf: Int, sup: Int): Int = {

      // Función recursiva anidada que recorre un bloque secuencialmente (acotado por sup)
      // hasta encontrar aBuscar o determinar que no está presente
      @scala.annotation.tailrec
      def recorrerBloque(i: Int): Int = {
        if (i == sup) -1 // Fin del bloque sin encontrar el elemento
        else if (coleccion(i) == aBuscar) i // Elemento encontrado
        else recorrerBloque(i + 1) // Continuar recorriendo bloque
      }

      // Cuerpo función go: búsqueda del bloque
      if (inf == coleccion.length) -1 // Se llega al final: no existe el elemento
      else if (criterio(aBuscar, coleccion(sup - 1)))
        go(sup, math.min(sup + tam_bloque, coleccion.length))
      else recorrerBloque(inf) // Caso base: encontrado elemento >= aBuscar,
      // se pasa a recorrer su bloque
    }

    // Punto de partida: bloque al inicio del array
    go(0, tam_bloque)

  }

}
