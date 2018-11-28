package dao.filtrosDeUsuario;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import properties.Constants;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

import configurables.MyConstantsConexiones;
import configurables.MyConstantsDAO;

public class TaggearComidas {
	DBCollection collection;
	Properties p;
	HashMap<String, Double> lComidasVacia;

	public TaggearComidas(DBCollection collection) {
		this.collection = collection;
		this.p = new Properties();
		/** Creamos un Objeto de tipo Properties */
		try {
			p.load(new FileReader(Constants.ROUTE_COMIDAS_PROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// GETs & SETs
	public DBCollection getCollection() {
		return collection;
	}

	public void setCollection(DBCollection collection) {
		this.collection = collection;
	}

	// TAGGEAR COMIDAS
	public DBCollection taggearComidas() {
		Enumeration<Object> keys = p.keys();
		BasicDBObject newDocument = new BasicDBObject();
		
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			if (p.get(key).equals(MyConstantsDAO.tagSanas)) {// si encuebtro algun tipo de
												// comida sana
				newDocument.append("$set",
						new BasicDBObject().append(MyConstantsDAO.tipoDeComida, MyConstantsDAO.tagSanas));
				BasicDBObject searchQuery = new BasicDBObject().append(
						MyConstantsDAO.promoProducto, key);
				collection.update(searchQuery, newDocument);
			}
			if (p.get(key).equals(MyConstantsDAO.tagChatarras)) {// si encuebtro algun tipo de
													// comida sana
				newDocument.append("$set",
						new BasicDBObject().append(MyConstantsDAO.tipoDeComida, MyConstantsDAO.tagChatarras));
				BasicDBObject searchQuery = new BasicDBObject().append(
						MyConstantsDAO.promoProducto, key);
				collection.update(searchQuery, newDocument);
			}
			if (p.get(key).equals(MyConstantsDAO.tagPastas)) {// si encuebtro algun tipo de
												// comida sana
				newDocument.append("$set",
						new BasicDBObject().append(MyConstantsDAO.tipoDeComida, MyConstantsDAO.tagPastas));
				BasicDBObject searchQuery = new BasicDBObject().append(
						MyConstantsDAO.promoProducto, key);
				collection.update(searchQuery, newDocument);
			}
			if (p.get(key).equals(MyConstantsDAO.tagPostres)) {// si encuebtro algun tipo de
												// comida sana
				newDocument.append("$set",
						new BasicDBObject().append(MyConstantsDAO.tipoDeComida, MyConstantsDAO.tagPostres));
				BasicDBObject searchQuery = new BasicDBObject().append(
						MyConstantsDAO.promoProducto, key);
				collection.update(searchQuery, newDocument);
			}
		}
		return collection;

	}

	public DBCollection eliminarComidasSinTaggear(DBCollection collection3) {
		lComidasVacia = new HashMap<String, Double>();
		BasicDBObject searchQueryEliminar = new BasicDBObject().append(MyConstantsDAO.tipoDeComida, MyConstantsDAO.tipoComidaDefault);
		collection3.remove(searchQueryEliminar);
		this.collection= collection3;
		return collection3;
	}
	
//	public DBCollection eliminarSinTag(DBCollection collection3) {
//		lComidasVacia = new HashMap<String, Double>();
//		BasicDBObject searchQueryEliminar = new BasicDBObject().append(MyConstantsDAO.tipoDeComida, lComidasVacia);
//		collection3.remove(searchQueryEliminar);
//		this.collection= collection3;
//		return collection3;
//	}
	
	public DBCollection taggeoInicial(DBCollection collection3){
		BasicDBObjectBuilder whereBuilder2 = BasicDBObjectBuilder.start();
		whereBuilder2.append(MyConstantsDAO.promoLocal, MyConstantsDAO.localMcDonalds);
		DBObject where2 = whereBuilder2.get();
		BasicDBObject update2= new BasicDBObject();
		update2.append("$set", new BasicDBObject().append(MyConstantsDAO.tipoDeComida, MyConstantsDAO.tipoComidaDefault));
		collection3.update(where2, update2);
		this.collection= collection3;
		return collection3;
		
	}
	
	public DBCollection taggeoInicialLocal(DBCollection collection3, String local){
		BasicDBObjectBuilder whereBuilder2 = BasicDBObjectBuilder.start();
		whereBuilder2.append(MyConstantsDAO.promoLocal, local);
		DBObject where2 = whereBuilder2.get();
		BasicDBObject update2= new BasicDBObject();
		update2.append("$set", new BasicDBObject().append(MyConstantsDAO.tipoDeComida, MyConstantsDAO.tipoComidaDefault));
		collection3.update(where2, update2);
		this.collection= collection3;
		return collection3;
		
	}
	
	public void mostrarPropertiesDeTipoComidas() {
		Enumeration<Object> keys = p.keys();

		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			//System.out.println(key + " = " + p.get(key));
		}
	}

}
