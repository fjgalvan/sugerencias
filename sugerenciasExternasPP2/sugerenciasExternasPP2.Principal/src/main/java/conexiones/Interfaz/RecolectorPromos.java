package conexiones.Interfaz;

import java.io.FileReader; 
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import configurables.MyConstantsModelo;
import properties.Constants;
import sugerencias.Sugerencias;
import twitter4j.TwitterException;
import util_.Date;
import dao.filtrosDeUsuario.TaggearComidas;
//import dao.mongoDB.MongoConcreteStub;
import dao.mongoDB.MongoConcrete;

public class RecolectorPromos {
	Properties propConexion;
	//MongoConcreteStub mongoDB;
	MongoConcrete mongoDB;
	
	
	private List<InterfaceConectores> listaDeConectores = new ArrayList<InterfaceConectores>();

	//public RecolectorPromos() {//SIN ARGUMENTOS (MongoConcreteStub mongoDB)
	public RecolectorPromos(MongoConcrete mongoDB) {
		this.mongoDB=mongoDB;
		propConexion = new Properties();
		try {
			propConexion.load(new FileReader(Constants.ROUTE_PROPConexion));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//mongoDB= new MongoConcreteStub();
		//mongoDB= new MongoConcrete("promosActual");//YA LO CREE A LA COLLECTION
		//this.mongoDB= mongoDB;
	}

	public List<InterfaceConectores> cargarListaConectores()
			throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			TwitterException {

		Enumeration<Object> keys = propConexion.keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			//System.out.println(key + " = " + propConexion.get(key));
			InterfaceConectores ic= (InterfaceConectores) conexionExternaDinamica(key.toString());
			listaDeConectores.add(ic);
		}
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
	
	public DBCollection promosConFiltros(String tag1, String tag2){
		
		mongoDB.leerColeccion();
		DBCursor cursor = mongoDB.getPromos().find();
		DBObject docExcel=cursor.next();
		mongoDB.getPromos().remove(docExcel);
		try {
			while (cursor.hasNext()) {

				DBObject doc= cursor.next();
				String local=(String) doc.get(MyConstantsModelo.promoLocal);
				int cont=0;
				try{
					String lComidas=(String) doc.get("lComidas");
					//System.out.println("lComidas: "+lComidas);
					if(!(lComidas.equals(tag1))){
						cont= cont +1;
					}
					if(!(lComidas.equals(tag2))){
						cont= cont +1;
					}
					if(cont==2){
						mongoDB.getPromos().remove(doc);
					}
				}catch(Exception e){
					mongoDB.getPromos().remove(doc);
				}
				
			}
		} finally {
			cursor.close();
		}
		mongoDB.leerColeccion();
		return mongoDB.getPromos();
	}
	
	public  List<InterfaceConectores> getListadeconectores() {
		return listaDeConectores;
	}

//	public MongoConcreteStub getMongoDB() {
//		return mongoDB;
//	}
//	
//	public void finishMongo(){
//		mongoDB.finish();
//	}
	public MongoConcrete getMongoDB() {
		return mongoDB;
	}
	
	public void finishMongo(){
		//mongoDB.finish();
	}
}