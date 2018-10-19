package dao.filtrosDeUsuario;

import java.io.FileReader; 
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import properties.Constants;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;


public class TaggearComidas {
	DBCollection collection;
	Properties p;
	
	
	public TaggearComidas(DBCollection collection){
		this.collection= collection;
		this.p = new Properties();/** Creamos un Objeto de tipo Properties */
		try { p.load(new FileReader(Constants.ROUTE_COMIDAS_PROPERTIES));
		} catch (IOException e) { e.printStackTrace();}
	}

	//GETs & SETs
	public DBCollection getCollection() {
		return collection;}
	public void setCollection(DBCollection collection) {
		this.collection = collection;}
	
	//TAGGEAR COMIDAS 
	public DBCollection taggearComidas(){
		Enumeration<Object> keys = p.keys();
		BasicDBObject newDocument = new BasicDBObject();
		
		while (keys.hasMoreElements()){
		   Object key = keys.nextElement();
		   //System.out.println(key + " = "+ p.get(key));
		   if(p.get(key).equals("sanas")){//si encuebtro algun tipo de comida sana
			   newDocument.append("$set", new BasicDBObject().append("lComidas", "sanas"));
			   BasicDBObject searchQuery = new BasicDBObject().append("producto", key);
			   collection.update(searchQuery, newDocument);
		   }
		   if(p.get(key).equals("chatarras")){//si encuebtro algun tipo de comida sana
			   newDocument.append("$set", new BasicDBObject().append("lComidas", "chatarras"));
			   BasicDBObject searchQuery = new BasicDBObject().append("producto", key);
			   collection.update(searchQuery, newDocument);
		   }
		   if(p.get(key).equals("pastas")){//si encuebtro algun tipo de comida sana
			   newDocument.append("$set", new BasicDBObject().append("lComidas", "pastas"));
			   BasicDBObject searchQuery = new BasicDBObject().append("producto", key);
			   collection.update(searchQuery, newDocument);
		   }
		   if(p.get(key).equals("postres")){//si encuebtro algun tipo de comida sana
			   newDocument.append("$set", new BasicDBObject().append("lComidas", "postres"));
			   BasicDBObject searchQuery = new BasicDBObject().append("producto", key);
			   collection.update(searchQuery, newDocument);
		   }
		}
		return collection;
		
	}
	
	public DBCollection eliminarComidasSinTaggear(){
		HashMap<String,Double> lComidas = new HashMap<String,Double>();
//		BasicDBObject newDocument = new BasicDBObject();
//		newDocument.append("$set", new BasicDBObject().append("lComidas", lComidas));
		BasicDBObject searchQuery = new BasicDBObject().append("lComidas", lComidas);
		
		//collection.find(searchQuery).remove();
		
		collection.remove(searchQuery);
		return collection;
	}
	
	
	
	public void mostrarPropertiesDeTipoComidas(){
		Enumeration<Object> keys = p.keys();

		while (keys.hasMoreElements()){
		   Object key = keys.nextElement();
		   System.out.println(key + " = "+ p.get(key));
		}
	}
	
//	public static void main(String[] args) {
//		Mongo mongo = new Mongo("localhost", 27017);
//		DB db = mongo.getDB("yourdb11");
//		DBCollection collection = db.getCollection("excelPromoJc2SON");
//		TaggearComidas tc= new TaggearComidas(collection);
//		
//		tc.mostrarPropertiesDeTipoComidas();
//	}
}
