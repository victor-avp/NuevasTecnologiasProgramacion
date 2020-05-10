package Pruebas

import Ejercicios.CambiosPosibles.listarCambiosPosibles
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll

// PRUEBAS: Comprobar para diferentes conjuntos monedas y cantidades que
// la suma de todos los resultados es la cantidad dada
// También se comprueba que en problemas sin solución se devuelve una lista vacía (0 soluciones),
// y que con cantidad = 0 se devuelve una solución vacía
object CambiosPosiblesCheck extends org.scalacheck.Properties("Test Cambios Posibles") {
  val MAX_CANTIDAD = 50
  val MAX_VAL_TIPO = 20
  val MAX_TIPOS = 10

  // Generador problemas completos (cantidad y tipos_moneda)
  val problemas: Gen[(Int, List[Int])] = for {
    cantidad <- Gen.choose(1, MAX_CANTIDAD)
    tipos_moneda <- Gen.listOfN(MAX_TIPOS, Gen.choose(1, MAX_VAL_TIPO))
  } yield (cantidad, tipos_moneda)

  // Generador tipos de moneda
  val monedas = for {
    tipos_moneda <- Gen.listOfN(MAX_TIPOS, Gen.choose(1, MAX_VAL_TIPO))
  } yield (tipos_moneda)

  // Propiedad 1: para problemas con solución, todas las soluciones dan la cantidad correcta
  property("Todas las combinaciones suman la cantidad adecuada") = {
    forAll(problemas) { problema => {
      // Al añadir una moneda de valor 1, se garantiza que haya al menos
      // una solución para cada cantidad. Se filtran valores repetidos.
      val tipos = (1 :: problema._2).distinct
      val combinaciones = listarCambiosPosibles(problema._1, tipos)
      println(s"Problema: Cantidad = ${problema._1}, Tipos = $tipos, Nº Soluciones = ${combinaciones.length}")

      combinaciones.forall(s => s.sum == problema._1)
    }
    }
  }

  // Propiedad 2: para problemas sin solución, no se devuelve nada
  property("Para problema sin solución, no se devuelve nada") = {
    // Se filtran valores repetidos
    forAll(monedas) { lista_monedas => {
      val tipos = lista_monedas.distinct
      // El objetivo es un valor inferior a la moneda más pequeña, o, si fuera 0, -1
      val cantidad = if (lista_monedas.min != 1) lista_monedas.min - 1 else -1
      val combinaciones = listarCambiosPosibles(cantidad, lista_monedas)
      println(s"Problema: Cantidad = $cantidad, Tipos = $tipos, Nº Soluciones = ${combinaciones.length}")

      combinaciones.isEmpty
    }
    }
  }

  // Propiedad 3: para cambio 0, se deuvelve como única solución una lista vacía
  property("Para cantidad 0, se deuvelve una única solución que está vacía") = {
    forAll(monedas) { lista_monedas => {
      // Se filtran valores repetidos
      val tipos = lista_monedas.distinct
      val combinaciones = listarCambiosPosibles(0, lista_monedas)
      println(s"Problema: Cantidad = 0, Tipos = $tipos, Nº Soluciones = ${combinaciones.length}")

      combinaciones.length == 1 && combinaciones.head.isEmpty
    }
    }
  }

}
