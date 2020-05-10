package Pruebas

import Ejercicios.SeriesRecurrente.serieRecurrente
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll

// PRUEBAS: Comprobar que dada una serie (elem iniciales. y relación), elementos
// x >= 2 contienen como valor el resultado de relación(x-2,x-1).
// Tambiń comprobar que x0 y x1 son los que correpsonden
object SerieRecurrenteCheck extends org.scalacheck.Properties("Test Serie Recurrente") {
  val MAX_COEF = 10
  val MAX_INI = 20
  val MAX_ELEM = 50

  // Generador de series. Está definida por sus elementos iniciales y los coeficientes
  // usados en la relación. La relación es una suma/resta de los dos elementos previos (con coeficientes),
  // como en los ejemplos del guión
  val series = for {
    elem0 <- Gen.choose(0, MAX_INI)
    elem1 <- Gen.choose(0, MAX_INI)
    coef_prev <- Gen.choose(-MAX_COEF, MAX_COEF)
    coef_actual <- Gen.choose(-MAX_COEF, MAX_COEF)
  } yield (elem0, elem1, coef_prev, coef_actual)

  // Generadores valores a calcular de la serie mayores o igual a 2
  val elementos = for {
    elem <- Gen.choose(2, MAX_ELEM)
  } yield (elem)

  // Propiedad: el valor de una serie corresponde con la relación dada por los dos previos
  property("El elemento n-ésimo de una serie corresponde con la relación de los dos elementos previos") = {
    forAll(series) { serie => {
      val relacion = (prev: Int, act: Int) => serie._3 * prev + serie._4 * act

      def func_serie: Int => Int = serieRecurrente(serie._1, serie._2, relacion)

      println(s"SERIE: elem0 = ${serie._1}, elem1 = ${serie._2}, nuevo = ${serie._3}prev + (${serie._4}actual)")
      forAll(elementos) { elem => {
        val resultado = func_serie(elem)
        println(s"   Resultado para índice $elem: $resultado")
        resultado == relacion(func_serie(elem - 2), func_serie(elem - 1))
      }
      }
    }
    }
  }

  // Propiedad: al calcular los valores 0 y 1 de la serie, se obtienen los elementos iniciales
  // definidos para la misma
  property("Los elementos 0 y 1 de la serie corresponden con los definidos") = {
    forAll(series) { serie => {
      val relacion = (prev: Int, act: Int) => serie._3 * prev + serie._4 * act

      def func_serie: Int => Int = serieRecurrente(serie._1, serie._2, relacion)

      println(s"SERIE: elem0 = ${serie._1}, elem1 = ${serie._2}, nuevo = ${serie._3}prev + (${serie._4}actual)")
      val resultado0 = func_serie(0)
      val resultado1 = func_serie(1)
      println(s"   Resultado para índice 0: $resultado0")
      println(s"   Resultado para índice 1: $resultado1")
      resultado0 == serie._1 && resultado1 == serie._2
    }
    }
  }


}
