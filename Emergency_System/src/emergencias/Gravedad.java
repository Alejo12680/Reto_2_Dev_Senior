package emergencias;

public enum Gravedad {
  BAJA(1),
  MEDIA(2),
  ALTA(3);

  // Variable nivel de tipo int
  private final int nivel;

  // *Es el constructor del enum, es private implícitamente, instancia los valores de nivel*
  Gravedad(int nivel) {
    this.nivel = nivel;
  }

  // Método getter para acceder al valo del nivel
  public int getNivel() {
    return nivel;
  }

}
