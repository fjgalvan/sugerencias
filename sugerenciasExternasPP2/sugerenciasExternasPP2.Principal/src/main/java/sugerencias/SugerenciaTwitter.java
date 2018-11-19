package sugerencias;

import java.io.IOException;  
import java.util.HashMap;
import java.util.List;

import promo.Interfaz.InterfacePromo;
import promo.Twitter.PromoTwitter;
import conexiones.conexionTwitter.ConectorTwitter;
import twitter4j.TwitterException;
import util_.Date;

public class SugerenciaTwitter extends Sugerencias{
	
    
    public SugerenciaTwitter(String local, String ubicacion, HashMap<String,Double> listaProductos, Date fechaDeVigencia) 
    {
    	super(local, ubicacion, listaProductos, fechaDeVigencia);
    }
    
    public void leerSugerencias(){
    	leerTweets();
	}
    public void conexionConTwitter(){
    	usarTwitterDeUsuario();
    }
    public List<String> leerTweets(){
    	PromoTwitter pw= new PromoTwitter(); 
    	return pw.getListTweets();
    }
    
    public void usarTwitterDeUsuario(){
    	ConectorTwitter t= new ConectorTwitter();
    	try { t.conexionConTwitterDeUsuario();
		} catch (TwitterException e) { e.printStackTrace();}
    }
}
