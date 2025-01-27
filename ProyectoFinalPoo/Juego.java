package o2024.Casino;



//Clase abstracta
public abstract class Juego {
	private String nombre;//Nombre del juego
	private double costoEntrada;//Lo que cuesta una jugada
	private double probabilidadGanar;
	protected double premioBase;
	
	//Constructor que ayuda a que cada juego tenga un nombre y un costo definido
	public Juego(String nombre, double costoEntrada, double probabilidadGanar, double premioBase) {
		this.nombre = nombre;
		this.costoEntrada = costoEntrada;
		this.probabilidadGanar = probabilidadGanar;
      this.premioBase = premioBase;
	}
	
	//Getters and setters
	public String getNombre() {
		return nombre;//Va a regresar el nombre del juego para mostrarlo en el menu
	}	
	public double getCostoEntrada() {
		return costoEntrada;//Va a regresar el costo de la entrada
	}
	
	public double getProbabilidadGanar() {
      return probabilidadGanar;
  }

  public double getPremioBase() {
      return premioBase;
  }
	
  
  public ResultadoJuego jugar(double apuesta) {
		//Genera un numero aleatorio para decidir si el jugador gana o pierde.
      double resultado = Math.random();
      if (resultado <= probabilidadGanar) {
          return ResultadoJuego.GANAR;
      } else {
          return ResultadoJuego.PERDER;
      }
	}
  
  public double calcularRecompensa(double apuesta) {
      //Si es que la probabilidad de ganar es baja, el premio será mayor.
      return apuesta * (1 / probabilidadGanar) * premioBase;
  }
	
	
	//Metodos abstractos
	public abstract void iniciarJuego();
	
}

