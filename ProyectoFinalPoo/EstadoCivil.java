package o2024.Casino;

public enum EstadoCivil {
    SOLTERO("Soltero"),CASADO("Casado"),VIUDO("Viudo"),DIVORCIADO("Divorciado"),UNION_LIBRE("Union libre");

    private String estadoCivil;

    EstadoCivil(String nombre)
    {
        estadoCivil= nombre;
    }
    public String toString()
    {
        return estadoCivil;
    }
}
