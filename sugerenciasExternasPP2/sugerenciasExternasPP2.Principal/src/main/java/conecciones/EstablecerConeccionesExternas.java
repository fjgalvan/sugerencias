package conecciones;

public class EstablecerConeccionesExternas {
	
	public EstablecerConeccionesExternas(){
		
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	public void conectarseATwitter(){
		//get Bloque estático
	    try { 
	        Class c = Class.forName ("conecciones.ConectorTwitterJ"); 
	    } catch (ClassNotFoundException e) { 
	    } 
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	public void conectarseAExcelCSV(){
		//get Bloque estático
	    try { 
	        Class c = Class.forName ("conecciones.LeerExcelCSV"); 
	    } catch (ClassNotFoundException e) { 
	    } 
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	public void conectarseAExcelXLSX(){
		//get Bloque estático
	    try { 
	        Class c = Class.forName ("conecciones.LeerFicherosExcel"); 
	    } catch (ClassNotFoundException e) { 
	    } 
	}
	
}
