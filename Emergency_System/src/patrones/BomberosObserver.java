package patrones;

public class BomberosObserver implements ServicioEmergenciaObserver {

  @Override
  public void notificar(String mensaje) {
    System.out.println("|| Bomberos recibieron la notificación: " + mensaje);
  }
}
