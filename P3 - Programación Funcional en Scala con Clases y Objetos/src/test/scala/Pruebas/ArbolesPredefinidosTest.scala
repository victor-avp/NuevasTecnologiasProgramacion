package Pruebas

import ArbolBinario.{ArbolVacio, ArbolBinario => AB}
import org.scalatest.FunSuite

import scala.util.Random.nextInt

class ArbolesPredefinidosTest extends FunSuite {

  def id: AB => Int = x => x.id.getOrElse(-1)

  // Arbol 1 -> Un nodo con un hijo izquierdo
  val h1 = AB(nextInt)
  val arbol1 = AB(h1, ArbolVacio)

  // Árbol 2 -> Un nodo con un hijo derecho
  val h2 = AB(nextInt)
  val arbol2 = AB(ArbolVacio, h2)

  // Árbol 3 -> Árbol de profundidad 4
  val h3 = List.fill(3)(AB(nextInt))
  val i31 = AB(h3.head, h3(1))
  val i32 = AB(i31, ArbolVacio)
  val arbol3 = AB(h3(2), i32)
  val n3 = List(arbol3, h3(2), i32, i31, h3(0), h3(1)) // nodos en anchura

  // Árbol 4 -> Árbol de profundidad 8
  val h4 = List.fill(9)(AB(nextInt))
  val i41 = AB(h4(0), h4(1))
  val i42 = AB(h4(2), i41)
  val i43 = AB(i42, h4(3))
  val i44 = List(AB(h4(4), i43), AB(h4(5), ArbolVacio))
  val i45 = List(AB(ArbolVacio, i44(0)), AB(h4(6), h4(7)), AB(h4(8), i44(1)))
  val i46 = List(AB(i45(0), ArbolVacio), AB(i45(1), i45(2)))
  val arbol4 = AB(i46(0), i46(1))
  val n4 = List(arbol4) ::: i46 ::: i45 ::: List(i44(0), h4(6), h4(7), h4(8),
    i44(1), h4(4), i43, h4(5), i42, h4(3), h4(2), i41, h4(0), h4(1)) // nodos en anchura

  println(s"Estructura árbol1: \n${arbol1}")
  println(s"Estructura árbol2: \n${arbol2}")
  println(s"Estructura árbol3: \n${arbol3}")
  println(s"Estructura árbol4: \n${arbol4}")

  test("Recorrido en anchura") {
    assert(AB.recorridoAnchura(arbol1) == List(arbol1, h1).map(id))
    assert(AB.recorridoAnchura(arbol2) == List(arbol2, h2).map(id))
    assert(AB.recorridoAnchura(arbol3) == n3.map(id))
    assert(AB.recorridoAnchura(arbol4) == n4.map(id))
  }

  test("Recorrido en inOrden") {
    assert(AB.recorridoInOrden(arbol1) == List(h1, arbol1).map(id))
    assert(AB.recorridoInOrden(arbol2) == List(arbol2, h2).map(id))
    assert(AB.recorridoInOrden(arbol3) == List(n3(1), n3(0), n3(4), n3(3), n3(5), n3(2)).map(id))
    assert(AB.recorridoInOrden(arbol4) == List(n4(3), n4(11), n4(6), n4(16), n4(14), n4(18), n4(17),
      n4(19), n4(12), n4(15), n4(1), n4(0), n4(7), n4(4), n4(8), n4(2), n4(9), n4(5), n4(13), n4(10)).map(id))
  }

  test("Recorrido en preOrden") {
    assert(AB.recorridoPreOrden(arbol1) == List(arbol1, h1).map(id))
    assert(AB.recorridoPreOrden(arbol2) == List(arbol2, h2).map(id))
    assert(AB.recorridoPreOrden(arbol3) == List(n3(0), n3(1), n3(2), n3(3), n3(4), n3(5)).map(id))
    assert(AB.recorridoPreOrden(arbol4) == List(n4(0), n4(1), n4(3), n4(6), n4(11), n4(12), n4(14),
      n4(16), n4(17), n4(18), n4(19), n4(15), n4(2), n4(4), n4(7), n4(8), n4(5), n4(9), n4(10), n4(13)).map(id))
  }

  test("Recorrido en postOrden") {
    assert(AB.recorridoPostOrden(arbol1) == List(h1, arbol1).map(id))
    assert(AB.recorridoPostOrden(arbol2) == List(h2, arbol2).map(id))
    assert(AB.recorridoPostOrden(arbol3) == List(n3(1), n3(4), n3(5), n3(3), n3(2), n3(0)).map(id))
    assert(AB.recorridoPostOrden(arbol4) == List(n4(11), n4(16), n4(18), n4(19), n4(17), n4(14), n4(15),
      n4(12), n4(6), n4(3), n4(1), n4(7), n4(8), n4(4), n4(9), n4(13), n4(10), n4(5), n4(2), n4(0)).map(id))
  }

  test("Número hojas y nodos internos") {
    assert(AB.numHojas(arbol1) == 1)
    assert(AB.numNodosInternos(arbol1) == 1)
    assert(AB.numNodosTotal(arbol1) == 2)

    assert(AB.numHojas(arbol2) == 1)
    assert(AB.numNodosInternos(arbol2) == 1)
    assert(AB.numNodosTotal(arbol2) == 2)

    assert(AB.numHojas(arbol3) == 3)
    assert(AB.numNodosInternos(arbol3) == 3)
    assert(AB.numNodosTotal(arbol3) == 6)

    assert(AB.numHojas(arbol4) == 9)
    assert(AB.numNodosInternos(arbol4) == 11)
    assert(AB.numNodosTotal(arbol4) == 20)
  }
}
