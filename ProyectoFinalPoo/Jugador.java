package o2024.Casino;

public class Jugador extends Persona implements Recompensa {
    private NivelJugador nivel; // Su nivel
    private static double saldo; // Dinero que tiene
    private static int numeroApuestas; // Número de apuestas realizadas

    public Jugador(String nombre, String apellidos, Fecha fechaNacimiento, EstadoCivil estadoCivil, Sexo sexo, NivelJugador nivel, double saldo, int numeroApuestas) {
        super(nombre, apellidos, fechaNacimiento, estadoCivil, sexo); // Llamada al constructor de Persona
        this.nivel = nivel;
        this.saldo = saldo;
        this.numeroApuestas = numeroApuestas;
    }

    // Constructor para nuevos jugadores (por omisión)
    public Jugador(String nombre, NivelJugador nivel, double saldo) {
        super(nombre, Persona.APELLIDOS_POR_OMISION, new Fecha(Persona.DIA_NACIMIENTO_POR_OMISION, Persona.MES_NACIMIENTO_POR_OMISION, Persona.ANIO_NACIMIENTO_POR_OMISION));
        this.nivel = nivel;
        this.saldo = saldo;
        this.numeroApuestas = 0; // Por omisión comienza en 0
    }

    public NivelJugador getNivel() {
        return nivel;
    }

    public void setNivel(NivelJugador nivel) {
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getNumeroApuestas() {
        return numeroApuestas;
    }

    public void incrementarApuestas() {
        this.numeroApuestas++;
    }

    public void apostar(double monto, JugadorDAO jugadorDAO) {
        if (monto > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        saldo -= monto;
        System.out.println(getNombre() + " apostó $" + monto + ". Saldo restante: $" + saldo);

        // Actualizar en la base de datos
        jugadorDAO.actualizarSaldo(getNombre(), saldo);
    }

    @Override
    public void recibirRecompensa(double monto, JugadorDAO jugadorDAO) {
        saldo += monto;
        System.out.println(getNombre() + " recibió una recompensa de $" + monto + ". Nuevo saldo: $" + saldo);
        jugadorDAO.actualizarSaldo(getNombre(), saldo);
    }
}

