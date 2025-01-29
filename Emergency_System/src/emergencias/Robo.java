package emergencias;

public class Robo extends Emergencia {

  // Constructor
  public Robo(String ubicacion, Gravedad gravedad, int heridos) {
    super(ubicacion, gravedad, heridos);
  }

  // Método atender
  @Override
  public void atender() {
    System.out.println("|| Están en camino al " + ubicacion + " para atender un robo con una gravedad " + gravedad);
  }

}
