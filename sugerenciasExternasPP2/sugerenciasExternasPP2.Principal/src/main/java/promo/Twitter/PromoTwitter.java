package promo.Twitter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import promo.Excel.PromoExcel;
import promo.Interfaz.InterfacePromo;
import sugerencias.ConvertirString_a_Sugerencia;
import sugerencias.Sugerencias;
import sugerencias.SugerenciaTwitter;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import validaciones.ValidarFechaPromo;
import validaciones.ValidarTwitter;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.util.JSON;

import conexiones.conexionTwitter.ConectorTwitter;
import configurables.MyConstantsDAO;
import dao.filtrosDeUsuario.TaggearComidas;
import dao.mongoDB.MongoConcreteStub;

public class PromoTwitter implements InterfacePromo {
	List<String> listaTweets = new ArrayList<String>();
	List<SugerenciaTwitter> listaTweets_promoComida = new ArrayList<SugerenciaTwitter>();
	DBCollection collection;
	Mongo mongo;
	DB db;

	public PromoTwitter() {

	}

	// Leo y guardo todos los tweets en tipo lista de String
	public List<String> getListTweets() {
		ResponseList<Status> rl = null;
		ConectorTwitter ut = new ConectorTwitter();
		try {
			ut.conexionConTwitterDeUsuario();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		try {
			rl = ut.RecuperarListadoDeUltimosTweetsEscritos();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < rl.size(); i++) {
			listaTweets.add(rl.get(i).getText());
		}
		//System.out.println("listaTweets: "+listaTweets);
		return listaTweets;
	}

	public DBCollection mostrarListProdDeTwitter(String s, DBCollection coll) {
		this.collection = coll;
		ArrayList<Sugerencias> l;
		ValidarTwitter vt = new ValidarTwitter(s);
		ValidarFechaPromo vf = new ValidarFechaPromo(s);

		if ((vt.twitterStringValido()) && (vf.VigenciaPromoValida())) {
			ConvertirString_a_Sugerencia cs = new ConvertirString_a_Sugerencia(
					s);// s
			cs.convertirLocal();
			cs.convertirUbicacion();
			cs.convertirLista();
			cs.convertirFecha();
			l = new ArrayList<Sugerencias>();
			l = cs.getListSugerenciaTwitter();

			// Declaramos el Iterador e imprimimos los Elementos del ArrayList
			Iterator<Sugerencias> nombreIterator = l.iterator();
			while (nombreIterator.hasNext()) {
				Sugerencias elemento = nombreIterator.next();
			}

			// PARSEO A JSON y A BSON

			parsear_a_JSON(l, collection);
		}
		return getCollection();
	}

	// Parseo los objetos de tipo SugerenciaTwitter a JSON
	public void parsear_a_JSON(List<SugerenciaTwitter> listaTweets_promoComida) {
		for (int i = 0; i < listaTweets_promoComida.size(); i++) {
			Gson gson = new Gson();
			String representacionJSON = gson.toJson(listaTweets_promoComida
					.get(i));
		}
	}

	public void parsear_a_JSON(ArrayList<Sugerencias> l,
			DBCollection collection2) {
		for (int i = 0; i < l.size(); i++) {
			Gson gson = new Gson();
			String representacionJSON = gson.toJson(l.get(i));
			parsearBSON(representacionJSON, collection2);
		}

	}

	public DBCollection parsearBSON(String s, DBCollection collection2) {
		this.collection = collection2;
		HashMap<String, Double> l = new HashMap<String, Double>();
		try {
			// convert JSON to DBObject directly
			DBObject bson = (DBObject) JSON.parse(s);
			this.collection.insert(bson);

			// ACTUALIZO EL BSON
			BasicDBObject newDocument = new BasicDBObject();
			newDocument.append("$set",
					new BasicDBObject().append("listaProductosPrecios", null));
			BasicDBObject searchQuery = new BasicDBObject().append(
					"listaProductosPrecios", l);
			this.collection.update(searchQuery, newDocument);
			// TAGGEO INICIAL
			if(this.collection.count() == 1){
				TaggearComidas tcI = new TaggearComidas(this.collection);
				this.collection = tcI.taggeoInicial(this.collection);
			}
			TaggearComidas tcI = new TaggearComidas(this.collection);
			this.collection = tcI.taggeoInicialLocal(this.collection, MyConstantsDAO.localburguerKing);
			this.collection = tcI.taggeoInicialLocal(this.collection, MyConstantsDAO.localMcDonalds);
			this.collection = tcI.taggeoInicialLocal(this.collection, MyConstantsDAO.localStarBuck);
			this.collection = tcI.taggeoInicialLocal(this.collection, MyConstantsDAO.localTearrabusi);
			// TAGGEO PROMOS DE COMIDAS
			TaggearComidas tc = new TaggearComidas(this.collection);
			this.collection = tc.taggearComidas();
			// ELIMINO PROMOS DE COMIDAS INCORRECTOS
			TaggearComidas tc2 = new TaggearComidas(this.collection);
			this.collection = tc2.eliminarComidasSinTaggear(this.collection);

		} catch (MongoException e) {
			e.printStackTrace();
		}
		return this.collection;
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
		return getCollection();
	}
}
