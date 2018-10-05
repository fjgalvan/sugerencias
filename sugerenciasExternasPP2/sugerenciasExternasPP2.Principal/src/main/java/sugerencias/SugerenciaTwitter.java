package sugerencias;

import interfaces.InterfacePromo;

import java.io.IOException; 
import java.util.HashMap;
import java.util.List;

import promosEnJson.PromoTwitter;
import conecciones.ConectorTwitterJ;
import conecciones.UsoTwitterDeUsuario;
import twitter4j.TwitterException;
import util.Date;

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
    public void conectarConTwitterPrimeraVez(){
    	ConectorTwitterJ c= new ConectorTwitterJ();
    	try { c.conectarConUsuarioDeTwitter();
		} catch (IOException | TwitterException e) {e.printStackTrace();}
    }
    public void usarTwitterDeUsuario(){
    	UsoTwitterDeUsuario t= new UsoTwitterDeUsuario();
    	try { t.conexionConTwitterDeUsuario();
		} catch (TwitterException e) { e.printStackTrace();}
    }
}
