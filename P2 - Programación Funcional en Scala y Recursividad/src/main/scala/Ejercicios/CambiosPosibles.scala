package Ejercicios

/**
 * Punto 5: Contador de posibles cambios de monedas
 */
object CambiosPosibles {
  /**
   * Devuelve todas las combinaciones posibles de un conjunto de tipos de monedas
   * para devolver una cantidad dada.
   *
   * @param cantidad Valor que debe sumar cada una de las combinaciones
   * @param monedas  Tipos de monedas a usar
   * @return Lista con todas las combinaciones (listas de enteros) que suman cantidad
   *         usando los tipos de monedas dados
   */
  def listarCambiosPosibles(cantidad: Int,
                            monedas: List[Int]): List[List[Int]] = {

    // Dada una cantidad y conjunto de tipos de moneda, las combinaciones posibles
    // son las dadas para (cantidad-primer tipo moneda) incorporando una moneda de dicho tipo,
    // junto a las combinaciones para la cantidad total sin usar el primer tipo de moneda
    if (cantidad > 0 && monedas.nonEmpty)
      listarCambiosPosibles(cantidad - monedas.head, monedas).
        map(l => monedas.head :: l) ::: listarCambiosPosibles(cantidad, monedas.tail)
    else if (cantidad == 0) List(List()) // la única forma de dar cambio 0 es no dando nada (lista vacía)
    else List() // No hay ninguna forma de dar cambio sin monedas
  }
}
