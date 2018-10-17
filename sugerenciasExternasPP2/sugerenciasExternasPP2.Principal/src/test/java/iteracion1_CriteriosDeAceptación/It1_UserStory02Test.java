package iteracion1_CriteriosDeAceptación;

import static org.junit.Assert.assertTrue; 

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.omg.CORBA.portable.UnknownException;

import promo.Twitter.PromoTwitter;
import sugerencias.ConvertirString_a_Sugerencia;
import sugerencias.SugerenciaTwitter;
import validaciones.ValidarTwitter;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import conexiones.conexionTwitter.UsoTwitterDeUsuario;
import dao.mongoDB.MongoUtils;
import dao.mongoDB.MyConstants;

public class It1_UserStory02Test {
	UsoTwitterDeUsuario t= new UsoTwitterDeUsuario();
	List<String> listaTweets = new ArrayList<String>();
	String s= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/50.0,papas/40.0)_20-09-2018";
	String sInvalid= "#promos:mcDonalds_sanIsidro_lista(hamburguesa/50.0,papas/40.0)_20-09-2018";
	boolean res= false;
//	
//	@Test
//	public void test1(){
//		boolean seCreo=false;
//		seCreo= MongoClass.create_DB_MongoDB();
//		assertTrue(seCreo);
//	
//	}
	
	@Test
	public void test2(){
//	Integer dia = Integer.toString(c.get(Calendar.DATE));
//	Integer mes = Integer.toString(c.get(Calendar.MONTH));
//	Integer annio = Integer.toString(c.get(Calendar.YEAR));
		
		java.util.Date fecha = new Date();
		System.out.println (fecha.getDay());
		System.out.println (fecha.getMonth());	
		System.out.println (fecha);
	}
	
	@Test
	public void test3(){ //PARSEA un String de un tweet a un JSON y luego a BSON
		System.out.println("test3!");
		listaTweets.add(s);
		ValidarTwitter vt = new ValidarTwitter(s);
		res= vt.twitterStringValido();
		mostrarTweetJSON();
		assertTrue(res);
		
		try{
			//MongoClient mongoClient= new MongoClient("localhost", 27017);
			 // To connect to mongodb server
		    MongoClient mongoClient = null;
			try {
				mongoClient = MongoUtils.getMongoClient();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			DB db = mongoClient.getDB(MyConstants.DB_NAME);
			DBCollection dept= db.getCollection("TEST3");
			
			DBObject dbObject = (DBObject)JSON.parse(mostrarTweetJSON());

			dept.insert(dbObject);
			
			DBCursor cursor= dept.find();
			while(cursor.hasNext()){
				int i=1;
				System.out.println(cursor.next());
				i++;
			}   
			System.out.println("Conexion establecida satisfactoriamente!");
		}
		catch(UnknownException e){
			System.out.println("no se pudo conectar!");
		}
		
	}
	
	public String mostrarTweetJSON(){
		String JSON="";
		ConvertirString_a_Sugerencia cs= new ConvertirString_a_Sugerencia(s);
		cs.convertirLocal();
		cs.convertirUbicacion();
		cs.convertirLista();
		cs.convertirFecha();
		SugerenciaTwitter st= new SugerenciaTwitter(cs.getSugerenciaTwitter().getLocal(),
				cs.getSugerenciaTwitter().getUbicacion(), cs.getSugerenciaTwitter().getListaProductosPrecios(),
				cs.getSugerenciaTwitter().getFechaDeVigencia() );
		PromoTwitter pt= new PromoTwitter();
		List<SugerenciaTwitter> listaSugerencia = new ArrayList<SugerenciaTwitter>();
		listaSugerencia.add(cs.getSugerenciaTwitter());
		JSON= parsear_a_JSON(listaSugerencia);
		return JSON;
		
	}
	//Parseo los objetos de tipo SugerenciaTwitter a JSON
		public String parsear_a_JSON(List<SugerenciaTwitter> listaTweets_promoComida){
			String representacionJSON=""; 
			for(int i=0; i< listaTweets_promoComida.size(); i++){
				Gson gson = new Gson();
		    	representacionJSON = gson.toJson(listaTweets_promoComida.get(i));
		    	System.out.println("\n\n"+representacionJSON);
			}
			return representacionJSON;
		}
}
