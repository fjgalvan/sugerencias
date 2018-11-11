package promo.Twitter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sugerencias.ConvertirString_a_Sugerencia;
import sugerencias.SugerenciaTwitter;
import sugerencias.Sugerencias;
import validaciones.ValidarFechaPromo;
import validaciones.ValidarTwitter;

import com.mongodb.DBCollection;

public class TwitterParsers {
	PromoTwitter pt = null;
	DBCollection collection;
	public TwitterParsers(DBCollection collection){
		this.collection= collection;
	}
	
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
		ValidarTwitter vt = new ValidarTwitter(s);
		ValidarFechaPromo vf= new ValidarFechaPromo(s);
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
			}
			
			//PARSEO A JSON y A BSON
			pt= new PromoTwitter();
			pt.parsear_a_JSON(l, collection);
		}
		return pt.getCollection();
	}
	
	public long cantidadColeecion(String s){

		long rowCount= collection.count();
	    return rowCount;
	}
}
