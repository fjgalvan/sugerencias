package dao.mongoDB;

import java.net.InetSocketAddress; 
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import dao.Interfaz.InterfaceMongoAccess;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;

public class MongoConcreteStub implements InterfaceMongoAccess{
	MongoServer server;
	InetSocketAddress serverAddress;
	MongoClient client;
	DBCollection coll;
	
	public MongoConcreteStub(){
		getDB();
	}
	public MongoConcreteStub(String nombreBase){
		server = new MongoServer(new MemoryBackend());
		// bind on a random local port
		serverAddress = server.bind();

		client = new MongoClient(new ServerAddress(serverAddress));

		coll = client.getDB(nombreBase).getCollection("testcoll");
	}
	public MongoClient getDB(){
		if(client == null){
			conectarseMongoDB();
		}
		return client;
	}
	
	public void agregarNuevosDocumentos(DBCollection nuevasPromos){
		DBCursor cursor = nuevasPromos.find();
		try {
			int i = 1;
			while (cursor.hasNext()) {
				System.out.println("Document: " + i);
				//System.out.println(cursor.next());
				BasicDBObject doc= (BasicDBObject) cursor.next();
				coll.insert(doc);
				i++;
			}
		} finally {
			cursor.close();
		}
	}
	@SuppressWarnings("deprecation")
	public void init(){
		server = new MongoServer(new MemoryBackend());
		// bind on a random local port
		serverAddress = server.bind();

		client = new MongoClient(new ServerAddress(serverAddress));

		coll = client.getDB("testdb").getCollection("testcoll");
		// creates the database and collection in memory and inserts the object
	}
	
	
	public void finish(){
		client.close();
		server.shutdownNow();
	}
	
	public void insert(){
		coll.insert(new BasicDBObject("key", "value"));
	}

	@Override
	public DBCollection leerColeccion() {
		
		DBCursor cursor = coll.find();
		try {
			int i = 1;
			while (cursor.hasNext()) {
//				System.out.println("Document: " + i);
//				System.out.println(cursor.next());
				cursor.next();
				i++;
			}
		} finally {
			cursor.close();
		}
		return coll;
	}

	@Override
	public void agregarDocumentoEnColeccion(BasicDBObject doc1) {
		coll.insert(doc1);
	}

	@Override
	public void borrarUnDocumentoDeUnaColeccion(BasicDBObject searchQuery) {
		coll.remove(searchQuery);
	}

	@Override
	public void conectarseMongoDB() {
		init();
		
	}
	public void eliminarTodaLaColeccion(){
		coll.drop();
	}
	public  DBCollection getPromos() {
		return coll;
	}
	public void setColl(DBCollection coll) {
		this.coll = coll;
	}
	
	
}
