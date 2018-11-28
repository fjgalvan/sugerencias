package conexiones;

import java.text.ParseException;

import org.junit.Test;

import promo.Twitter.PromoTwitter;
import sugerencias.ConvertirString_a_Sugerencia;
import sugerencias.Sugerencias;
import conexiones.conexionTwitter.ConectorTwitter;
import dao.mongoDB.MongoConcreteStub;

public class ConectorTwittweTest {
	@Test
    public void conectorTwitterTest() throws ParseException{
		ConectorTwitter ct= new ConectorTwitter();
		ct.conectarse();
		MongoConcreteStub mongo= new MongoConcreteStub();
		ct.getPromo(mongo.getPromos());
		ct.conectarsePruebaTwitter();
		String s= "#promo:Terrabusi_Martinez_lista(hamburguesa/120.0,flan/55.0,sopa/30.0,canelones/30.0)_10-12-2018";
		PromoTwitter pt= new PromoTwitter();
		pt.mostrarListProdDeTwitter(s, mongo.getPromos());
		ConvertirString_a_Sugerencia cs = new ConvertirString_a_Sugerencia(s);
		cs.convertirLocal();
		cs.convertirUbicacion();
		cs.convertirLista();
		cs.convertirFecha();
		ConectorTwitter.ParsearObjetoA_JSON(cs.getSugerenciaTwitter());
	}

}
