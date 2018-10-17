package iteracion1_CriteriosDeAceptación;


import static org.junit.Assert.assertTrue;  

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import promo.Twitter.PromoTwitter;
import sugerencias.ConvertirString_a_Sugerencia;
import sugerencias.Sugerencias;
import sugerencias.SugerenciaTwitter;
import validaciones.ValidarFechaPromo;
import validaciones.ValidarTwitter;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

import conexiones.conexionTwitter.UsoTwitterDeUsuario;


public class It1_UserSoty01Test {
	UsoTwitterDeUsuario t= new UsoTwitterDeUsuario();
	List<String> listaTweets = new ArrayList<String>();
	String s= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/50.0,helado/40.0)_20-09-2018";
	String sFechaValida= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/50.0,helado/40.0)_20-10-2018";
	String sInvalid= "#promos:mcDonalds_sanIsidro_lista(hamburguesa/50.0,papas/40.0)_20-09-2018";
	String sComidaInvalida= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/50.0,helado/40.0,cerveza/40.0)_20-09-2018";
	boolean res= false;
	boolean resDate= false;
	
	@Test
	public void test1(){
		System.out.println("test1!");
		listaTweets.add(s);
		ValidarTwitter vt = new ValidarTwitter(s);
		res= vt.twitterStringValido();
		mostrarTweetJSON();
		//mostrarListProdDeTwitter();
		
		//VALIDAR FECHA
//		ValidarFechaPromo vf= new ValidarFechaPromo(sFechaValida);
//		resDate=vf.VigenciaPromoValida();
//		System.out.println("resDate: "+resDate);
		
		assertTrue(res);
		
	}
	
//	@Test
//	public void test2(){
//		System.out.println("\ntest2!");
//		String ej= ExcelParserJSON.ExcelJSONformat();
//		System.out.println(ej);
//		assertFalse(ej.isEmpty());
//	}
//	
//	@Test
//	public void test3(){
//		System.out.println("test3!");
//		listaTweets.add(sInvalid);
//		ValidarTwitter vt = new ValidarTwitter(sInvalid);
//		res= vt.twitterStringValido();
//		System.out.println(res);
//		if(res) mostrarTweetJSON();
//		assertFalse(res);
//	}
//	
//	@Test
//	public void test4(){
//		System.out.println("test4!");
//		boolean l=LeerFicherosExcel.validarExcel("src/main/resources/SugerenciasInvalida.xlsx");
//		System.out.println(l);
//		assertFalse(l);
//	}
	
	public void mostrarTweetJSON(){
		ConvertirString_a_Sugerencia cs= new ConvertirString_a_Sugerencia(sComidaInvalida);//s
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
	
	public void mostrarListProdDeTwitter(){
		System.out.println("mostrarListProdDeTwitter !");
		ValidarTwitter vt = new ValidarTwitter(sComidaInvalida);
		ValidarFechaPromo vf= new ValidarFechaPromo(sComidaInvalida);
		System.out.println("vt.twitterStringValido(): "+vt.twitterStringValido());
		System.out.println("vf.VigenciaPromoValida(): "+vf.VigenciaPromoValida());
		if((vt.twitterStringValido()) && (vf.VigenciaPromoValida()) ){
			ConvertirString_a_Sugerencia cs= new ConvertirString_a_Sugerencia(sComidaInvalida);//s
			cs.convertirLocal();
			cs.convertirUbicacion();
			cs.convertirLista();
			cs.convertirFecha();
			//cs.getSugerenciaTwitter();
			ArrayList<Sugerencias> l = new ArrayList<Sugerencias>();
			l= cs.getListSugerenciaTwitter();
			
			// Declaramos el Iterador e imprimimos los Elementos del ArrayList
			Iterator<Sugerencias> nombreIterator = l.iterator();
			while(nombreIterator.hasNext()){
				Sugerencias elemento = nombreIterator.next();
				System.out.print(elemento.getProducto()+" / "+elemento.getPrecio()+"\n");
			}
			
			//PARSEO A JSON y A BSON
			PromoTwitter pt= new PromoTwitter();
			pt.parsear_a_JSON(l);
		}
	}
}
