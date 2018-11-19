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
		System.out.println("DBCollection taggearComidas()");
		
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			if (p.get(key).equals("sanas")) {// si encuebtro algun tipo de
												// comida sana
				newDocument.append("$set",
						new BasicDBObject().append("lComidas", "sanas"));
				BasicDBObject searchQuery = new BasicDBObject().append(
						"producto", key);
				collection.update(searchQuery, newDocument);
			}
			if (p.get(key).equals("chatarras")) {// si encuebtro algun tipo de
													// comida sana
				newDocument.append("$set",
						new BasicDBObject().append("lComidas", "chatarras"));
				BasicDBObject searchQuery = new BasicDBObject().append(
						"producto", key);
				collection.update(searchQuery, newDocument);
			}
			if (p.get(key).equals("pastas")) {// si encuebtro algun tipo de
												// comida sana
				newDocument.append("$set",
						new BasicDBObject().append("lComidas", "pastas"));
				BasicDBObject searchQuery = new BasicDBObject().append(
						"producto", key);
				collection.update(searchQuery, newDocument);
			}
			if (p.get(key).equals("postres")) {// si encuebtro algun tipo de
												// comida sana
				newDocument.append("$set",
						new BasicDBObject().append("lComidas", "postres"));
				BasicDBObject searchQuery = new BasicDBObject().append(
						"producto", key);
				collection.update(searchQuery, newDocument);
			}
		}
		return collection;

	}

	public DBCollection eliminarComidasSinTaggear(DBCollection collection3) {
		lComidasVacia = new HashMap<String, Double>();
		BasicDBObject searchQueryEliminar = new BasicDBObject().append("lComidas", "vacia");
		collection3.remove(searchQueryEliminar);
		this.collection= collection3;
		return collection3;
	}
	
	public DBCollection taggeoInicial(DBCollection collection3){
		System.out.println("taggeoInicial");
		BasicDBObjectBuilder whereBuilder2 = BasicDBObjectBuilder.start();
		whereBuilder2.append("local", "mcDonalds");
		DBObject where2 = whereBuilder2.get();
		BasicDBObject update2= new BasicDBObject();
		update2.append("$set", new BasicDBObject().append("lComidas", "vacia"));
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
