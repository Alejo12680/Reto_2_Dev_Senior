package controladores;

public class ControladorRecursos {
  private static ControladorRecursos instanciaUnica;
  private int bomberosDisponibles    = 10;
  private int ambulanciasDisponibles = 5;
  private int policiasDisponibles    = 15;

  // Constructor privado para evitar que otros creen instancias
  private ControladorRecursos() {
  }

  // Método estático para obtener la única instancia
  public static ControladorRecursos getInstancia() {
    if (instanciaUnica == null) {
      instanciaUnica = new ControladorRecursos();
    }
    return instanciaUnica;
  }

  // Métodos para asignar recursos
  public void asignarBomberos(int cantidad) {
    if (bomberosDisponibles >= cantidad) {
      bomberosDisponibles -= cantidad;
      System.out.println(cantidad + " bomberos asignados => Bomberos disponibles: " + bomberosDisponibles);
    } else {
      System.out.println("No hay suficientes bomberos disponibles.");
    }
  }

  public void asignarAmbulancias(int cantidad) {
    if (ambulanciasDisponibles >= cantidad) {
      ambulanciasDisponibles -= cantidad;
      System.out.println(cantidad + " ambulancias asignadas => Ambulancias disponibles: " + ambulanciasDisponibles);
    } else {
      System.out.println("No hay suficientes ambulancias disponibles.");
    }
  }

  public void asignarPolicias(int cantidad) {
    if (policiasDisponibles >= cantidad) {
      policiasDisponibles -= cantidad;
      System.out.println(cantidad + " policías asignados => Policías disponibles: " + policiasDisponibles);
    } else {
      System.out.println("No hay suficientes policías disponibles.");
    }
  }

  // Método para mostrar el estado de los recursos
  public void mostrarEstado() {
    System.out.println("\n*Estado de los recursos*\r");
    System.out.println("==============================");
    System.out.println("|| Bomberos disponibles:    " + bomberosDisponibles);
    System.out.println("|| Ambulancias disponibles: " + ambulanciasDisponibles);
    System.out.println("|| Policías disponibles:    " + policiasDisponibles);
    System.out.println("==============================");
  }

  // Método para liberar recursos (después de atender una emergencia)
  public void liberarBomberos(int cantidad) {
    bomberosDisponibles += cantidad;
    System.out.println(cantidad + " bomberos liberados. ");
  }

  public void liberarAmbulancias(int cantidad) {
    ambulanciasDisponibles += cantidad;
    System.out.println(cantidad + " ambulancias liberadas. ");
  }

  public void liberarPolicias(int cantidad) {
    policiasDisponibles += cantidad;
    System.out.println(cantidad + " policías liberados. ");
  }
}
