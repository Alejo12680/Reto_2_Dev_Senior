
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import controladores.ControladorRecursos;
import emergencias.AccidenteVehicular;
import emergencias.Emergencia;
import emergencias.Gravedad;
import emergencias.Incendio;
import emergencias.Robo;
import patrones.AmbulanciaObserver;
import patrones.BomberosObserver;
import patrones.EmergenciaSujeto;
import patrones.PoliciaObserver;
import servicios.Ambulancia;
import servicios.Bomberos;
import servicios.Policia;
import patrones.EmergenciaFactory;
import utils.Estadisticas;

public class App {

  // Variables globales
  static int opcion = 0;
  static int heridos = -1;
  static int bomberosUsados = 0;
  static int ambulanciasUsadas = 0;
  static int policiasUsados = 0;
  static int tiempoRespuesta = 0;
  static String[] emergis = { "Incendio", "AccidenteVehicular", "Robo" };
  static String[] location = { "Norte", "Sur", "Occidente", "Oriente" };
  static String[] status = { "BAJA", "MEDIA", "ALTA" };
  private static List<Emergencia> listaEmergencias = new ArrayList<>();

  public static void main(String[] args) throws Exception {
    // Obtener la única instancia del ControladorRecursos
    ControladorRecursos controlador = ControladorRecursos.getInstancia();

    // Configuración del patrón Observer
    EmergenciaSujeto emergenciaSujeto = new EmergenciaSujeto();

    emergenciaSujeto.agregarServicio(new BomberosObserver());
    emergenciaSujeto.agregarServicio(new AmbulanciaObserver());
    emergenciaSujeto.agregarServicio(new PoliciaObserver());

    // Crear el menú
    Scanner scanner = new Scanner(System.in);
    boolean salir = false;

    while (!salir) {
      System.out.println("\n* Sistema de Gestión de Emergencias *\r");
      System.out.println("╔═..══════════════════════════════..═╗");
      System.out.println("| 1. Registrar una nueva emergencia  |");
      System.out.println("| 2. Ver el estado de los recursos   |");
      System.out.println("| 3. Atender una emergencia          |");
      System.out.println("| 4. Mostrar estadísticas del día    |");
      System.out.println("| 5. Salir                           |");
      System.out.println("╚═..══════════════════════════════..═╝");
      System.out.print("Seleccione una opción: ");

      int opcion = scanner.nextInt();
      scanner.nextLine(); // Consumir la nueva línea

      switch (opcion) {
        case 1:
          if (listaEmergencias.isEmpty()) {
            registrarEmergencia(scanner, emergenciaSujeto);
          } else {
            System.out.println("\n╔═..═══════════════════════════════════════════════════════..═╗");
            System.out.println(" Ya tienes una emergencias registrada en proceso de atender...");
            System.out.println("╚═..═══════════════════════════════════════════════════════..═╝\n");
          }
          break;
        case 2:
          mostrarEstadoRecursos(controlador);
          break;
        case 3:
          atenderEmergencia(controlador);
          break;
        case 4:
          mostrarEstadisticas();
          break;
        case 5:
          System.out.println("Saliendo del sistema...");
          salir = true;
          break;
        default:
          System.out.println("Opción no válida, intente de nuevo.");
      }
    }

    scanner.close();
  }

