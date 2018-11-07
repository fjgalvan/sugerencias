package promo.Twitter;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import dao.mongoDB.MyConstants;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import promo.Interfaz.InterfacePromo;
import sugerencias.ConvertirString_a_Sugerencia;
import sugerencias.Sugerencias;
import validaciones.ValidarFechaPromo;
import validaciones.ValidarTwitter;

public class PromoTwitterStub implements InterfacePromo{

	String s= "#promo:mcDonalds_sanIsidro_lista(hamburguesa/50.0,helado/40.0,ensalada/20.0,fideos/30.0)_20-12-2018";
	PromoTwitter pt = null;
	boolean res=false;
	
	private MongoClient client;
    private MongoServer server;
    private static DB db; 
	private static DBCollection collection;

	
	@Override
	public DBCollection getPromo() {
		// TODO Auto-generated method stub
		return collection;
	}
	public DBCollection conectarseMongoDBstub(String nombreBase, String nombreColeccion) {
		return setUp();
	}
	
    @SuppressWarnings("deprecation")
	public DBCollection setUp() {
        server = new MongoServer(new MemoryBackend());

        // bind on a random local port
        InetSocketAddress serverAddress = server.bind();

        client = new MongoClient(new ServerAddress(serverAddress));
        db = client.getDB(MyConstants.DB_NAME);
        collection= db.getCollection(MyConstants.DB_NAME);
        
        collection= mostrarListProdDeTwitter(s);
		System.out.println("cantCollection: "+cantidadColeccion());
		return collection;
    }

   
    public void tearDown() {
        client.close();
        server.shutdown();
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
	
	public long cantidadColeccion(){
		long rowCount= collection.count();
    return rowCount;
}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
