package utils;

import java.util.ArrayList;
import java.util.List;

public class Estadisticas {
  private int totalEmergenciasAtendidas = 0;
  private List<Integer> tiemposRespuesta = new ArrayList<>(); // Guardaremos los tiempos de respuesta en minutos
  private int bomberosUsados = 0;
  private int ambulanciasUsadas = 0;
  private int policiasUsados = 0;

  public void registrarEmergenciaAtendida(int tiempoRespuesta, int bomberos, int ambulancias, int policias) {
    totalEmergenciasAtendidas++;
    tiemposRespuesta.add(tiempoRespuesta);
    bomberosUsados += bomberos;
    ambulanciasUsadas += ambulancias;
    policiasUsados += policias;
  }

  public double obtenerTiempoPromedioRespuesta() {
    return tiemposRespuesta.stream().mapToInt(Integer::intValue).average().orElse(0.0);
  }

  public void mostrarEstadisticas() {
    System.out.println("\n*Estadísticas*\r");
    System.out.println("===============================================");
    System.out.println("|| Total de emergencias atendidas: " + totalEmergenciasAtendidas);
    System.out.println("|| Tiempo promedio de respuesta:   " + obtenerTiempoPromedioRespuesta() + " minutos");
    System.out.println("|| Recursos utilizados: ");
    System.out.println("||   Bomberos:    " + bomberosUsados);
    System.out.println("||   Ambulancias: " + ambulanciasUsadas);
    System.out.println("||   Policías:    " + policiasUsados);
    System.out.println("===============================================");
  }
}
