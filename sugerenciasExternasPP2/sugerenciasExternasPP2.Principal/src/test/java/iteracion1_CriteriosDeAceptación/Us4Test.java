package iteracion1_CriteriosDeAceptación;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import modelo.Customer;
import modelo.Preferencias;
import modelo.Recomendacion;
import modelo.Usuario;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import dao.filtrosDeUsuario.FiltrosDeUsuarioAyB;
import dao.mongoDB.MongoConcreteStub;
import dao.mongoDB.MyConstants;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import promo.Twitter.PromoTwitter;
import promo.Twitter.TwitterParsers;
import properties.Constants;

public class Us4Test {
	String s= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/50.0,helado/40.0,ensalada/20.0,fideos/30.0)_31-12-2018";
	PromoTwitter pt = null;
	boolean res= false;
	//FiltrosDeUsuarioAyB f;
	Recomendacion f_a;
	Recomendacion f_b;
	Properties p1;
	Properties p2;
	TwitterParsers tp;
	
//	private MongoClient client;
//    private MongoServer server;
//    private static DB db; 
	private DBCollection collection;
	private MongoConcreteStub mongo;

	@Before
    public void setUp() {
//        server = new MongoServer(new MemoryBackend());
//
//        // bind on a random local port
//        InetSocketAddress serverAddress = server.bind();
//
//        client = new MongoClient(new ServerAddress(serverAddress));
//        //collection = client.getDatabase("testdb").getCollection("testcollection");
//        db = client.getDB(MyConstants.DB_NAME);
//        collection= db.getCollection(MyConstants.DB_NAME);
//        
//        tp= new TwitterParsers(collection);
//        
//        System.out.println("init");
		//f= new FiltrosDeUsuarioAyB();
		//f.mostrarListProdDeTwitter(s, collection);
		System.out.println("Creo el Mongo");
		mongo= new MongoConcreteStub();
		
		System.out.println("Cargo Properties a y b");
		p1 = new Properties();
		try { p1.load(new FileReader(Constants.ROUTE_PREFERENCIAS_USUARIO_A));
		} catch (IOException e) { e.printStackTrace();}
		
		p2 = new Properties();
		try { p2.load(new FileReader(Constants.ROUTE_PREFERENCIAS_USUARIO_B));
		} catch (IOException e) { e.printStackTrace();}
		
		System.out.println("cargo customers a y b");
		Usuario a = new Usuario("a", "a@yahoo.com.ar");
		Preferencias pa1 = new Preferencias(1, p1.getProperty("comida1"));
		Preferencias pa2 = new Preferencias(2, p1.getProperty("comida2"));
		ArrayList<Preferencias> listaPreferencias = new ArrayList<Preferencias>();
		listaPreferencias.add(pa1);
		listaPreferencias.add(pa2);
		Customer ca = new Customer("1", a, listaPreferencias);
		f_a= new Recomendacion(ca);
		
		
		Usuario b = new Usuario("b", "b@yahoo.com.ar");
		Preferencias pb1 = new Preferencias(1, p2.getProperty("comida1"));
		Preferencias pb2 = new Preferencias(2, p2.getProperty("comida2"));
		ArrayList<Preferencias> listaPreferenciasb = new ArrayList<Preferencias>();
		listaPreferenciasb.add(pb1);
		listaPreferenciasb.add(pb2);
		Customer cb = new Customer("2", b, listaPreferenciasb);
		f_b= new Recomendacion(cb);
		
		collection= mongo.getPromos();
//		collection= tp.mostrarListProdDeTwitter(s);
//		mongo.setColl(collection);
		
    }

    @After
    public void tearDown() {
        mongo.finish();
    }
	
    //Si se busca preferencias de usuario A en A9, debe devolver verdadero la elección de comidas “chatarras”
    //y “postres” y los productos "hamburguesa" y "helado" . 
	@Test
	public void test1(){
		System.out.println("test1!");
		collection= f_a.mostrarListProdDeTwitter(s, mongo.getPromos());
		f_a.leerPreferencias();
		f_a.mostrarRecomendaciones(collection);
		res= f_a.buscarPreferenciasUsuarioConFiltro();
		assertTrue(res);
		
	}
	//Si se busca preferencias de usuario B en A9, debe devolver verdadero la elección de comidas
	//“sanas” y “postres” y los productos "ensalada" y "helado"
	@Test
	public void test2(){
		System.out.println("test2!");
		collection= f_b.mostrarListProdDeTwitter(s, mongo.getPromos());
		f_b.leerPreferencias();
		f_b.mostrarRecomendaciones(collection);
		res= f_b.buscarPreferenciasUsuarioConFiltro();
		assertTrue(res);
		
	}
	//Si se busca comida de tipo pastas para el usuario A, debe devolver vacío
	//debido a que no es una preferencia de ninguno de los usuarios.
	@Test
	public void test3(){
		System.out.println("test3!");
		
		List<Preferencias> lista= new ArrayList<Preferencias>();
		collection= f_a.mostrarListProdDeTwitter(s, mongo.getPromos());
		Preferencias paPastas = new Preferencias(4, "pastas");
		
		lista= f_a.leerPreferencias();
		assertFalse(lista.contains(paPastas));
	}
	//Si se busca comida de tipo pastas para el usuario B, debe devolver 
	//vacío debido a que no es una preferencia de ninguno de los usuarios.
	@Test
	public void test4(){
		System.out.println("test4!");
		
		List<Preferencias> lista= new ArrayList<Preferencias>();
		collection= f_b.mostrarListProdDeTwitter(s, mongo.getPromos());
		Preferencias paPastas = new Preferencias(4, "pastas");
		
		lista= f_b.leerPreferencias();
		assertFalse(lista.contains(paPastas));
	}
	
	
	
}
