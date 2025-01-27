package o2024.Casino;

public class Persona {
	protected String nombre;
	protected String apellidos;
	protected Fecha fechaNacimiento;
	protected EstadoCivil estadoCivil;
	protected Sexo sexo;
	
	// Constantes
	protected static final String NOMBRE_POR_OMISION = "Fulano";
	protected static final String APELLIDOS_POR_OMISION = "De Tal";
	protected static final int DIA_NACIMIENTO_POR_OMISION = 1;
	protected static final int MES_NACIMIENTO_POR_OMISION = 1;
	protected static final int ANIO_NACIMIENTO_POR_OMISION = 1900;
	protected static final EstadoCivil ESTADO_CIVIL_POR_OMISION = EstadoCivil.SOLTERO;
	protected static final Sexo SEXO_POR_OMISION = Sexo.FEMENINO;
	
	public Persona(String nombre, String apellidos, Fecha fechaNacimiento, EstadoCivil estadoCivil, Sexo sexo) {
		setNombre(nombre);
		setApellidos(apellidos);
		setFechaNacimiento(fechaNacimiento);
		setEstadoCivil(estadoCivil);
		setSexo(sexo);
	}

	
	// Constructor - metodo especial de la POO
	// La mayoria de las veces tiene alcance public
	// No indica el tipo de datos que retorna, ni siquiera void
	// Tiene el mismo nombre que la clase
	// Se puede sobrecargar
	public Persona(String nombre, String apellidos, Fecha fechaNacimiento) {
		this(nombre, apellidos, fechaNacimiento, ESTADO_CIVIL_POR_OMISION, SEXO_POR_OMISION);
	}
	
	public Persona(String nombre, String apellidos) {
		this(nombre, apellidos, new Fecha(DIA_NACIMIENTO_POR_OMISION, MES_NACIMIENTO_POR_OMISION, ANIO_NACIMIENTO_POR_OMISION));
	}
	
	public Persona() {
		this(NOMBRE_POR_OMISION, APELLIDOS_POR_OMISION);
	}
	
	public Sexo getSexo() { return sexo; }
	
	public EstadoCivil getEstadoCivil() { return estadoCivil; }
	
	
	public int getEdad() {
		int anioActual = 2024;
		int mesActual = 10;
		int diaActual = 31;
		
		int difAnios = anioActual - fechaNacimiento.getAño();
		if (mesActual <= fechaNacimiento.getMes()) {
			if (mesActual == fechaNacimiento.getMes()) {
				if (diaActual < fechaNacimiento.getDia())
					difAnios--;
			}
			else difAnios--;
		}
			
		return difAnios;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nom) {
		nombre = nom;
	}
	
	public String getApellidos() {
		return apellidos;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	public void setEstadoCivil(EstadoCivil estadoCivilNuevo) {
		estadoCivil = estadoCivilNuevo;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public Fecha getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Fecha fechaNac) {
		fechaNacimiento = fechaNac;
	}
	
	public String toString() {
		return "El nombre es: " + nombre + " " + apellidos + "\n" + "La edad es: " + getEdad() + "\n";
	}
	
	public Persona clone() {
		return new Persona(nombre, apellidos, fechaNacimiento);
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof Persona)) return false; // Si el objeto que mandas para comparar no es del tipo de la clase Persona regresa falso
		
		Persona p = (Persona)o;
		if (nombre.equals(p.nombre) && apellidos.equals(p.apellidos) 
			&& fechaNacimiento.equals(p.fechaNacimiento))
			return true;
		
		return false;
	}

}
