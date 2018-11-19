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

import modelo.Producto;
import modelo.Usuario;
import properties.Constants;
import twitter4j.TwitterException;
import conexiones.conexionTwitter.UsoTwitterDeUsuario;
import dao.filtrosDeUsuario.TaggearComidas;
import dao.mongoDB.MongoConcrete;
import dao.mongoDB.MongoConcreteStub;

public class RecolectorPromos {
	Properties propConexion;
	//MongoConcrete mongoDB;
	MongoConcreteStub mongoDB;
	
	private List<InterfaceConectores> listaDeConectores = new ArrayList<InterfaceConectores>();

	public RecolectorPromos() {
		propConexion = new Properties();
		try {
			propConexion.load(new FileReader(Constants.ROUTE_PROPConexion));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mongoDB= new MongoConcreteStub(); //MongoConcrete();
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
			InterfaceConectores ic= (InterfaceConectores) conexionExternaDinamica(key.toString());//(propConexion.get(key).toString());
			listaDeConectores.add(ic);
		}
		System.out.println("propConexion size: "+propConexion.size());
		System.out.println("listaDeConectores: "+listaDeConectores.size());//18 deberia ser 9
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
		// Crear clase de tipo UsoTwitterDeUsuario.
		Class<?> myClass = Class.forName(propConexion.getProperty(nombreConexionExterna));// paquete.nombreClase
		// Crear llamada de constructor con tipos de argumento.
		Constructor<?> ctr = myClass.getDeclaredConstructor();
		// Finalmente crear objeto de tipo de una ConexionExterna y pasar datos
		// al constructor.
		// String arg1 = "My User Data";
		// Object object = ctr.newInstance(new Object[] { arg1 });
		Object object = ctr.newInstance(new Object[] {});

		return object;
	}

	public void buscarPromociones() {
		int i=0;
		DBCollection promociones;
		for (InterfaceConectores conector : listaDeConectores) {
			conector.conectarse();
			promociones= conector.getPromo(mongoDB.getPromos());
			//promociones.aggregate(conector.getPromo());
			//guardo la colleccion en la base de DAO
			//mongoDB.agregarNuevosDocumentos(promociones);//repite _id
			System.out.println("contador recoPromo: "+i);
			i++;
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
