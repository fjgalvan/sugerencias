package notificaciones;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class BuscarArchivos {
	ArrayList<String> listaDeClases;
	ArrayList<String> listaDeJar;
		
	
	public BuscarArchivos(File dir) throws ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
		listaDeClases = new ArrayList<String>();
		listaDeJar= new ArrayList<String>();
		
		verContenidoFolder(dir);
	}

	
	public void verContenidoFolder(File dir)
			throws ClassNotFoundException, IllegalArgumentException,
			InvocationTargetException {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					verContenidoFolder(file);
				} else { // file.isFile()
					String nameArchivo= file.getCanonicalPath();
					//System.out.println("nameArchivo: "+nameArchivo);

					// Muestro el nombre del archivo "file"
					if(isNombreFileAClass(nameArchivo)){
						//System.out.println("NOMBRE PAQUETE CLASE: "+ buscarNombrePaqueteClase(file));
						listaDeClases.add(buscarNombrePaqueteClase(file));
					}
					// Muestro el nombre del archivo "file"
					if(isNombreFileAJar(nameArchivo)){
						String fileName2 = file.getName().toString();
						//System.out.println("NOMBRE jar: "+fileName2);
						listaDeJar.add(fileName2);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isNombreFileAClass(String nombreFile) {
		boolean res = false;
		if (nombreFile.contains(".class")) {
			//System.out.println("El archivo es un .class");
			res = true;
		} else {
			//System.out.println("El archivo NO es un .class");
		}
		return res;
	}
	
	public boolean isNombreFileAJar(String nombreFile) {
		boolean res = false;
		if (nombreFile.contains(".jar")) {
			//System.out.println("El archivo es un .jar");
			res = true;
		} else {
			//System.out.println("El archivo NO es un .jar");
		}
		return res;
	}
	
	public String buscarNombrePaqueteClase(File file) {
		String nombrePaqueteClase = "";
		String nombreParent = file.getParent().toString();
		String nombreClass = file.getName().toString();

		String separador = Pattern.quote("\\");
		String[] parts = nombreParent.split(separador);
		String partPaquete = parts[2];

		String separador2 = Pattern.quote(".");
		String[] parts2 = nombreClass.split(separador2);
		String partClase = parts2[0];

		nombrePaqueteClase = partPaquete + "." + partClase;

		return nombrePaqueteClase;
	}

	public ArrayList<String> getListaDeClases() {
		return listaDeClases;
	}

	public ArrayList<String> getListaDeJar() {
		return listaDeJar;
	}
	
	
	
}
