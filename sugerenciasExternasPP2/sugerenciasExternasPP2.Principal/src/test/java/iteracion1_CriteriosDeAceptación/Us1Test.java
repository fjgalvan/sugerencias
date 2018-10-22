package iteracion1_CriteriosDeAceptación;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import promo.Twitter.PromoTwitter;
import conexiones.conexionExel.ExcelParserJSON;
import conexiones.conexionExel.LeerFicherosExcel;
import conexiones.conexionTwitter.UsoTwitterDeUsuario;
import dao.mongoDB.MyConstants;
import sugerencias.ConvertirString_a_Sugerencia;
import sugerencias.SugerenciaTwitter;
import validaciones.ValidarTwitter;

public class Us1Test {
	UsoTwitterDeUsuario t= new UsoTwitterDeUsuario();
	List<String> listaTweets = new ArrayList<String>();
	String s= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/50.0,helado/40.0)_20-09-2018";
	String sInvalid= "#promos:mcDonalds_sanIsidro_lista(hamburguesa/50.0,papas/40.0)_20-09-2018";
	boolean res= false;
	PromoTwitter pt;
	
	@Test
	public void test1(){
		System.out.println("test1!");
		listaTweets.add(s);
		ValidarTwitter vt = new ValidarTwitter(s);
		res= vt.twitterStringValido();
		mostrarTweetJSON(s);
		assertTrue(res);
	}
	
	@Test
	public void test2(){
		System.out.println("\ntest2!");
		String ej= ExcelParserJSON.ExcelJSONformat();
		System.out.println(ej);
		assertFalse(ej.isEmpty());
	}
	
	@Test
	public void test3(){
		System.out.println("test3!");
		listaTweets.add(sInvalid);
		ValidarTwitter vt = new ValidarTwitter(sInvalid);
		res= vt.twitterStringValido();
		System.out.println(res);
		if(res) mostrarTweetJSON(sInvalid);
		assertFalse(res);
	}
	
	@Test
	public void test4(){
		System.out.println("test4!");
		boolean l=LeerFicherosExcel.validarExcel(MyConstants.input_excel_invalido);
		System.out.println(l);
		assertFalse(l);
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
		pt= new PromoTwitter();
		List<SugerenciaTwitter> listaSugerencia = new ArrayList<SugerenciaTwitter>();
		listaSugerencia.add(cs.getSugerenciaTwitter());
		pt.parsear_a_JSON(listaSugerencia);
	}
}
