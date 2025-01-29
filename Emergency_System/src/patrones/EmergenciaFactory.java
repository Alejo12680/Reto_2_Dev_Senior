package patrones;

import emergencias.Emergencia;
import emergencias.Gravedad;
import emergencias.Incendio;
import emergencias.AccidenteVehicular;
import emergencias.Robo;

public class EmergenciaFactory {
  public static Emergencia crearEmergencia(String tipo, String ubicacion, Gravedad gravedad, int heridos) {

    if (heridos < 0) {
      throw new IllegalArgumentException("El número de heridos no puede ser negativo.");
    }

    switch (tipo) {
      case "Incendio":
        return new Incendio(ubicacion, gravedad, heridos);
      case "AccidenteVehicular":
        return new AccidenteVehicular(ubicacion, gravedad, heridos);
      case "Robo":
        return new Robo(ubicacion, gravedad, heridos);
      default:
        throw new IllegalArgumentException("Tipo de emergencia no reconocido");
    }
  }

}
