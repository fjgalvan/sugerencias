package conexiones.Interfaz;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import com.mongodb.DBCollection;
import properties.Constants;
import twitter4j.TwitterException;
import dao.mongoDB.MongoConcreteStub;

public class RecolectorPromos {
	Properties propConexion;
	MongoConcreteStub mongoDB;
	
	private List<InterfaceConectores> listaDeConectores = new ArrayList<InterfaceConectores>();

	public RecolectorPromos() {
		propConexion = new Properties();
		try {
			propConexion.load(new FileReader(Constants.ROUTE_PROPConexion));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mongoDB= new MongoConcreteStub();
	}

	public List<InterfaceConectores> cargarListaConectores()
			throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			TwitterException {

		Enumeration<Object> keys = propConexion.keys();
		System.out.println("propConexion size: "+propConexion.size());
		System.out.println("listaDeConectores INICIO: "+listaDeConectores.size());
		while (keys.hasMoreElements()) {
			System.out.println("Dentro del While");
			Object key = keys.nextElement();
			System.out.println(key + " = " + propConexion.get(key));
			InterfaceConectores ic= (InterfaceConectores) conexionExternaDinamica(key.toString());
			listaDeConectores.add(ic);
		}
		System.out.println("propConexion size: "+propConexion.size());
		System.out.println("listaDeConectores: "+listaDeConectores.size());
		return listaDeConectores;
	}

	public Object conexionExternaDinamica(
			String nombreConexionExterna)
			// Object
			// twitter-excelcsv-excelxlsx
			throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			TwitterException {
		// Crear clase de tipo ConectorTwitter.
		Class<?> myClass = Class.forName(propConexion.getProperty(nombreConexionExterna));// paquete.nombreClase
		// Crear llamada de constructor con tipos de argumento.
		Constructor<?> ctr = myClass.getDeclaredConstructor();
		Object object = ctr.newInstance(new Object[] {});

		return object;
	}

	public void buscarPromociones() {
		@SuppressWarnings("unused")
		DBCollection promociones;
		for (InterfaceConectores conector : listaDeConectores) {
			conector.conectarse();
			promociones= conector.getPromo(mongoDB.getPromos());
		}
	}

	public  List<InterfaceConectores> getListadeconectores() {
		return listaDeConectores;
	}

	
	// -------------------------------------------------------------------------------------//
	public MongoConcreteStub getMongoDB() {
		return mongoDB;
	}

//	public static void main(String args[]) throws ClassNotFoundException,
//			NoSuchMethodException, SecurityException, InstantiationException,
//			IllegalAccessException, IllegalArgumentException,
//			InvocationTargetException, TwitterException {
//
//		RecolectorPromos c = new RecolectorPromos();
//
//		c.cargarListaConectores();
//		c.buscarPromociones();
//		System.out.println("c.getMongoDB().getPromos().count(): "+c.getMongoDB().getPromos().count());
//		TaggearComidas tc= new TaggearComidas(c.getMongoDB().getPromos());
//		tc.eliminarComidasSinTaggear(c.getMongoDB().getPromos());
//		c.getMongoDB().leerColeccion();
//		
//	}

}