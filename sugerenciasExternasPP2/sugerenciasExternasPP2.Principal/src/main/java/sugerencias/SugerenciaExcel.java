package sugerencias;

import conecciones.LeerExcelCSV;
import util.Date;

public class SugerenciaExcel extends Sugerencias{
	
	public SugerenciaExcel(String local, String ubicacion, String producto, Double precio, Date fechaDeVigencia) 
    {
    	super(local, ubicacion, producto, precio, fechaDeVigencia);
    }
	
	public void leerSugerencias(){
		leerExcelCSV();
	}
	
	public void leerExcelCSV(){
		LeerExcelCSV l= new LeerExcelCSV();
    	l.leerExcelCSV();
	}
	
	
}
