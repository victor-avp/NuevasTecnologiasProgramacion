package Pruebas

import Ejercicios.BusquedaSaltos.busquedaSaltos
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll

// PRUEBA: Para diferentes vectores (variantes en tamaño y elementos), pero
// siempre ordenados, el resultado de indexOf debe coincidir.
// Se prueba con tipos diferentes de datos (int y char)
object BusquedaSaltosCheck extends org.scalacheck.Properties("Test Búsqueda a Saltos"){
  val MAX_TAM = 50
  val MAX_VAL = 25

  // Criterios enteros y char
  val criterioInt = (a: Int, b: Int) => a > b
  val criterioChar = (a: Char, b: Char) => a > b

  // Generadores problemas enteros y char (vector y valor a buscar)
  val problemaEnteros = for {
    vector <- Gen.listOfN(MAX_TAM, Gen.choose(0, MAX_VAL))
    valor <- Gen.choose(0, MAX_VAL)
  } yield (vector, valor)

  val problemaChar = for {
    vector <- Gen.listOfN(MAX_TAM, Gen.alphaChar.sample.get)
    valor <- Gen.alphaChar
  } yield (vector, valor)

  // Test con enteros
  property("Búsqueda a saltos sobre enteros coincide con IndexOf") = {
    forAll(problemaEnteros) {
      problema => {
        val coleccion  = problema._1.sorted.toArray
        val valor = problema._2
        println("Comprobación con una lista de enteros")
        busquedaSaltos(coleccion, valor, criterioInt) == coleccion.indexOf(valor)
      }
    }
  }

  // Test con char
  property("Búsqueda a saltos sobre char coincide con IndexOf") = {
    forAll(problemaChar) {
      problema => {
        val coleccion  = problema._1.sorted.toArray
        val valor = problema._2
        println("Comprobación con una lista de char")
        busquedaSaltos(coleccion, valor, criterioChar) == coleccion.indexOf(valor)
      }
    }
  }

}
