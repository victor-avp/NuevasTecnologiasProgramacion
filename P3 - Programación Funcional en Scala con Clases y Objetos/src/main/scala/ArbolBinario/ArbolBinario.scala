package ArbolBinario

/**
 * Interfaz para árbol binario
 */
sealed trait ArbolBinario {
  /**
   * Identificador de un árbol cualquiera. Si es nulo, devuelve None
   *
   * @return
   */
  def id: Option[Int] = this match {
    case n: Nodo => Some(n.identificador)
    case ArbolVacio => None
  }

  /**
   * Hijo izquierdo de un árbol cualquiera. Si es una hoja o nulo, devuelve None
   *
   * @return
   */
  def izquierda: Option[ArbolBinario] = this match {
    case n: NodoInterno => Some(n.izq)
    case _ => None
  }

  /**
   * Hijo derecho de un árbol cualquiera. Si es una hoja o nulo, devuelve None
   *
   * @return
   */
  def derecha: Option[ArbolBinario] = this match {
    case n: NodoInterno => Some(n.der)
    case _ => None
  }

  /**
   * Valor almacenado por un árbol cualqiera. Si no es una hoja, devuelve None
   *
   * @return
   */
  def valor: Option[Int] = this match {
    case h: Hoja => Some(h.v)
    case _ => None
  }

  /**
   * Método toString que muestra los niveles por columnas, colocando los hijos
   * derechos encima de los izquierdos.
   * Para cada nodo se muestra su id (una x si es nulo), es un nodo hoja
   * también se muestra entre paréntesis su valor
   *
   * @return
   */
  override def toString(): String = {
    def imprimirArbol(arbol: ArbolBinario, base: String): String = {
      arbol match {
        case h: Hoja => base + s"-- ${h.identificador}(${h.v})\n"
        case n: NodoInterno =>
          base + s"-- ${n.identificador}\n${imprimirArbol(n.der, base + "   |")}" +
            s"${imprimirArbol(n.izq, base + "   |")}"
        case ArbolVacio => base + "-- x\n"
      }
    }

    imprimirArbol(this, "")
  }
}

/**
 * Companion object de árbol binario
 * Aquí están los métodos a implementar en la práctica
 */
object ArbolBinario {

  /**
   * Método para permitir crear un árbol binario como una hoja a partir de un valor
   *
   * @param valor
   * @return
   */
  def apply(valor: Int): ArbolBinario = {
    Hoja(valor)
  }

  /**
   * Método para componer dos árboles: permite crear un árbol binario como
   * un nodo interno  apartir de dos árboles hijos. Si ambos hijos son nulos,
   * se devuelve el árbol nulo
   *
   * @param izq
   * @param der
   * @return
   */
  def apply(izq: ArbolBinario, der: ArbolBinario): ArbolBinario = {
    (izq, der) match {
      case (ArbolVacio, ArbolVacio) => ArbolVacio
      case _ => NodoInterno(izq, der)
    }
  }

  /**
   * Método para determinar la profundidad máxima de un árbol
   *
   * @param arbol
   * @return
   */
  def profundidad(arbol: ArbolBinario): Int = {

    arbol match {
      case h: Hoja => 1
      case n: NodoInterno => 1 + Math.max(profundidad(n.izq), profundidad(n.der))
      case ArbolVacio => 0
    }
  }

  /**
   * Método para obtener el recorrido en anchura de un nivel concreto de un árbol
   *
   * @param arbol
   * @param nivel
   * @return Lista de enteros con los ids del recorrido
   */
  def recorridoNivel(arbol: ArbolBinario, nivel: Int): List[Int] = {
    arbol match {
      case h: Hoja => if (nivel == 1) List(h.identificador) else List()
      case n: NodoInterno =>
        if (nivel == 1) List(n.identificador)
        else recorridoNivel(n.izq, nivel - 1) ::: recorridoNivel(n.der, nivel - 1)
      case ArbolVacio => List()
    }
  }

  /**
   * Método para obtener el recorrido en anchura de un árbol, en todos sus niveles
   *
   * @param arbol
   * @return Lista de enteros con los ids del recorrido
   */
  def recorridoAnchura(arbol: ArbolBinario): List[Int] = {
    (1 to profundidad(arbol) toList).flatMap(nivel => recorridoNivel(arbol, nivel))
  }

