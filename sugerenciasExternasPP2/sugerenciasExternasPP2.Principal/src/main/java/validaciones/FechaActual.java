package validaciones;

import java.util.Calendar;
import java.util.Date;


public class FechaActual {
	static Date date= new Date();
	static Calendar c;
	
	public FechaActual(){
		c = Calendar.getInstance();
	}
	
	public static void FechaActualMostrar(){
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		System.out.println(dia +"-"+ mes +"-"+ annio);
	}
	
	public Integer getDiaActual(){
		return c.get(Calendar.DATE);
	}
	
	public Integer getMesActual(){//devuelve el mes anterior, esto fue corregido
		return c.get(Calendar.MONTH)+1;
	}
	
	public Integer getAnnioActual(){
		return c.get(Calendar.YEAR);
	}
}
