package servicios;

public class Ambulancia  {

  public void tipoAmbulancia(int heridos) {
    System.out.println("|| Debido a los heridos de la emergencia, se requiere de: ");
    if (heridos > 5) {
      System.out.println("    Unas Ambulancias grandes");
      
    } else {
      System.out.println("    Unas Ambulancias pequeñas");
    }
  }

}
