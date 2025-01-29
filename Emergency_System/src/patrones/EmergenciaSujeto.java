package patrones;

import java.util.ArrayList;
import java.util.List;

import emergencias.Gravedad;

public class EmergenciaSujeto {
  private List<ServicioEmergenciaObserver> servicios = new ArrayList<>();

  public void agregarServicio(ServicioEmergenciaObserver servicio) {
    servicios.add(servicio);
  }

  public void nuevaEmergencia(String tipoEmergencia, String ubicacion, Gravedad gravedad, int heridos) {
    System.out.println("========================================================================================");
    System.out.println("Nueva emergencia registrada es un " + tipoEmergencia + " en el " + ubicacion + " con una gravedad " + gravedad + " y " + heridos + " heridos");
    notificarServicios(tipoEmergencia);
  }

  private void notificarServicios(String mensaje) {
    for (ServicioEmergenciaObserver servicio : servicios) {
      servicio.notificar(mensaje);
    }
  }
}
