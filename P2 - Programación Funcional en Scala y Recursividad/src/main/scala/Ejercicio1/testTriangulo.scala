package Ejercicio1

import org.scalacheck.Gen
import org.scalacheck.Prop.forAll

object testTriangulo extends org.scalacheck.Properties("test triángulo") {
  val MAXIMO: Int = 100
  val coordenadasExtremos = for {

    // Se genera número de fila: valor comprendido entre 0 y MAXIMO
    // (MAXIMO no está incluido)
    fila <- Gen.choose(0, MAXIMO)

    // Se genera número de columna: o 0 o el valor de la fila. Esto
    // asegura que se trata de un valor de los extremos (X, 0) o
    // (X, X)
    columna <- Gen.oneOf(0, fila)
  } yield (fila, columna)

  val coordenadasInterior = for {
    fila <- Gen.choose(2, MAXIMO)
    columna <- Gen.choose(1, fila - 1)
  } yield (fila, columna)

  property("Elementos en lados del triángulo valen 1") = {
    forAll(coordenadasExtremos) { (i) => {
      val resultado = TrianguloDePascal.calcularValorTrianguloPascal(i._1, i._2)
      println("Fila = " + i._1 + " Columna = " + i._2 + " Resultado = " + resultado
      )
      resultado == 1
    }
    }
  }

  property("Elementos en el interior del triángulo son la suma de los dos elementos superiores") = {
    forAll(coordenadasInterior) { (i) => {
      val resultado = TrianguloDePascal.calcularValorTrianguloPascal(i._1, i._2)
      println("Fila = " + i._1 + " Columna = " + i._2 + " Resultado = " + resultado
      )
      resultado == TrianguloDePascal.calcularValorTrianguloPascal(i._1 - 1, i._2) +
        TrianguloDePascal.calcularValorTrianguloPascal(i._1 - 1, i._2 - 1)
    }
    }
  }
}
