package notificaciones;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;

public class CargarArchivoClass {
	
	public CargarArchivoClass(String nombrePaqueteClase,String destino, String notificacion) throws IllegalArgumentException, InvocationTargetException, ClassNotFoundException, MalformedURLException, NoSuchMethodException, SecurityException, IllegalAccessException{
		
		cargarDinamicamente2(nombrePaqueteClase,destino, notificacion);
	}
	
	public void cargarDinamicamente2(String nombrePaqueteClase,
			String destino, String notificacion)
			throws IllegalArgumentException, InvocationTargetException, ClassNotFoundException, MalformedURLException, NoSuchMethodException, SecurityException, IllegalAccessException {
		
		try {
			System.out.println("nombrePaqueteClase: "+nombrePaqueteClase);
			String classToLoad = nombrePaqueteClase;
			System.out.println("classToLoad: "+classToLoad);
			File file = new File("C:\\formas de envio");
			// Convert File to a URL
			URL url = file.toURI().toURL();
			URL[] urls = new URL[] { url };
			ClassLoader cl = new URLClassLoader(urls);
			Class cls = cl.loadClass(classToLoad);
			// Obtengo el Objeto de la clase
			Object instance = cls.newInstance();
			// Ejecuto los 2 metodos de la "InterfazNotificacion"
			ValidarArchivoClass va= new ValidarArchivoClass();
			if (va.isInstanceOfInterfazNotificacion(instance,
					"notificaciones.InterfazNotificacion")) { //Acá debo poner la condicion de ejecutar solo 1 .class (nombrePaqueteClase.equals(JCombobox )
				// Ejecuto metodo y sus Tipo.Class de los argumentos
				Method setNameMethod = instance.getClass().getMethod(
						"conectarConServicioDeNotificacion", null);// NombreMetodo,
				// tipoArg1.class, ...
				setNameMethod.invoke(instance, null); // pass arg1

				// Ejecuto metodo y sus Tipo.Class de los argumentos
				Method setNameMethod2 = instance.getClass().getMethod(
						"enviarNotificacion", String.class, String.class);// NombreMetodo,
				// tipoArg1.class, ...
				setNameMethod2.invoke(instance, destino, notificacion); // pass
																		// arg1
			}

		} catch (ClassNotFoundException | MalformedURLException
				| NoSuchMethodException | InstantiationException
				| IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
}
