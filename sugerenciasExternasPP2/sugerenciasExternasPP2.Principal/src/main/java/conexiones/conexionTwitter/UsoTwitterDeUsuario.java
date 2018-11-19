package conexiones.conexionTwitter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;  
import java.util.HashMap;

import promo.Twitter.PromoTwitter;
import properties.Constants;
import properties.PropertiesPrincipal;

import com.google.gson.Gson;
import com.mongodb.DBCollection;

import conexiones.Interfaz.InterfaceConectores;
import dao.mongoDB.MongoConcreteStub;
import dao.mongoDB.MyConstants;
import sugerencias.Sugerencias;
import sugerencias.SugerenciaTwitter;
//import PP2.sugerencias.InterfazDeSugerencias;
//import PP2.sugerencias.SugerenciaTwitter;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import validaciones.ValidarTwitter;
 
public class UsoTwitterDeUsuario implements InterfaceConectores
{
	Twitter twitter;
	DBCollection promosNuevas;
	
	public  UsoTwitterDeUsuario(){
		
	}
	
    public void conexionConTwitterDeUsuario() throws TwitterException
    {
        
        ConfigurationBuilder cb = new ConfigurationBuilder();
        //PrincipalProperties p= new PrincipalProperties();
        PropertiesPrincipal pp= new PropertiesPrincipal(Constants.ROUTE_PROPERTIES);
        
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey(pp.leerValorDeUnaClave(MyConstants.ConsumerKey))
			.setOAuthConsumerSecret(pp.leerValorDeUnaClave(MyConstants.ConsumerSecret))
			.setOAuthAccessToken(pp.leerValorDeUnaClave(MyConstants.AccessToken))
			.setOAuthAccessTokenSecret(pp.leerValorDeUnaClave(MyConstants.AccessTokenSecret));
        twitter = new TwitterFactory(cb.build()).getInstance();
    }
    
    public ResponseList<Status> RecuperarListadoDeUltimosTweetsEscritos() throws TwitterException
    {
        //El paging sirve para decir el número máx de tweets que quieres recuperar
        Paging pagina = new Paging();
        pagina.setCount(50);
        
      //Recupera como máx 50 tweets escritos por tí
        ResponseList<Status> listado = twitter.getUserTimeline(pagina);
        for (int i = 0; i < listado.size(); i++)
            System.out.println(listado.get(i).getText());
        
        //Recupera como máx los ultimos 50 tweets de tus tablon de novedades
//      listado = twitter.getHomeTimeline(pagina);
//      for (int i = 0; i < listado.size()/2; i++)//Lo divido por 2 porque se duplica
//          System.out.println(listado.get(i).getText());
        
        return listado;
    }
    
    @SuppressWarnings("unused")
	public void actualizarEstadoCreandoNuevosTweets(String tweet){
    	tweet= "#promo: mcDonalds_beccar_hamburguesa_50_20-09-2018.";
        try {
			Status tweetEscrito = twitter.updateStatus(tweet);
		} catch (TwitterException e1) {
			e1.printStackTrace();
		}
    }
    
    public static void ParsearObjetoA_JSON(Sugerencias s) throws ParseException {
    	final SugerenciaTwitter st = new SugerenciaTwitter(s.getLocal(), s.getUbicacion(), s.getListaProductosPrecios(), s.getFechaDeVigencia());
    	final Gson gson = new Gson();
    	final String representacionJSON = gson.toJson(st);
    	System.out.println("\n\n"+representacionJSON);
    }
    
    public HashMap<String, String> conectarsePruebaTwitter() {
    	HashMap<String, String> map= new HashMap<String, String>();
		try {conexionConTwitterDeUsuario();
		} catch (TwitterException e) {e.printStackTrace();}
		return map;
	}

	@Override
	public void conectarse(){
		try {
			conexionConTwitterDeUsuario();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	@Override
	public DBCollection getPromo(DBCollection promosViejas) {
		promosNuevas= promosViejas;
		ResponseList<Status> listado = null;
		PromoTwitter pts= new PromoTwitter();
		try {
			listado=RecuperarListadoDeUltimosTweetsEscritos();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < listado.size(); i++){
			 System.out.println("tweet: "+listado.get(i).getText());
			 ValidarTwitter v= new ValidarTwitter(listado.get(i).getText());
			 if(v.twitterStringValido()){
				System.out.println("Tweet valido: "+listado.get(i).getText());
				promosNuevas=pts.mostrarListProdDeTwitter(listado.get(i).getText(), promosNuevas);
			 }
		 }
		return promosNuevas;//pts.getCollection();
	}
	
}
