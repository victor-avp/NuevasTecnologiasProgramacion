sealed trait ArbolBinario[+A]

/*
 * Objeto para definir árbol vacío
 */
case object Nil extends ArbolBinario[Nothing]


def appy[A] (elementos: A*) : ArbolBinario[A] = ???

def recorridoAnchura = ???

def recorridoProfundidad = ???

def tamArbol = ???

def sumarHojas = ???

def aplicarFuncionHojas = ???

def composicionArboles = ???

// Más métodos que se consideren necesarios/oportunos
