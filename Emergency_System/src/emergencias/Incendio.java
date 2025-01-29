package emergencias;

public class Incendio extends Emergencia {

  // El Constructor, llama al constructor de la clase base Emergencia
  public Incendio(String ubicacion, Gravedad gravedad, int heridos) {
    super(ubicacion, gravedad, heridos);
  }

  // Método atender
  @Override
  public void atender() {
    System.out.println("|| Están en camino al " + ubicacion + " para atender un incendio con una gravedad " + gravedad);
  }
}