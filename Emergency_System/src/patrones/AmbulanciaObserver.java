package patrones;

public class AmbulanciaObserver implements ServicioEmergenciaObserver {

  @Override
  public void notificar(String mensaje) {
    System.out.println("|| Ambulancia recibió la notificación:  " + mensaje);
  }
}
