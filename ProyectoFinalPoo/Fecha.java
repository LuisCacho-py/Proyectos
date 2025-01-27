package o2024.Casino;

import java.util.Calendar;

public class Fecha {
	private int dia, mes, año;
	private String nombreMes;
	private int formato;
	
	public final static int DIA_POR_OMISION = 1;
	public final static int MES_POR_OMISION = 1;
	public final static int AÑO_POR_OMISION = 2024;
	public final static int FORMATO_POR_OMISION = 0;
	
	public final static int AÑO_MIN = 1900;
	public final static int AÑO_MAX = 3000;
	
	private String[] arrMes = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	
	public Fecha() {
		Calendar c = Calendar.getInstance();
		setAño(c.get(Calendar.YEAR));
		setMes(c.get(Calendar.MONTH) + 1);
		setDia(c.get(Calendar.DAY_OF_MONTH));
//		this(DIA_POR_OMISION, MES_POR_OMISION, AÑO_POR_OMISION, FORMATO_POR_OMISION);
	}
	
	public Fecha(int dia, int mes, int año) {
		this(dia, mes, año, FORMATO_POR_OMISION);
	}
	
	public Fecha(int dia, int mes, int año, int formato) {
		setAño(año);
		setMes(mes);
		setDia(dia);
		setFormato(formato);
	}
	
	public int getDia() { return dia; }
	public int getMes() { return mes; }
	public int getAño() { return año; }
	public int getFormato() { return formato; }
	public String getNombreMes() { return nombreMes; }
	
	public void setAño( int año ) {
		if (año >= AÑO_MIN && año <= AÑO_MAX) this.año = año;
	}
	
	public void setMes( int mes ) {
		if (mes >= 1 && mes <= 12) { 
			this.mes = mes;
			nombreMes = arrMes[mes-1];
		}
	}
	
	private int maxDia() {
		int maxD = 31;
		if (mes == 4 || mes == 6 || mes == 9 || mes == 11) maxD = 30;
		if (mes == 2) maxD = 28;
		return maxD;
	}
	
	public void setDia( int dia ) {
		if (dia >= 1 && dia <= maxDia()) this.dia = dia;
	}
	
	public void setFormato( int formato ) {
		if (formato >= 0 && formato <= 2) this.formato = formato;
	}
	
	public String toString() {
		if (formato == 0) return String.format("%02d/%02d/%02d", dia, mes, año % 100);
		else if (formato == 1) return dia + "-" + nombreMes.substring(0,3) + "-" + año;
		else return dia + " de " + nombreMes.toLowerCase() + " de " + año;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof Fecha)) return false;
		
		Fecha f = (Fecha)o;
		return (dia == f.dia && mes == f.mes && año == f.año && formato == f.formato);
	}
	
	public Fecha clone() {
		return new Fecha(dia, mes, año, formato);
	}
	
	public void siguiente() {
		dia++;
		if (dia > maxDia()) {
			dia = 1;
			if (mes < 12) setMes(mes+1);
			else
			{
				setMes(1);
				año++;
				if (año > AÑO_MAX) año = AÑO_MAX;
			}
		}
	}
}

