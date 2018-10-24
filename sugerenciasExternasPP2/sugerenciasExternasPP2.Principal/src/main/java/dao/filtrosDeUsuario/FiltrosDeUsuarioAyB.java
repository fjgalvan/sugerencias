package dao.filtrosDeUsuario;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.bson.Document;

import promo.Twitter.PromoTwitter;
import properties.Constants;
import sugerencias.ConvertirString_a_Sugerencia;
import sugerencias.Sugerencias;
import validaciones.ValidarFechaPromo;
import validaciones.ValidarTwitter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;

public class FiltrosDeUsuarioAyB {
	PromoTwitter pt = null;
	Properties p1;
	Properties p2;
	Properties p3;
	DBObject fa;
	DBObject fb;
	
	public void FiltrosDeUsuarioAyB(){
		p3 = new Properties();
		try { p1.load(new FileReader(Constants.ROUTE_COMIDAS_PROPERTIES));
		} catch (IOException e) { e.printStackTrace();}
	}
	
	public void leerProperties(){
		p1 = new Properties();
		try { p1.load(new FileReader(Constants.ROUTE_PREFERENCIAS_USUARIO_A));
		} catch (IOException e) { e.printStackTrace();}
		System.out.println("uno="+p1.getProperty("comida1"));
		System.out.println("uno="+p1.getProperty("comida2"));
		
		p2 = new Properties();
		try { p2.load(new FileReader(Constants.ROUTE_PREFERENCIAS_USUARIO_B));
		} catch (IOException e) { e.printStackTrace();}
		System.out.println("dos="+p2.getProperty("comida1"));
		System.out.println("uno="+p2.getProperty("comida2"));
	}
	
	
	public DBCollection mostrarListProdDeTwitter(String s, DBCollection coll){
		
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
			pt.parsear_a_JSON(l, coll);//pt.getCollection());
		}
		return pt.getCollection();
	}
	
	public boolean buscarPreferenciasUsuarioConFiltro(Properties p, String tipoComida1, String tipoComida2,String comida1, String comida2 ){
		System.out.println("buscarPreferenciasUsuarioConFiltro()");
		fa=null;
		DBObject f2=null;
		fb=null;
		DBObject f4=null;
		DBCollection col = pt.getCollection();
		
		BasicDBObject filtro = new BasicDBObject();
		filtro.put("lComidas", p.getProperty("comida1"));
		DBCursor cur = col.find(filtro);
		while (cur.hasNext()){
		  fa=cur.next();
		}
		
		BasicDBObject filtro2 = new BasicDBObject();
		filtro2.put("producto", comida1);
		DBCursor cur2 = col.find(filtro2);
		while (cur2.hasNext()){
		  f2=cur2.next();
		}
		
		BasicDBObject filtro3 = new BasicDBObject();
		filtro3.put("lComidas", p.getProperty("comida2"));
		DBCursor cur3 = col.find(filtro3);
		while (cur3.hasNext()){
		  fb=cur3.next();
		}
		
		BasicDBObject filtro4 = new BasicDBObject();
		filtro4.put("producto", comida2);
		DBCursor cur4 = col.find(filtro4);
		while (cur4.hasNext()){
		  f4=cur4.next();
		}
	
		if((fa==null) || (f2==null) || (fb==null) || (f4==null)){
			return false;
		}
		if(fa.equals(f2) && (fb.equals(f4))){
			return true;
		}else{
			return false;
		}
	}
	
	


	public boolean buscarPreferenciasUsuarioConFiltro2(Properties p, String tipoComida1, String producto1){
		DBObject f1=null;
		DBObject f2=null;
		DBCollection col = pt.getCollection();
		
		BasicDBObject filtro = new BasicDBObject();
		filtro.put("lComidas", p.getProperty("comida1"));
		DBCursor cur = col.find(filtro);
		while (cur.hasNext()){
		  f1=cur.next();
		}
		
		BasicDBObject filtro2 = new BasicDBObject();
		filtro2.put("producto", producto1);
		DBCursor cur2 = col.find(filtro2);
		while (cur2.hasNext()){
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
	

	public Properties getP1() {
		return p1;
	}

	public Properties getP2() {
		return p2;
	}
	public DBObject getFa() {
		return fa;
	}

	public DBObject getFb() {
		return fb;
	}
	
}
