package sugerencias;

import conexiones.conexionExel.LeerExcelCSV;
import util_.Date;

public class SugerenciaExcel extends Sugerencias{
	
	public SugerenciaExcel(String local, String ubicacion, String producto, String precio, String fechaDeVigencia) 
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
