package Benchmark

import Ejercicios.BusquedaBinaria.busquedaBinaria
import Ejercicios.BusquedaSaltos.busquedaSaltos
import org.scalameter.api._

import scala.util.Random

object BenchmarkBusquedas extends Bench.LocalTime {

  // Criterio > para int y char
  val criterioInt = (a: Int, b: Int) => a > b
  val criterioChar = (a: Char, b: Char) => a > b

  // Generador de tamaño de vectores contemplados
  val sizes = Gen.range("tam")(100000, 1000000, 100000)

  // Arrays más grandes a usar de ambos tipos. Están ordenados.
  val arrayEnteros: Array[Int] = Array.fill(1000000)(Random.nextInt(50)).sorted
  val arrayChar: Array[Char] = Array.fill(1000000)(Random.nextPrintableChar).sorted

  // Benchmark para array de enteros
  performance of "Rendimiento con enteros" in {

    measure method "BusquedaBinaria" in {
      using(sizes) in {
        s => busquedaBinaria(arrayEnteros.take(s), arrayEnteros(s - 1), criterioInt)
      }
    }

    measure method "BusquedaSaltos" in {
      using(sizes) in {
        s => busquedaSaltos(arrayEnteros.take(s), arrayEnteros(s - 1), criterioInt)
      }
    }
  }

  // Benchmark para array de char
  performance of "Rendimiento con char" in {

    measure method "BusquedaBinaria" in {
      using(sizes) in {
        s => busquedaBinaria(arrayChar.take(s), arrayChar(s - 1), criterioChar)
      }
    }

    measure method "BusquedaSaltos" in {
      using(sizes) in {
        s => busquedaSaltos(arrayChar.take(s), arrayChar(s - 1), criterioChar)
      }
    }
  }

  // Se obtienen tiempos medios
  override def aggregator: Aggregator[Double] = Aggregator.average


}
