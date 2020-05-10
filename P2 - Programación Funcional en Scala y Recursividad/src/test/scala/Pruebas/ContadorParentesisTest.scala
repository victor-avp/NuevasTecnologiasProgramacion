package Pruebas

import Ejercicios.ContadorParentesis.chequearBalance
import org.scalatest.FunSuite

class ContadorParentesisTest extends FunSuite {

  // Prueba 1
  test("chequear balance: '(if (zero? x) max (/ 1 x))' está balanceada") {
    assert(chequearBalance("(if (zero? x) max (/ 1 x))".toList))
  }

  // Prueba 2
  test("chequear balance: 'Te lo dije...' está balanceada") {
    assert(chequearBalance("Te lo dije (eso está (todavía) hecho)".toList))
  }

  // Prueba 3
  test("chequear balance: ':-)' no está balanceada") {
    assert(!chequearBalance(":-)".toList))
  }

  // Prueba 4
  test("chequear balance: no basta con contar sin más") {
    assert(!chequearBalance("())(".toList))
  }

  // Prueba 5
  test("(if (a > b) (b/a) else (a/(b*b)))") {
    assert(chequearBalance("(if (a > b) (b/a) else (a/(b*b)))".toList))
  }

  // Prueba 6
  test("(ccc(ccc)cc((ccc(c))))") {
    assert(chequearBalance("(ccc(ccc)cc((ccc(c))))".toList))
  }

  // Prueba 7
  test("(if (a > b) b/a) else (a/(b*b)))") {
    assert(!chequearBalance("(if (a > b) b/a) else (a/(b*b)))".toList))
  }

  // Prueba 8
  test("(ccc(ccccc((ccc(c))))") {
    assert(!chequearBalance("(ccc(ccccc((ccc(c))))".toList))
  }

  // Prueba 9
  test("())()()))") {
    assert(!chequearBalance("())()()))".toList))
  }
}
