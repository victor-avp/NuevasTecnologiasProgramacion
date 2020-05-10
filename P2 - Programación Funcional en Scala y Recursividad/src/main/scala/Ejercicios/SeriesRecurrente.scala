package Ejercicios

/**
 * Punto 3: Series definidas de forma recurrente
 */
object SeriesRecurrente {

  /**
   * Función que devuelve el valor de una serie recurrente dada para un índice concreto
   * Se utiliza currying
   *
   * @param elem0    Elemento 0 de la serie
   * @param elem1    Elemento 1 serie
   * @param relacion Relación que indica el valor del siguiente elemento a partir
   *                 del anterior y el actual
   * @param n        Elemento de la serie a calcular
   * @return Elemento n-esimo de la serie
   */
  def serieRecurrente(elem0: Int, elem1: Int, relacion: (Int, Int) => Int)(n: Int): Int = {

    // Función anidada tailrecursive que calcula el elemento n-ésimo
    // calculando sucesivamente el siguiente elemento de la serie hasta llegar al objetivo
    @scala.annotation.tailrec
    def go(n: Int, prev: Int, act: Int): Int = {
      if (n == 0) prev
      else go(n - 1, act, relacion(prev, act))
    }

    // Punto de partida: quedan n aplicaciones de la serie, se parte de los dos primeros elementos
    go(n, elem0, elem1)
  }

  // Definición de las series presentadas en el guión a partir de la función
  // serieRecurrente

  def serieFibonacci: Int => Int = serieRecurrente(0, 1, (prev, act) => prev + act)

  def serieLucas: Int => Int = serieRecurrente(2, 1, (prev, act) => prev + act)

  def seriePell: Int => Int = serieRecurrente(2, 6, (prev, act) => prev + 2 * act)

  def seriePellLucas: Int => Int = serieRecurrente(2, 2, (prev, act) => prev + 2 * act)

  def serieJacobsthal: Int => Int = serieRecurrente(0, 1, (prev, act) => 2 * prev + act)
}
