package o2024.Casino;

//interface
public interface Recompensa {
	void recibirRecompensa(double monto, JugadorDAO jugadorDAO);//Monto equivale al premio que se recibira
	//Nos ayuda a que cualquir clase que la implemente sea si o si que tenga un premio
}
