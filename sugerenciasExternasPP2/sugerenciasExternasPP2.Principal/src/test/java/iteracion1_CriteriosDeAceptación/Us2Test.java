package iteracion1_CriteriosDeAceptación;

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import promo.Twitter.PromoTwitter;
import com.mongodb.DBCollection;
import dao.mongoDB.MongoConcreteStub;
import sugerencias.ConvertirString_a_Sugerencia;
import sugerencias.SugerenciaTwitter;
import sugerencias.Sugerencias;
import validaciones.ValidarFechaPromo;
import validaciones.ValidarTwitter;

public class Us2Test {
	List<String> listaTweets = new ArrayList<String>();
	String sFechaValida= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/50.0,helado/40.0)_20-12-2018";
	String sInvalid= "#promos:mcDonalds_sanIsidro_lista(hamburguesa/50.0,papas/40.0)_20-09-2018";
	String sComidaInvalida= "#promo:mcDonalds_sanIsidro_lista(cerveza/40.0)_20-12-2018";
	String sComidaValida= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/40.0)_20-12-2018";
	boolean res= false;
	boolean resDate= false;
	PromoTwitter pt = null;

	private static DBCollection collection;
	
	MongoConcreteStub ima;

	@Before
    public void setUp() {
		ima= new MongoConcreteStub();
		ima.init();
    }

    @After
    public void tearDown() {
    	ima.finish();
    }
	
	
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
	}
	
	//Si se obtiene información de comida de cerveza, no se guarda esa promo debido a que no es un tipo de comida a considerar. 
	//Estas son comidas: chatarras, sanas, pastas y postres
	@Test
	public void test3(){
		System.out.println("test3!");
		this.collection= ima.leerColeccion();
		mostrarListProdDeTwitter(sComidaInvalida);
		cantidadColeecion(sComidaInvalida);
		
		assertEquals(0, cantidadColeecion(sComidaInvalida));
	}
	
	@Test
	public void test4(){
		System.out.println("test4!");
		this.collection= ima.leerColeccion();
		mostrarListProdDeTwitter(sComidaValida);
		cantidadColeecion(sComidaValida);
		
		assertEquals(1, cantidadColeecion(sComidaValida));

	}
	
	
	

/************AUX***************/	
	@SuppressWarnings("unused")
	public void mostrarTweetJSON(String t){ 
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
			pt.parsear_a_JSON(l, collection);
		}
		return pt.getCollection();
	}
	
	public long cantidadColeecion(String s){
		
//		long rowCount = mostrarListProdDeTwitter(s).count();
//		System.out.println(" Document count: "+ rowCount);
//		// List of Collections
//		Set<String> collections = pt.getDb().getCollectionNames(); 
//	    for(String coll: collections)  {
//	        System.out.println("Collection: "+ coll);
//	    }
		long rowCount= collection.count();
	    return rowCount;
	}
	
}
