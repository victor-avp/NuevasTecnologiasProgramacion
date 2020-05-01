package Ejercicio1

object TrianguloDePascal {

  // Incluso con Long, se desbordaría para x en rango 0-100
  def factorial(x: Int): BigInt = {
    @annotation.tailrec
    def go(x: Int, acum: BigInt): BigInt = {
      if (x <= 1) acum
      else go(x - 1, acum * x)
    }

    go(x, 1)
  }

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
