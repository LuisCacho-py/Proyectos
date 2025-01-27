package o2024.Casino;

import java.util.Scanner;

public class Ruleta extends Juego {
    private int numeroCasillas;

    public Ruleta(String nombre, double costoEntrada, double probabilidadGanar, double premioBase, int numeroCasillas) {
        super(nombre, costoEntrada, probabilidadGanar, premioBase);
        this.numeroCasillas = numeroCasillas;
    }

    @Override
    public void iniciarJuego() {
        System.out.println("Iniciando el juego de Ruleta con " + numeroCasillas + " casillas.");
    }

    public void jugarConJugador(Jugador jugador, JugadorDAO jugadorDAO) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tu saldo actual es: $" + jugador.getSaldo());
        System.out.println("Elige el tipo de apuesta:");
        System.out.println("1. Número específico (Pago: 36x)");
        System.out.println("2. Color (rojo/negro) (Pago: 2x)");
        System.out.println("3. Par/Impar (Pago: 2x)");
        int tipoApuesta = scanner.nextInt();

        System.out.println("¿Cuánto deseas apostar?");
        double apuesta = scanner.nextDouble();

        if (apuesta > jugador.getSaldo()) {
            System.out.println("No tienes suficiente saldo para esta apuesta.");
            return;
        }

        // Descontar la apuesta y actualizar en la base de datos
        jugador.apostar(apuesta, jugadorDAO);

        boolean ganador = false;
        double pago = 0;

        switch (tipoApuesta) {
            case 1: // Apuesta a un número específico
                System.out.println("Elige un número (1 a " + numeroCasillas + "):");
                int numeroElegido = scanner.nextInt();
                int numeroGanador = (int) (Math.random() * numeroCasillas) + 1;
                System.out.println("El número ganador es: " + numeroGanador);
                ganador = (numeroElegido == numeroGanador);
                if (ganador) {
                    pago = apuesta * 36; // Pago 36x en apuestas a número
                }
                break;

            case 2: // Apuesta a color
                System.out.println("Elige un color (rojo/negro):");
                String color = scanner.next();
                int numeroColor = (int) (Math.random() * numeroCasillas) + 1;
                System.out.println("El número ganador es: " + numeroColor);
                ganador = (numeroColor % 2 == 0 && color.equalsIgnoreCase("rojo")) ||
                          (numeroColor % 2 != 0 && color.equalsIgnoreCase("negro"));
                if (ganador) {
                    pago = apuesta * 2; // Pago 2x en apuestas a color
                }
                break;

            case 3: // Apuesta a par/impar
                System.out.println("Elige par o impar:");
                String paridad = scanner.next();
                int numeroParidad = (int) (Math.random() * numeroCasillas) + 1;
                System.out.println("El número ganador es: " + numeroParidad);
                ganador = (numeroParidad % 2 == 0 && paridad.equalsIgnoreCase("par")) ||
                          (numeroParidad % 2 != 0 && paridad.equalsIgnoreCase("impar"));
                if (ganador) {
                    pago = apuesta * 2; // Pago 2x en apuestas a par/impar
                }
                break;

            default:
                System.out.println("Opción inválida. Intenta nuevamente.");
                return;
        }

        if (ganador) {
            // Recompensa y actualización en la base de datos
            jugador.recibirRecompensa(pago, jugadorDAO);
            System.out.println("¡Felicidades! Ganaste $" + pago);
        } else {
            System.out.println("Lo siento, has perdido.");
        }

        // Mostrar el saldo actualizado
        System.out.println("Tu saldo actual es: $" + jugador.getSaldo());
    }
}


