sealed trait ArbolBinario {
  def id: Option[Int] = this match {
    case n: Nodo => Some(n.identificador)
    case Nil => None
  }

  def izquierda: Option[ArbolBinario] = this match {
    case n: NodoInterno => Some(n.izq)
    case _ => None
  }

  def derecha: Option[ArbolBinario] = this match {
    case n: NodoInterno => Some(n.der)
    case _ => None
  }

  def valor: Option[Int] = this match {
    case h: Hoja => Some(h.v)
    case _ => None
  }

  override def toString() : String = {
    def imprimirArbol(arbol : ArbolBinario, base : String) : String = {
      arbol match {
        case h: Hoja => base + s"-- ${h.identificador}(${h.v})\n"
        case n: NodoInterno =>
          base + s"-- ${n.identificador}\n${imprimirArbol(n.der, base + "   |")}${imprimirArbol(n.izq, base + "   |")}"
        case Nil => base + "-- x\n"
      }
    }

    imprimirArbol(this, "")
  }
}

object ArbolBinario {

  def altura(arbol: ArbolBinario): Int = {

    arbol match {
      case h: Hoja => 1
      case n: NodoInterno => 1 + Math.max(altura(n.izq), altura(n.der))
      case Nil => 0
    }
  }

  def recorridoNivel(arbol: ArbolBinario, nivel: Int): List[Int] = {
    arbol match {
      case h: Hoja => if (nivel == 1) List(h.identificador) else List()
      case n: NodoInterno =>
        if (nivel == 1) List(n.identificador)
        else recorridoNivel(n.izq, nivel - 1) ::: recorridoNivel(n.der, nivel - 1)
      case Nil => List()
    }
  }

  def recorridoAnchura(arbol: ArbolBinario): List[Int] = {
    (1 to altura(arbol) toList).flatMap(nivel => recorridoNivel(arbol, nivel))
  }

  def recorridoProfundidad(arbol: ArbolBinario): List[Int] = {
    recorridoInOrden(arbol)
  }

  def recorridoInOrden(arbol: ArbolBinario): List[Int] = {
    arbol match {
      case h: Hoja => List(h.identificador)
      case n: NodoInterno =>
        recorridoInOrden(n.izq) ::: List(n.identificador) ::: recorridoInOrden(n.der)
      case Nil => List()
    }
  }

  def recorridoPreOrden(arbol: ArbolBinario): List[Int] = {
    arbol match {
      case h: Hoja => List(h.identificador)
      case n: NodoInterno =>
        List(n.identificador) ::: recorridoPreOrden(n.izq) ::: recorridoPreOrden(n.der)
      case Nil => List()
    }
  }

  def recorridoPostOrden(arbol: ArbolBinario): List[Int] = {
    arbol match {
      case h: Hoja => List(h.identificador)
      case n: NodoInterno =>
        recorridoPostOrden(n.izq) ::: recorridoPostOrden(n.der) ::: List(n.identificador)
      case Nil => List()
    }
  }

  def numNodosTotal(arbol: ArbolBinario): Int = {
    arbol match {
      case h: Hoja => 1
      case n: NodoInterno => numNodosTotal(n.izq) + numNodosTotal(n.der) + 1
      case Nil => 0
    }
  }

  def numNodosInternos(arbol: ArbolBinario): Int = {
    arbol match {
      case h: Hoja => 0
      case n: NodoInterno => numNodosInternos(n.izq) + numNodosInternos(n.der) + 1
      case Nil => 0
    }
  }

  def numHojas(arbol: ArbolBinario): Int = {
    arbol match {
      case h: Hoja => 1
      case n: NodoInterno => numHojas(n.izq) + numHojas(n.der)
      case Nil => 0
    }
  }

  def aplicarHojas(arbol: ArbolBinario, funcion: Int => Int): ArbolBinario = {
    arbol match {
      case h: Hoja => h.copy(v = funcion(h.v))
      case n: NodoInterno =>
        NodoInterno(aplicarHojas(n.izq, funcion), aplicarHojas(n.der, funcion))
      case Nil => Nil
    }
  }

  def sumarHojas(arbol: ArbolBinario): Int = {
    arbol match {
      case h: Hoja => h.v
      case n: NodoInterno => sumarHojas(n.izq) + sumarHojas(n.der)
      case Nil => 0
    }
  }

  def componerArboles(arbolIzq: ArbolBinario, arbolDer: ArbolBinario): ArbolBinario = {
    NodoInterno(arbolIzq, arbolDer)
  }
}

case object Nil extends ArbolBinario

sealed trait Nodo extends ArbolBinario {
  val identificador: Int = GeneradorId()
}

final case class NodoInterno(izq: ArbolBinario, der: ArbolBinario) extends Nodo

final case class Hoja(v: Int) extends Nodo

object GeneradorId {
  var ultimo_id = 0

  def apply(): Int = {
    ultimo_id += 1
    ultimo_id
  }
}

////////////////////////////////////////////////////////////////////////////////

object pruebas extends App {

  val a1 = NodoInterno(Hoja(5), Hoja(3))
  val a2 = NodoInterno(Hoja(4), Hoja(17))
  val a3 = NodoInterno(a1, a2)
  val a31 = NodoInterno(a3, Hoja(16))
  val a4 = NodoInterno(a31, Nil)
  println(ArbolBinario.sumarHojas(a4))
  val a5 = ArbolBinario.aplicarHojas(a4, i => 2 * i)
  println(a5.izquierda.getOrElse(Nil).izquierda.getOrElse(Nil).izquierda)
  println(ArbolBinario.numHojas(a5))
  println(ArbolBinario.numNodosInternos(a5))
  println(ArbolBinario.numNodosTotal(a5))

  println(ArbolBinario.recorridoProfundidad(a4))
  println(ArbolBinario.recorridoInOrden(a4))
  println(ArbolBinario.recorridoPostOrden(a4))
  println(ArbolBinario.recorridoPreOrden(a4))
  println(ArbolBinario.recorridoAnchura(a4))
  println(a4)
}