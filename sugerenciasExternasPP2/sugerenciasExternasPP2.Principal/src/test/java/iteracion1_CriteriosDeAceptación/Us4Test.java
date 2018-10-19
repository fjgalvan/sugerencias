package iteracion1_CriteriosDeAceptación;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import dao.filtrosDeUsuario.FiltrosDeUsuarioAyB;
import dao.mongoDB.MyConstants;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import promo.Twitter.PromoTwitter;
import promo.Twitter.TwitterParsers;
import properties.Constants;

public class Us4Test {
	String s= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/50.0,helado/40.0,ensalada/20.0,fideos/30.0)_20-11-2018";
	PromoTwitter pt = null;
	boolean res= false;
	FiltrosDeUsuarioAyB f;
	Properties p1;
	Properties p2;
	TwitterParsers tp;
	
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
        
        tp= new TwitterParsers(collection);
        
        System.out.println("init");
		f= new FiltrosDeUsuarioAyB();
		f.mostrarListProdDeTwitter(s);
		p1 = new Properties();
		try { p1.load(new FileReader(Constants.ROUTE_PREFERENCIAS_USUARIO_A));
		} catch (IOException e) { e.printStackTrace();}
		
		p2 = new Properties();
		try { p2.load(new FileReader(Constants.ROUTE_PREFERENCIAS_USUARIO_B));
		} catch (IOException e) { e.printStackTrace();}
		
		collection= tp.mostrarListProdDeTwitter(s);
    }

    @After
    public void tearDown() {
        client.close();
        server.shutdown();
    }
	
	@Test
	public void test1(){
		System.out.println("test1!");
		
		res= f.buscarPreferenciasUsuarioConFiltro(p1, "chatarras", "postres", "hamburguesa", "helado");
		assertTrue(res);
		
	}
	
	@Test
	public void test2(){
		System.out.println("test2!");
		
		res= f.buscarPreferenciasUsuarioConFiltro(p2, "sanas", "postres", "ensalada", "helado");
		assertTrue(res);
		
	}
	
	@Test
	public void test3(){
		System.out.println("test3!");
		
		res= f.buscarPreferenciasUsuarioConFiltro2(p1, "pastas", "fideos");
		assertFalse(res);
	}
	
	@Test
	public void test4(){
		System.out.println("test4!");
		
		res= f.buscarPreferenciasUsuarioConFiltro2(p2, "pastas", "fideos");
		assertFalse(res);
	}
	
	
	
}
