package o2024.Casino;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Casino {
    protected List<Juego> juegos = new ArrayList<>(); // Lista de juegos

    public void agregarJuego(Juego juego) {
        juegos.add(juego);
    }

    public void iniciarCasino() {
        Scanner scanner = new Scanner(System.in);
        JugadorDAO jugadorDAO = new JugadorDAO();
    
        System.out.println("¿Ya tienes una cuenta? (1: Sí, 2: No)");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
    
        Jugador jugador = null;
    
        if (opcion == 1) {
            // Si el jugador ya tiene cuenta, pedir su ID
            System.out.println("Ingresa tu ID:");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            jugador = jugadorDAO.obtenerJugadorPorId(id);
    
            if (jugador == null) {
                System.out.println("No se encontró un jugador con ese ID.");
                return; // Termina si no se encuentra el jugador
            } else {
                System.out.println("¡Bienvenido de nuevo, " + jugador.getNombre() + " " + jugador.getApellidos() + "!");
                System.out.println("Tu saldo actual es: $" + jugador.getSaldo());
                System.out.println("Tu nivel actual es: " + jugador.getNivel());
            }
        } else if (opcion == 2) {
            // Si es un nuevo jugador, registrarlo
            System.out.println("¿Cuál es tu nombre?");
            String nombreJugador = scanner.nextLine();
            System.out.println("¿Cuáles son tus apellidos?");
            String apellidosJugador = scanner.nextLine();
            System.out.println("¿Cuál es tu fecha de nacimiento? (día, mes, año)");
            int dia = scanner.nextInt();
            int mes = scanner.nextInt();
            int anio = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            System.out.println("¿Cuál es tu estado civil? (SOLTERO, CASADO, DIVORCIADO, VIUDO)");
            String estadoCivilStr = scanner.nextLine().toUpperCase();
            EstadoCivil estadoCivilJugador = EstadoCivil.valueOf(estadoCivilStr);

            System.out.println("¿Cuál es tu sexo? (MASCULINO, FEMENINO)");
            String sexoStr = scanner.nextLine().toUpperCase();
            Sexo sexoJugador = Sexo.valueOf(sexoStr);

            System.out.println("¿Cuál es tu saldo inicial?");
            double saldoInicial = scanner.nextDouble();
            scanner.nextLine(); // Limpiar el buffer
    
            Fecha fechaNacimiento = new Fecha(dia, mes, anio);
            jugador = new Jugador(nombreJugador, apellidosJugador, fechaNacimiento, estadoCivilJugador, sexoJugador, NivelJugador.PRINCIPIANTE, saldoInicial, 0);
            jugadorDAO.guardarJugador(jugador);
            System.out.println("Jugador registrado exitosamente.");
        } else {
            System.out.println("Opción inválida.");
            return;
        }
    
        // Continuar con el flujo del casino
        boolean seguirJugando = true;
    
        while (seguirJugando && jugador.getSaldo() > 0) {
            System.out.println("\nElige un juego para jugar:");
            for (int i = 0; i < juegos.size(); i++) {
                System.out.println((i + 1) + ". " + juegos.get(i).getNombre());
            }
    
            int opcionJuego = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            Juego juegoSeleccionado = juegos.get(opcionJuego - 1);
    
            if (juegoSeleccionado instanceof Ruleta) {
                ((Ruleta) juegoSeleccionado).jugarConJugador(jugador, jugadorDAO);
            } else if (juegoSeleccionado instanceof Tragamonedas) {
                System.out.println("¿Cuánto deseas apostar?");
                double apuesta = scanner.nextDouble();
    
                if (apuesta > jugador.getSaldo()) {
                    System.out.println("No tienes suficiente saldo para esta apuesta.");
                    continue;
                }

                // Registrar apuesta y actualizar la base de datos
                jugador.apostar(apuesta, jugadorDAO);
                jugador.incrementarApuestas(); // Incrementar número de apuestas
                jugadorDAO.actualizarNumeroApuestas(jugador.getNombre(), jugador.getNumeroApuestas());

                // Actualizar nivel del jugador según el número de apuestas
                Casino.actualizarNivelJugador(jugador, jugadorDAO);

                ResultadoJuego resultado = ((Tragamonedas) juegoSeleccionado).jugarConJugador(jugador, jugadorDAO, apuesta);
    
                if (resultado == ResultadoJuego.GANAR) {
                    double recompensa = juegoSeleccionado.calcularRecompensa(apuesta);
                    jugador.recibirRecompensa(recompensa, jugadorDAO);
                    System.out.println("\u00a1Ganaste, recibiste una recompensa de $" + recompensa + "!");
                } else {
                    System.out.println("Perdiste. Mejor suerte la próxima vez.");
                }
            }
    
            System.out.println("¿Deseas seguir jugando? (1: Sí, 2: No)");
            int continuar = scanner.nextInt();
            seguirJugando = (continuar == 1);
        }
    
        System.out.println("Gracias por jugar. Tu saldo final es: $" + jugador.getSaldo());
        scanner.close();
    }

    // Método para actualizar el nivel del jugador según el número de apuestas
    public static void actualizarNivelJugador(Jugador jugador, JugadorDAO jugadorDAO) {
        int numeroApuestas = jugador.getNumeroApuestas();

        if (numeroApuestas > 100) {
            jugador.setNivel(NivelJugador.AVANZADO);
        } else if (numeroApuestas > 50) {
            jugador.setNivel(NivelJugador.AVANZADO);
        } else if (numeroApuestas > 10) {
            jugador.setNivel(NivelJugador.INTERMEDIO);
        } else {
            jugador.setNivel(NivelJugador.PRINCIPIANTE);
        }

        // Actualizar el nivel del jugador en la base de datos
        jugadorDAO.actualizarNivel(jugador.getNombre(), jugador.getNivel());
    }
}
