package o2024.Casino;




import java.util.Random;

public class Tragamonedas extends Juego {
    private int numeroRodillos;
    private String[] simbolos = {"1", "2", "3", "4"};

    public Tragamonedas(String nombre, double costoEntrada, double probabilidadGanar, double premioBase, int numeroRodillos) {
        super(nombre, costoEntrada, probabilidadGanar, premioBase);
        this.numeroRodillos = numeroRodillos;
    }

    @Override
    public void iniciarJuego() {
        System.out.println("Iniciando el Juego de Tragamonedas con " + numeroRodillos + " rodillos.");
    }

    public ResultadoJuego jugarConJugador(Jugador jugador, JugadorDAO jugadorDAO, double apuesta) {
        Random random = new Random();
        String[] resultados = new String[numeroRodillos];
    
        // Generar resultados de los rodillos
        System.out.print("Resultados: ");
        for (int i = 0; i < numeroRodillos; i++) {
            resultados[i] = simbolos[random.nextInt(simbolos.length)];
            System.out.print(resultados[i] + " ");
        }
        System.out.println();
    
        // Verificar si todos los rodillos tienen el mismo símbolo
        boolean todosIguales = true;
        for (int i = 1; i < resultados.length; i++) {
            if (!resultados[i].equals(resultados[0])) {
                todosIguales = false;
                break;
            }
        }
    
        if (todosIguales) {
            // El jugador gana
            System.out.println("¡Felicidades! Has ganado!");
            return ResultadoJuego.GANAR;
        } else {
            System.out.println("Lo siento, no ganaste esta vez.");
            return ResultadoJuego.PERDER;
        }
    }
    
    

    @Override
    public double calcularRecompensa(double apuesta) {
        return apuesta * getPremioBase() * numeroRodillos;
    }
}
