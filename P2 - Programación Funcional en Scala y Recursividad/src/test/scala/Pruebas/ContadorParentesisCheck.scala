package Pruebas

import Ejercicios.ContadorParentesis.chequearBalance
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll

object ContadorParentesisCheck extends org.scalacheck.Properties("test triángulo") {
  val MAXIMALONGITUD = 20
  // Generación de cadenas de longitud n: forma de uso strGen(10) para cadenas
  // de 10 caracteres
  val strGen: Int =>
    Gen[String] = (n: Int) =>
    Gen.listOfN(n, Gen.oneOf('(', ')', Gen.alphaChar.sample.get)).
      map(_.mkString)

  property("Balance de cadenas") = {
    forAll(strGen(Gen.choose(1, MAXIMALONGITUD).sample.get)) {
      cadena => {
        val condition = chequearBalance(cadena.toList)
        println("Comprobando cadena: " + cadena + ", Resultado: " + condition)
        for (i <- 2 until cadena.length) {
          val substring = cadena.substring(0, i)
          val openCount = substring.filter(c => c == '(').length
          val closeCount = substring.filter(c => c == ')').length
          condition == ((openCount - closeCount) >= 0)
        }
      }
        true
    }
  }

}
