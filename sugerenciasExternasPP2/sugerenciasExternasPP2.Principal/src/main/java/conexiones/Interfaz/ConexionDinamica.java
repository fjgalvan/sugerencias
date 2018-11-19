package conexiones.Interfaz;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import properties.Constants;
import twitter4j.TwitterException;
import conexiones.conexionTwitter.ConectorTwitter;

public class ConexionDinamica {
	Properties propConexion;

	public ConexionDinamica() {
		propConexion = new Properties();
		try {
			propConexion.load(new FileReader(Constants.ROUTE_PROPConexion));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Object conexionExternaDinamica(String nombreConexionExterna)
			// twitter-excelcsv-excelxlsx
			throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			TwitterException {
		// Crear clase de tipo ConectorTwitter.
		Class<?> myClass = Class.forName(propConexion
				.getProperty(nombreConexionExterna));// paquete.nombreClase
		// Crear llamada de constructor con tipos de argumento.
		Constructor<?> ctr = myClass.getDeclaredConstructor();
		// Finalmente crear objeto de tipo de una ConexionExterna y pasar datos
		// al constructor.
		// String arg1 = "My User Data";
		// Object object = ctr.newInstance(new Object[] { arg1 });
		Object object = ctr.newInstance(new Object[] {});

		return object;
	}

	public static void main(String args[]) throws ClassNotFoundException,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, TwitterException {

		ConexionDinamica c = new ConexionDinamica();

		// Escriba-cast y acceda a los datos de la clase Base.
		ConectorTwitter conexion = (ConectorTwitter) c
				.conexionExternaDinamica("twitter");
		conexion.conexionConTwitterDeUsuario();
		conexion.RecuperarListadoDeUltimosTweetsEscritos();
	}
}
