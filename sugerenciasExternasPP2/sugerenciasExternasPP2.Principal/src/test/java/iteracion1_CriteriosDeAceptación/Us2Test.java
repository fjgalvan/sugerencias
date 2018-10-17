package iteracion1_CriteriosDeAceptación;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import promo.Twitter.PromoTwitter;

import com.mongodb.DBCollection;

import dao.mongoDB.MongoUtils;
import sugerencias.ConvertirString_a_Sugerencia;
import sugerencias.SugerenciaTwitter;
import sugerencias.Sugerencias;
import validaciones.ValidarFechaPromo;
import validaciones.ValidarTwitter;

public class Us2Test {
	List<String> listaTweets = new ArrayList<String>();
	String sFechaValida= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/50.0,helado/40.0)_20-10-2018";
	String sInvalid= "#promos:mcDonalds_sanIsidro_lista(hamburguesa/50.0,papas/40.0)_20-09-2018";
	String sComidaInvalida= "#promo:mcDonalds_sanIsidro_lista(cerveza/40.0)_20-10-2018";
	String sComidaValida= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/40.0)_20-10-2018";
	boolean res= false;
	boolean resDate= false;
	PromoTwitter pt = null;
	

	
	
	@Test
	public void test1(){
		System.out.println("test1!");
		listaTweets.add(sInvalid);
		ValidarTwitter vt = new ValidarTwitter(sInvalid);
		res= vt.twitterStringValido();
		mostrarTweetJSON(sInvalid);
		ValidarFechaPromo vf= new ValidarFechaPromo(sInvalid);
		resDate=vf.VigenciaPromoValida();
		System.out.println("resDate: "+resDate);
		
		assertFalse(resDate);
		
		try {pt.getCollection().drop();
		} catch (Exception e) { }
	}
	
	@Test
	public void test2(){
		System.out.println("\ntest2!");
		listaTweets.add(sFechaValida);
		ValidarTwitter vt = new ValidarTwitter(sFechaValida);
		res= vt.twitterStringValido();
		mostrarTweetJSON(sFechaValida);
		ValidarFechaPromo vf= new ValidarFechaPromo(sFechaValida);
		resDate=vf.VigenciaPromoValida();
		System.out.println("resDate: "+resDate);
		
		assertTrue(resDate);
		try {pt.getCollection().drop();
		} catch (Exception e) { }
	}
	
	@Test
	public void test3(){
		System.out.println("test3!");
		mostrarListProdDeTwitter(sComidaInvalida);
		cantidadColeecion(sComidaInvalida);
		
		assertEquals(0, cantidadColeecion(sComidaInvalida));
		try {pt.getCollection().drop();
		} catch (Exception e) { }
	}
	
	@Test
	public void test4(){
		System.out.println("test4!");
		mostrarListProdDeTwitter(sComidaValida);
		cantidadColeecion(sComidaValida);
		
		assertEquals(1, cantidadColeecion(sComidaValida));
		try {pt.getCollection().drop();
		} catch (Exception e) { }
	}
	
	
	

/************AUX***************/	
	@SuppressWarnings("unused")
	public void mostrarTweetJSON(String t){
		try {pt.getCollection().drop();
		} catch (Exception e) { }
		ConvertirString_a_Sugerencia cs= new ConvertirString_a_Sugerencia(t);//s
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
		pt.parsear_a_JSON(listaSugerencia);
	}
	
	public DBCollection mostrarListProdDeTwitter(String s){
		try {pt.getCollection().drop();
		} catch (Exception e) { }
		ArrayList<Sugerencias> l;
		System.out.println("mostrarListProdDeTwitter !");
		ValidarTwitter vt = new ValidarTwitter(s);
		ValidarFechaPromo vf= new ValidarFechaPromo(s);
		System.out.println("vt.twitterStringValido(): "+vt.twitterStringValido());
		System.out.println("vf.VigenciaPromoValida(): "+vf.VigenciaPromoValida());
		if((vt.twitterStringValido()) && (vf.VigenciaPromoValida()) ){
			ConvertirString_a_Sugerencia cs= new ConvertirString_a_Sugerencia(s);//s
			cs.convertirLocal();
			cs.convertirUbicacion();
			cs.convertirLista();
			cs.convertirFecha();
			//cs.getSugerenciaTwitter();
			l = new ArrayList<Sugerencias>();
			l= cs.getListSugerenciaTwitter();
			
			// Declaramos el Iterador e imprimimos los Elementos del ArrayList
			Iterator<Sugerencias> nombreIterator = l.iterator();
			while(nombreIterator.hasNext()){
				Sugerencias elemento = nombreIterator.next();
				System.out.print(elemento.getProducto()+" / "+elemento.getPrecio()+"\n");
			}
			
			//PARSEO A JSON y A BSON
			pt= new PromoTwitter();
			pt.parsear_a_JSON(l);
		}
		return pt.getCollection();
	}
	
	public long cantidadColeecion(String s){
		
		long rowCount = mostrarListProdDeTwitter(s).count();
		System.out.println(" Document count: "+ rowCount);
		// List of Collections
		Set<String> collections = pt.getDb().getCollectionNames(); 
	    for(String coll: collections)  {
	        System.out.println("Collection: "+ coll);
	    }
	    return rowCount;
	}
	
}
