package Ejercicios

/**
 * Punto 4: Balanceo de Cadenas con Paréntesis
 */
object ContadorParentesis {

  /**
   * Función que comprueba si una cadena con paréntesis está balanceada
   *
   * @param cadena Cadena a analizar
   * @return Boolean que indica si la cadena está balanceada o no
   */
  def chequearBalance(cadena: List[Char]): Boolean = {

    // Función recursiva anidada que recorre la cadena la vez que cuenta el
    // número de paréntesis abiertos por cerrar
    @scala.annotation.tailrec
    def go(cadena: List[Char], n: Int): Boolean = {
      if (cadena.isEmpty) n == 0 // Caso base, si hay 0 paréntesis por cerrar: balanceada
      else {
        cadena.head match {
          case '(' => go(cadena.tail, n + 1) // Si se encuentra (, se incrementa n
          case ')' =>
            if (n > 0) go(cadena.tail, n - 1) // Si se encuentra ) y n positivo, se reduce n
            else false // Se cierra un paréntesis sin haber ninguno abierto pendiente: desbalanceada
          case _ => go(cadena.tail, n) // Si se encuentr otro caracter, se pasa al siguiente
        }
      }
    }

    // Punto de partida: la cadena completa y ningún paréntesis por cerrar
    go(cadena, 0)
  }
}
