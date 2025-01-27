package o2024.Casino;



public class TestCasino {
    public static void main(String[] args) {
        System.out.println("**********************************");
        System.out.println("    ¡Bienvenido al Casino Beta!   ");
        System.out.println("**********************************");

        // Probar la conexión a la base de datos
        if (ConexionBD.conectar() == null) {
            System.out.println("No se pudo conectar a la base de datos. Verifica la configuración.");
            return;
        }

        // Inicializar el casino y agregar juegos
        Casino casino = new Casino();
        casino.agregarJuego(new Tragamonedas("Tragamonedas", 10, 0.3, 2.0, 3));
        casino.agregarJuego(new Ruleta("Ruleta", 15, 0.2, 3.0, 36));

        // Iniciar el casino
        casino.iniciarCasino();
    }
}
