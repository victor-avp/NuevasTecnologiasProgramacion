package Ejercicios

/**
 * Punto 2: Triángulo de Pascal
 */
object TrianguloDePascal {

  /**
   * Función recursiva que devuelve el factorial de un número
   *
   * @param x Número del que se quiere obtener el factorial
   * @return Factorial de x
   */
  def factorial(x: Int): BigInt = {

    // Función tailrecursive que calcula el factorial acumulando
    // la multiplicación de los valores hasta llegar al objetivo
    @annotation.tailrec
    def go(x: Int, acum: BigInt): BigInt = {
      if (x <= 1) acum
      else go(x - 1, acum * x)
    }

    // Punto de partida: valor inicial x, acumulador a 1
    go(x, 1)
  }

  /**
   * Función que devuelve un elemento del triángulo de Pascal
   * con ayuda de la función recursiva factorial
   *
   * @param fila    Fila del elememnto
   * @param columna Columna del elemento
   * @return Elemento correspondiente del triángulo
   */
  def calcularValorTrianguloPascal(fila: Int, columna: Int): BigInt = {
    factorial(fila) / (factorial(columna) * factorial(fila - columna))
  }

  /**
   * Método main: en realidad no es necesario porque el desarrollo
   * debería guiarse por los tests de prueba
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {
    println("................... Triángulo de Pascal ...................")

    // Se muestran 10 filas del triángulo de Pascal
    for (row <- 0 to 10) {
      // Se muestran 10 y 10 filas
      for (col <- 0 to row)
        print(calcularValorTrianguloPascal(row, col) + " ")

      // Salto de línea para mejorar la presentación
      println()
    }

    // Se muestra el valor que debe ocupar la columna 5 en la fila 15
    print(calcularValorTrianguloPascal(15, 5))
  }
}
