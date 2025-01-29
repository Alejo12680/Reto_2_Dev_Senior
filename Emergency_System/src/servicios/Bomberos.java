package servicios;

public class Bomberos  {
  
  public void tipoBomberos(int incendio) {
    System.out.println("|| Debido al incendio de la emergencia, se requiere de: ");
    if (incendio > 5) {
      System.out.println("    Unos Bomberos especializados");
      System.out.println("========================================================================================");
    } else {
      System.out.println("    Unos Bomberos comunes");
      System.out.println("========================================================================================");
    }
  }

}