  /**
   * Método para realizar un recorrido en profundidad InOrden de un árbol
   *
   * @param arbol
   * @return Lista de enteros con los ids del recorrido
   */
  def recorridoInOrden(arbol: ArbolBinario): List[Int] = {
    arbol match {
      case h: Hoja => List(h.identificador)
      case n: NodoInterno =>
        recorridoInOrden(n.izq) ::: List(n.identificador) ::: recorridoInOrden(n.der)
      case ArbolVacio => List()
    }
  }

  /**
   * Método para realizar un recorrido en profundidad PreOrden de un árbol
   *
   * @param arbol
   * @return Lista de enteros con los ids del recorrido
   */
  def recorridoPreOrden(arbol: ArbolBinario): List[Int] = {
    arbol match {
      case h: Hoja => List(h.identificador)
      case n: NodoInterno =>
        List(n.identificador) ::: recorridoPreOrden(n.izq) ::: recorridoPreOrden(n.der)
      case ArbolVacio => List()
    }
  }

  /**
   * Método para realizar un recorrido en profundidad PostOrden de un árbol
   *
   * @param arbol
   * @return Lista de enteros con los ids del recorrido
   */
  def recorridoPostOrden(arbol: ArbolBinario): List[Int] = {
    arbol match {
      case h: Hoja => List(h.identificador)
      case n: NodoInterno =>
        recorridoPostOrden(n.izq) ::: recorridoPostOrden(n.der) ::: List(n.identificador)
      case ArbolVacio => List()
    }
  }

  /**
   * Método para obtener el número de nodos (hojas e internos) de un árbol
   *
   * @param arbol
   * @return
   */
  def numNodosTotal(arbol: ArbolBinario): Int = {
    arbol match {
      case h: Hoja => 1
      case n: NodoInterno => numNodosTotal(n.izq) + numNodosTotal(n.der) + 1
      case ArbolVacio => 0
    }
  }

  /**
   * Método para obtener el número de nodos internos de un árbol
   *
   * @param arbol
   * @return
   */
  def numNodosInternos(arbol: ArbolBinario): Int = {
    arbol match {
      case h: Hoja => 0
      case n: NodoInterno => numNodosInternos(n.izq) + numNodosInternos(n.der) + 1
      case ArbolVacio => 0
    }
  }

  /**
   * Método para obtener el número de nodos hoja de un árbol
   *
   * @param arbol
   * @return
   */
  def numHojas(arbol: ArbolBinario): Int = {
    listarValoresHojas(arbol).length
  }

  /**
   * Método para aplicar una función a todos las hojas de un árbol
   *
   * @param arbol
   * @param funcion
   * @return Nuevo árbol resultado de aplicar la función a las hojas del árbol original
   */
  def aplicarHojas(arbol: ArbolBinario, funcion: Int => Int): ArbolBinario = {
    arbol match {
      case h: Hoja => h.copy(v = funcion(h.v))
      case n: NodoInterno =>
        NodoInterno(aplicarHojas(n.izq, funcion), aplicarHojas(n.der, funcion))
      case ArbolVacio => ArbolVacio
    }
  }

  /**
   * Método para crear una lista con los valores de las hojas de un árbol
   *
   * @param arbol
   * @return
   */
  def listarValoresHojas(arbol: ArbolBinario): List[Int] = {
    arbol match {
      case h: Hoja => List(h.v)
      case n: NodoInterno => listarValoresHojas(n.izq) ::: listarValoresHojas(n.der)
      case ArbolVacio => List()
    }
  }

  /**
   * Método para sumar los valores de todas las hojas de un árbol
   *
   * @param arbol
   * @return
   */
  def sumarHojas(arbol: ArbolBinario): Int = {
    listarValoresHojas(arbol).sum
  }
}

/**
 * Objeto para definir árbol vacío
 */
case object ArbolVacio extends ArbolBinario

/**
 * Interfaz para un nodo no vacío, tiene un id asignado
 */
sealed trait Nodo extends ArbolBinario {
  val identificador: Int = GeneradorId()
}

/**
 * Clase para definir un nodo interno que tiene dos árboles hijos
 *
 * @param izq Hijo izquierdo
 * @param der Hijo derecho
 */
final case class NodoInterno(izq: ArbolBinario, der: ArbolBinario) extends Nodo

/**
 * Clase para definir un nodo hoja
 *
 * @param v Valor entero almacenado en la hoja
 */
final case class Hoja(v: Int) extends Nodo