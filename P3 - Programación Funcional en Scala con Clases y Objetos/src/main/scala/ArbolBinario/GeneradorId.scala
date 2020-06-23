package ArbolBinario

/**
 * Objeto singleton para generar Ids únicos de forma ordendada
 */
private object GeneradorId {

  /* Último id generado, inicialmente 0 */
  var ultimo_id = 0

  /**
   * Método para generar un nuevo id, incrementando el último generado
   *
   * @return
   */
  def apply(): Int = {
    ultimo_id += 1
    ultimo_id
  }
}