  // Método para registrar una nueva emergencia
  private static void registrarEmergencia(Scanner scanner, EmergenciaSujeto emergenciaSujeto) {

    String tipo = null;
    String ubicacion = null;
    Gravedad gravedad = null;

    System.out.print("\n*Ingrese el tipo de emergencia*\r\n");
    System.out.println("╔═..══════════════════..═╗");
    System.out.println("░ 01. Incendio           ░");
    System.out.println("░ 02. AccidenteVehicular ░");
    System.out.println("░ 03. Robo               ░");
    System.out.println("╚═..══════════════════..═╝");
    System.out.print("Digita una Opcion: ");
    opcion = scanner.nextInt();
    if (opcion < 0 || opcion >= 4) {
      System.out.println("Opción incorrecta saliendo del menú...\n");
      return;

    } else {
      tipo = emergis[opcion - 1];
      System.out.println("Tipo de emergencia: " + tipo);
    }

    System.out.print("\n*Ingrese la ubicación*\r\n");
    System.out.println("╔═..═════════..═╗");
    System.out.println("░ 01. Norte     ░");
    System.out.println("░ 02. Sur       ░");
    System.out.println("░ 03. Occidente ░");
    System.out.println("░ 04. Oriente   ░");
    System.out.println("╚═..═════════..═╝");
    System.out.print("Digita una Opcion: ");
    opcion = scanner.nextInt();
    if (opcion < 0 || opcion >= 5) {
      System.out.println("Opción incorrecta saliendo del menú...\n");
      return;

    } else {
      ubicacion = location[opcion - 1];
      System.out.println("Ubicación: " + ubicacion);
    }

    System.out.print("\n*Ingrese heridos*\r\n");
    System.out.println("╔═..══════════════════════════════════════════..═╗ ");
    System.out.println("░ Cuantos heridos hay en el " + tipo + " ?");
    System.out.println("╚═..══════════════════════════════════════════..═╝ ");
    if (scanner.hasNextInt()) {
      heridos = scanner.nextInt();
      scanner.nextLine();
      if (heridos < 0) {
        System.out.println("Cantidad de heridos no válida, saliendo del menú...\n");
        return;
      }
    } else {
      System.out.println("Entrada no válida, saliendo del menú...\n");
      return;
    }

    System.out.print("\n*Ingrese la gravedad*\r\n");
    System.out.println("╔═..═════..═╗");
    System.out.println("░ 01. Baja  ░");
    System.out.println("░ 02. Media ░");
    System.out.println("░ 03. Alta  ░");
    System.out.println("╚═..═════..═╝");
    opcion = scanner.nextInt();
    if (opcion < 0 || opcion >= 4) {
      System.out.println("Opción incorrecta saliendo del menú...\n");
      return;

    } else {
      String gravedadInput = status[opcion - 1];
      gravedad = Gravedad.valueOf(gravedadInput.toUpperCase());
      System.out.println("Gravedad: " + gravedad);
    }

    System.out.println("\n*Emergencia registrada exitosamente*");
    Emergencia nuevaEmergencia = EmergenciaFactory.crearEmergencia(tipo, ubicacion, gravedad, heridos);
    Policia policia = new Policia();
    Ambulancia ambulancia = new Ambulancia();
    Bomberos bomberos = new Bomberos();
    // Agregar la emergencia a la lista
    listaEmergencias.add(nuevaEmergencia);

    emergenciaSujeto.nuevaEmergencia(tipo, ubicacion, gravedad, heridos);
    nuevaEmergencia.atender();
    policia.tipoArma(gravedad);
    ambulancia.tipoAmbulancia(heridos);
    bomberos.tipoBomberos(heridos);

  }

  // Método para mostrar el estado de los recursos
  private static void mostrarEstadoRecursos(ControladorRecursos controlador) {
    controlador.mostrarEstado();
  }

