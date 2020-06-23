package Pruebas

import ArbolBinario.{ArbolVacio, ArbolBinario => AB}
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll

// Pruebas con árboles binarios generados alatoriametne:
//  - Check aplicar función hojas
//  - Check Nº Nodos
//  - Check Sumar hojas

object ArbolesAleatoriosCheck extends org.scalacheck.Properties("Test Funciones en Hojas") {
  val MAX_HOJA = 400
  val MAX_COEF = 10
  val MAX_DESPL = 10

  // Generador de funciones
  val funciones = for {
    coef <- Gen.choose(-MAX_COEF, MAX_COEF)
    despl <- Gen.choose(-MAX_DESPL, MAX_DESPL)
  } yield (coef, despl)

  // Generador de hojas
  val genHoja = for {
    v <- Gen.choose(0, MAX_HOJA)
  } yield AB(v)

  // Generador de nodos internos: cada vez que se genera un árbol, se reduce el tamaño
  // a la mitad
  val genNodo = for {
    izq <- Gen.sized(h => Gen.resize(h / 2, genArbol))
    der <- Gen.sized(h => Gen.resize(h / 2, genArbol))
  } yield AB(izq, der)

  val genNil = Gen.const(ArbolVacio)

  // Generador de árboles
  def genArbol: Gen[AB] = Gen.sized {
    profundidad =>
      if (profundidad <= 0) {
        genNil
      } else {
        Gen.frequency(1 -> genHoja, 9 -> genNodo)
      }
  }

  property("El árbol resultante de aplicar una función en las hojas es correcto\n") = {
    forAll(genArbol) {
      arbol => {
        forAll(funciones) {
          coefs => {
            val f: Int => Int = x => x * coefs._1 + coefs._2
            val arbol_mod = AB.aplicarHojas(arbol, f)
            AB.listarValoresHojas(arbol_mod) == AB.listarValoresHojas(arbol).map(f) &&
              AB.numNodosTotal(arbol_mod) == AB.numNodosTotal(arbol)
          }
        }
      }
    }
  }

  property("Nº Nodos total = Nº Hojas + Nº Nodos Internos\n") = {
    forAll(genArbol) {
      arbol => {
        AB.numNodosTotal(arbol) == AB.numHojas(arbol) + AB.numNodosInternos(arbol)
      }
    }
  }

  property("Nº Nodos total es la suma del Nº nodos de los hijos + 1\n") = {
    forAll(genArbol) {
      arbol => {
        arbol match {
          case ArbolVacio => AB.numNodosTotal(arbol) == 0
          case _ => AB.numNodosTotal(arbol) == AB.numNodosTotal(arbol.izquierda.getOrElse(ArbolVacio)) +
            AB.numNodosTotal(arbol.derecha.getOrElse(ArbolVacio)) + 1
        }
      }
    }
  }

  property("La suma de las hojas es correcta\n") = {
    forAll(genArbol) {
      arbol => {
        AB.listarValoresHojas(arbol).sum == AB.sumarHojas(arbol)
      }
    }
  }
}
