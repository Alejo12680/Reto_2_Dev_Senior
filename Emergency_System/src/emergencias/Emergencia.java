package emergencias;

public abstract class Emergencia {

  // Atributos
  protected String ubicacion;
  public Gravedad gravedad;
  protected int heridos;

  // Constructor
  public Emergencia(String ubicacion, Gravedad gravedad, int heridos) {
    this.ubicacion = ubicacion;
    this.gravedad = gravedad;
    this.heridos = heridos;
  }

  public String getTipo() {
    return ubicacion;
  }

  public int getHeridos() {
    return heridos;
  }

  // Método abstracto que cada tipo de emergencia implementará
  public abstract void atender();

  // Método que muestra la gravedad de la emergencia
  public void mostrarGravedad() {
    System.out.println("Gravedad de la emergencia: " + gravedad + " (Nivel " + gravedad.getNivel() + ")");
  }

}
