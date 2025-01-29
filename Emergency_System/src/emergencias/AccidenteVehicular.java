package emergencias;

public class AccidenteVehicular extends Emergencia {

  // Constructor
  public AccidenteVehicular(String ubicacion, Gravedad gravedad, int heridos) {
    super(ubicacion, gravedad, heridos);
  }

  // Método atender
  @Override
  public void atender() {
    System.out.println("|| Están en camino al " + ubicacion + " para atender un accidente vehicular con una gravedad " + gravedad);
  }
}
