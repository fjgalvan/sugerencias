package promosEnJson;

import interfaces.InterfacePromo;

import java.util.ArrayList; 
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import sugerencias.Sugerencias;
import sugerencias.SugerenciaTwitter;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

import conecciones.UsoTwitterDeUsuario;
import dao.TaggearComidas;

public class PromoTwitter implements InterfacePromo{
	List<String> listaTweets = new ArrayList<String>();
	List<SugerenciaTwitter> listaTweets_promoComida = new ArrayList<SugerenciaTwitter>();
	DBCollection collection;
	Mongo mongo;
	DB db;
	
	public PromoTwitter(){
		
	}

	//Leo y guardo todos los tweets en tipo lista de String
	public List<String> getListTweets(){
		ResponseList<Status> rl = null;
		UsoTwitterDeUsuario ut= new UsoTwitterDeUsuario();
		try {ut.conexionConTwitterDeUsuario();
		} catch (TwitterException e) {e.printStackTrace();}
		try {rl= ut.RecuperarListadoDeUltimosTweetsEscritos();
		} catch (TwitterException e) {e.printStackTrace();}
		for(int i=0; i <rl.size(); i++){
			listaTweets.add(rl.get(i).getText());}		
		return listaTweets;	
	}
	
	
	//Parseo los objetos de tipo SugerenciaTwitter a JSON
	public void parsear_a_JSON(List<SugerenciaTwitter> listaTweets_promoComida){
		for(int i=0; i< listaTweets_promoComida.size(); i++){
			Gson gson = new Gson();
	    	String representacionJSON = gson.toJson(listaTweets_promoComida.get(i));
	    	System.out.println("\n\n"+representacionJSON);
		}
		
	}
	
	public void parsear_a_JSON(ArrayList<Sugerencias> l) {
		for(int i=0; i< l.size(); i++){
			Gson gson = new Gson();
	    	String representacionJSON = gson.toJson(l.get(i));
	    	System.out.println("\n\n"+representacionJSON);
	    	parsearBSON(representacionJSON);
		}
		
	}
	public DBCollection parsearBSON(String s){
		HashMap<String,Double> l= new HashMap<String,Double>();
		//DBCollection collection = null;
		try {

			mongo = new Mongo("localhost", 27017);
			db = mongo.getDB("yourdb12");
			collection = db.getCollection("excelPromoJc1SON");
			
			// convert JSON to DBObject directly
			DBObject dbObject = (DBObject) JSON
					.parse(s);

			collection.insert(dbObject);
			
			//ACTUALIZO EL BSON
			BasicDBObject newDocument = new BasicDBObject();
			newDocument.append("$set", new BasicDBObject().append("listaProductosPrecios", null));//("listaProductosPrecios", l));
			BasicDBObject searchQuery = new BasicDBObject().append("listaProductosPrecios", l);
			collection.update(searchQuery, newDocument);
			
			//TAGGEO PROMOS DE COMIDAS
			TaggearComidas tc= new TaggearComidas(collection);
			tc.taggearComidas();
			
			//ELIMINO PROMOS DE COMIDAS INCORRECTOS
			tc.eliminarComidasSinTaggear();
			
			//IMPRIMO BSON
			DBCursor cursorDoc3 = tc.getCollection().find();//collection.find();
			while (cursorDoc3.hasNext()) {
				System.out.println(cursorDoc3.next());
			}
		} catch (MongoException e) {
			e.printStackTrace();
		}		
		return collection;
	}

	public Mongo getMongo() {
		return mongo;
	}

	public DB getDb() {
		return db;
	}

	public DBCollection getCollection() {
		return collection;
	}

	@Override
	public DBCollection getPromo() {
		// TODO Auto-generated method stub
		return getCollection();
	}

}
