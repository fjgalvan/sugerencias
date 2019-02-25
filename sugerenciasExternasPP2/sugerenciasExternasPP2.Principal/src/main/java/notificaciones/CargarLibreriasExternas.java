package notificaciones;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class CargarLibreriasExternas {

	public CargarLibreriasExternas(String namePuntoJar)
			throws MalformedURLException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		cargarJar(namePuntoJar);
	}

	public void cargarJar(String namePuntoJar) throws MalformedURLException,
			NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		String rutaDelJar = "C:\\formas de envio\\notificaciones\\" + namePuntoJar;
		try {
			File file = new File(rutaDelJar);// ("C:\\formas de envio\\saludos\\mail.jar");
			URL url = file.toURI().toURL();

			URLClassLoader classLoader = (URLClassLoader) ClassLoader
					.getSystemClassLoader();
			Method method = URLClassLoader.class.getDeclaredMethod("addURL",
					URL.class);
			method.setAccessible(true);
			method.invoke(classLoader, url);
			System.out.println("Se cargo el jar: " + rutaDelJar);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error al cargar el jar: " + rutaDelJar);
		}
	}
}
