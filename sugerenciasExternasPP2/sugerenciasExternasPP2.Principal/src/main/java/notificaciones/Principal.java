package notificaciones;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class Principal {
	public static void main(String[] args) throws ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException, MalformedURLException, NoSuchMethodException, SecurityException, IllegalAccessException {
//		File miDir = new File("C:\\formas de envio");
//		BuscarArchivos ba = new BuscarArchivos(miDir);
//
//		// Cargo los archivos.jar
//		Iterator<String> nombreIterator = ba.getListaDeJar().iterator();
//		while (nombreIterator.hasNext()) {
//			String elemento = nombreIterator.next();
//			CargarLibreriasExternas cle = new CargarLibreriasExternas(elemento);
//		}
//		
//		//Valido archivo .class de que sea una instancia de la InterfazNotificacion.
//		
//		CargarArchivoClass cac1= new CargarArchivoClass("notificaciones.NotificacionPorEmail", "fjgalvan_x87@yahoo.com.ar", "promos de comidas!");
//		
//		//CargarArchivoClass cac2= new CargarArchivoClass("notificaciones.NotificacionPorTwitter", "@Cristian38666", "promos de comidas!");
		String string = "004#034556";
		String[] parts = string.split("#");
		String part1 = parts[0]; // 004
		String part2 = parts[1]; // -034556
		System.out.println("string: "+string);
		System.out.println("part1: "+part1);
		System.out.println("part2: "+part2);
		for(int i=0; i<parts.length; i++){
			System.out.println(parts[i]);
		}
		Date date = new Date();
		//Caso 1: obtener la hora y salida por pantalla con formato:
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		System.out.println("Hora: "+hourFormat.format(date));
		//Caso 2: obtener la fecha y salida por pantalla con formato:
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Fecha: "+dateFormat.format(date));
		//Caso 3: obtenerhora y fecha y salida por pantalla con formato:
		DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		System.out.println("Hora y fecha: "+hourdateFormat.format(date));
		
	}
}