  // Método para atender una emergencia
  private static void atenderEmergencia(ControladorRecursos controlador) {
    // Verificar si hay emergencias registradas
    if (listaEmergencias.isEmpty()) {
      System.out.println("\n╔═..═════════════════════════════════════════════..═╗");
      System.out.println(" No hay emergencias registradas para atender...");
      System.out.println("╚═..═════════════════════════════════════════════..═╝\n");
      return;
    }

    System.out.println("\n╔═..═══════════════════════..═╗");
    System.out.println("      Recursos asignados");
    System.out.println("╚═..═══════════════════════..═╝");

    // Obtener la última emergencia registrada
    Emergencia emergencia = listaEmergencias.get(listaEmergencias.size() - 1);
    int heridos = emergencia.getHeridos();
    int ambulanciasExtra = 0;

    // Asigna una ambulancia extra por cada 5 heridos
    if (heridos > 5) {
      ambulanciasExtra = heridos / 5;
    }

    // Lógica dinámica según el tipo de emergencia y gravedad
    if (emergencia instanceof Incendio) {
      switch (emergencia.gravedad) {
        case ALTA:
          bomberosUsados = 5;
          ambulanciasUsadas = 3 + ambulanciasExtra;
          policiasUsados = 4;
          tiempoRespuesta = 2;

          controlador.asignarBomberos(bomberosUsados);
          controlador.asignarAmbulancias(ambulanciasUsadas);
          controlador.asignarPolicias(policiasUsados);
          break;
        case MEDIA:
          bomberosUsados = 3;
          ambulanciasUsadas = 2 + ambulanciasExtra;
          policiasUsados = 2;
          tiempoRespuesta = 4;

          controlador.asignarBomberos(bomberosUsados);
          controlador.asignarAmbulancias(ambulanciasUsadas);
          controlador.asignarPolicias(policiasUsados);
          break;
        case BAJA:
          bomberosUsados = 2;
          ambulanciasUsadas = ambulanciasExtra;
          tiempoRespuesta = 6;

          controlador.asignarBomberos(bomberosUsados);
          controlador.asignarAmbulancias(ambulanciasUsadas);
          break;
      }
    } else if (emergencia instanceof AccidenteVehicular) {
      switch (emergencia.gravedad) {
        case ALTA:
          bomberosUsados = 2;
          ambulanciasUsadas = 3 + ambulanciasExtra;
          policiasUsados = 3;
          tiempoRespuesta = 2;

          controlador.asignarBomberos(bomberosUsados);
          controlador.asignarAmbulancias(ambulanciasUsadas);
          controlador.asignarPolicias(policiasUsados);
          break;
        case MEDIA:
          bomberosUsados = 1;
          ambulanciasUsadas = 2 + ambulanciasExtra;
          policiasUsados = 2;
          tiempoRespuesta = 4;

          controlador.asignarBomberos(bomberosUsados);
          controlador.asignarAmbulancias(ambulanciasUsadas);
          controlador.asignarPolicias(policiasUsados);
          break;
        case BAJA:
          bomberosUsados = 1;
          ambulanciasUsadas = 1 + ambulanciasExtra;
          policiasUsados = 1;
          tiempoRespuesta = 5;

          controlador.asignarBomberos(bomberosUsados);
          controlador.asignarAmbulancias(ambulanciasUsadas);
          controlador.asignarPolicias(policiasUsados);
          break;
      }
    } else if (emergencia instanceof Robo) {
      switch (emergencia.gravedad) {
        case ALTA:
          policiasUsados = 6;
          ambulanciasUsadas = 2 + ambulanciasExtra;
          tiempoRespuesta = 2;

          controlador.asignarAmbulancias(ambulanciasUsadas);
          controlador.asignarPolicias(policiasUsados);
          break;
        case MEDIA:
          policiasUsados = 4;
          ambulanciasUsadas = 1 + ambulanciasExtra;
          tiempoRespuesta = 3;

          controlador.asignarAmbulancias(ambulanciasUsadas);
          controlador.asignarPolicias(policiasUsados);
          break;
        case BAJA:
          policiasUsados = 2;
          ambulanciasUsadas = ambulanciasExtra;
          tiempoRespuesta = 5;

          controlador.asignarPolicias(policiasUsados);
          controlador.asignarAmbulancias(ambulanciasUsadas);
          break;
      }
    }

    System.out.println("\n|| Atendiendo una emergencia, Por favor espera... ||\n");

    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    executorService.schedule(() -> {

      // Liberamos los recursos
      controlador.liberarBomberos(bomberosUsados);
      controlador.liberarAmbulancias(ambulanciasUsadas);
      controlador.liberarPolicias(policiasUsados);

      // Simulamos que la emergencia ha sido resuelta
      System.out.println("╔═..═══════════════════════..═╗");
      System.out.println("     Emergencia Atendida.");
      System.out.println("╚═..═══════════════════════..═╝\n");

    }, tiempoRespuesta, TimeUnit.SECONDS);
    // Indica que no se programarán más tareas
    executorService.shutdown();

    try {
      // Esperar hasta que todas las tareas hayan terminado
      if (!executorService.awaitTermination(tiempoRespuesta + 1, TimeUnit.SECONDS)) {
        System.out.println("La tarea no se completó en el tiempo esperado.");
        // Forzar cierre si no termina a tiempo
        executorService.shutdownNow();
      }
    } catch (InterruptedException e) {
      // Forzar cierre si la espera es interrumpida
      executorService.shutdownNow();
      // Restaurar el estado de interrupción del hilo
      Thread.currentThread().interrupt();
    }

    // Registramos las estadísticas
    estadisticas.registrarEmergenciaAtendida(tiempoRespuesta, bomberosUsados, ambulanciasUsadas, policiasUsados);
  }

  // Método para mostrar estadísticas (simulado)
  private static Estadisticas estadisticas = new Estadisticas();

  private static void mostrarEstadisticas() {
    estadisticas.mostrarEstadisticas();
  }
}
