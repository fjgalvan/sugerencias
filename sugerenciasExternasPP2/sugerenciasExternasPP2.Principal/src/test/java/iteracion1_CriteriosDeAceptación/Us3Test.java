package iteracion1_CriteriosDeAceptación;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import promosEnJson.PromoTwitter;
import sugerencias.ConvertirString_a_Sugerencia;
import sugerencias.Sugerencias;
import validaciones.ValidarFechaPromo;
import validaciones.ValidarTwitter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Us3Test {

	String sComidaValida= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/40.0,ensalada/60.0,helado/30.0)_20-10-2018";
	PromoTwitter pt = null;
	boolean res=false;
	
	@Before
	public void init(){
		System.out.println("init");
		mostrarListProdDeTwitter(sComidaValida);
		cantidadColeecion(sComidaValida);
	}
	
	@Test
	public void test1(){
		System.out.println("test1!");
		
		res= buscarConFiltro("sanas", "ensalada");
		assertTrue(res);
		try {pt.getCollection().drop();
		} catch (Exception e) { }
	}
	
	@Test
	public void test2(){
		System.out.println("test2!");
		
		res= buscarConFiltro("chatarras", "hamburguesa");
		assertTrue(res);
		try {pt.getCollection().drop();
		} catch (Exception e) { }
	}
	
	@Test
	public void test3(){
		System.out.println("test3!");
		
		res= buscarConFiltro("postres", "helado");
		assertTrue(res);
		try {pt.getCollection().drop();
		} catch (Exception e) { }
	}
	
	@Test
	public void test4(){
		System.out.println("test4!");
		
		res=buscarConFiltro("bebidas", "cerveza");
		assertFalse(res);
		try {pt.getCollection().drop();
		} catch (Exception e) { }
	}
	
/************AUX***************/	
	
public DBCollection mostrarListProdDeTwitter(String s){
		
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
	
	public boolean buscarConFiltro(String tipoComida, String producto){
		System.out.println("buscarConFiltro()");
		DBObject f1=null;
		DBObject f2=null;
		DBCollection col = pt.getCollection();
		
		BasicDBObject filtro = new BasicDBObject();
		filtro.put("lComidas", tipoComida);
		DBCursor cur = col.find(filtro);
		while (cur.hasNext()){
		  //System.out.println("filtro1: "+cur.next());
		  f1=cur.next();
		}
		
		BasicDBObject filtro2 = new BasicDBObject();
		filtro2.put("producto", producto);
		DBCursor cur2 = col.find(filtro2);
		while (cur2.hasNext()){
		  //System.out.println("filtro2: "+cur2.next());
		  f2=cur2.next();
		}
		if((f1==null) || (f2==null)){
			return false;
		}
		if(f1.equals(f2)){
			return true;
		}else{
			return false;
		}
	}
}
