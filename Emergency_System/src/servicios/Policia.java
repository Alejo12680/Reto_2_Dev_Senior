package servicios;

import emergencias.Gravedad;

public class Policia  {

  static String[] weapons = { "Pistola", "Bolillo", "Taser" };

  // Implementa los métodos de la interfaz
  public void tipoArma(Gravedad gravedad) {
    System.out.println("|| Debido a la gravedad de la emergencia, la policia requiere de: ");
    switch (gravedad) {
      case ALTA:
        System.out.println("    Una Pistola");
        break;
      case MEDIA:
        System.out.println("    Un Taser");
        break;
      case BAJA:
        System.out.println("    Un Bolillo");
        break;
      default:

        break;

    }
  }

}
