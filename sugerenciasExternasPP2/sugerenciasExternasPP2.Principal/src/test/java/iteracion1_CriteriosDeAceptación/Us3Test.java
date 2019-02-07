package iteracion1_CriteriosDeAceptación;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import promo.Twitter.PromoTwitter;
import sugerencias.ConvertirString_a_Sugerencia;
import sugerencias.Sugerencias;
import validaciones.ValidarFechaPromo;
import validaciones.ValidarTwitter;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import dao.mongoDB.MyConstants;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;

public class Us3Test {

	String sComidaValida= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/40.0,ensalada/60.0,helado/30.0)_20-12-2019";
	PromoTwitter pt = null;
	boolean res=false;
	
	private MongoClient client;
    private MongoServer server;
    private static DB db; 
	private static DBCollection collection;

	@Before
    public void setUp() {
        server = new MongoServer(new MemoryBackend());

        // bind on a random local port
        InetSocketAddress serverAddress = server.bind();

        client = new MongoClient(new ServerAddress(serverAddress));
        //collection = client.getDatabase("testdb").getCollection("testcollection");
        db = client.getDB(MyConstants.DB_NAME);
        collection= db.getCollection(MyConstants.DB_NAME);
        
        collection= mostrarListProdDeTwitter(sComidaValida);
		System.out.println("cantCollection: "+cantidadColeccion());
    }

    @After
    public void tearDown() {
        client.close();
        server.shutdown();
    }
	
	
	@Test
	public void test1(){
		System.out.println("test1!");
		
		res= buscarConFiltro("sanas", "ensalada");
		System.out.println("res1:"+res);
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
			pt.parsear_a_JSON(l, collection);
		}
		return pt.getCollection();
	}
	
	public long cantidadColeccion(){
		long rowCount= collection.count();
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
