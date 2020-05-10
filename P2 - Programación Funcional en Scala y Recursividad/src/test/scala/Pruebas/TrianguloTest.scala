package Pruebas

import Ejercicios.TrianguloDePascal.calcularValorTrianguloPascal
import org.scalatest.FunSuite

class TrianguloTest extends FunSuite {
  test("Valor en extremo debe ser 1 independientemente de la funcion") {
    assert(calcularValorTrianguloPascal(5, 5) == 1)
    assert(calcularValorTrianguloPascal(5, 0) == 1)
  }
}
