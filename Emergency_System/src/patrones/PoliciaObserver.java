package patrones;

public class PoliciaObserver implements ServicioEmergenciaObserver {

  @Override
  public void notificar(String mensaje) {
    System.out.println("|| Policía recibió la notificación:     " + mensaje);
  }

}
