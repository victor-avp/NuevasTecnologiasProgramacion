import ArbolBinario.{ArbolBinario => AB, _}

object EjemploUsoArbolBinario extends App {

  // Definición del árbol
  val a1 = AB(AB(5), AB(3))
  val a2 = AB(AB(4), AB(17))
  val a3 = AB(a1, a2)
  val a4 = AB(a3, AB(16))
  val a5 = AB(AB(22), AB(73))
  val a6 = AB(ArbolVacio, a5)
  val a7 = AB(a4, a6)
  val a8 = AB(a7, ArbolVacio)

  println("Árbol de partida")
  println(a8)

  // Operaciones
  println(s"Nº Nodos totales: ${AB.numNodosTotal(a8)}")
  println(s"Nº Nodos internos: ${AB.numNodosInternos(a8)}")
  println(s"Nº Nodos hoja: ${AB.numHojas(a8)}")
  println(s"Suma valores de las hojas: ${AB.sumarHojas(a8)}")
  println(s"Recorrido anchura: ${AB.recorridoAnchura(a8)}")
  println(s"Recorrido inOrden: ${AB.recorridoInOrden(a8)}")
  println(s"Recorrido PreOrden: ${AB.recorridoPreOrden(a8)}")
  println(s"Recorrido PostOrden: ${AB.recorridoPostOrden(a8)}")
  println(s"Valores de las hojas: ${AB.listarValoresHojas(a8)}")

  // Nuevo árbol
  val acuadrado = AB.aplicarHojas(a8, x => x * x)
  println("Árbol con los valores de las hojas al cuadrado:")
  println(acuadrado)
}
