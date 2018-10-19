package dao.mongoDB;

import static org.junit.Assert.assertEquals;

import java.net.InetSocketAddress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;

public class MongoConcreteStub {
	MongoServer server;
	InetSocketAddress serverAddress;
	MongoClient client;
	DBCollection coll;
	
	@SuppressWarnings("deprecation")
	@Before
	public void init(){
		server = new MongoServer(new MemoryBackend());
		// bind on a random local port
		serverAddress = server.bind();

		client = new MongoClient(new ServerAddress(serverAddress));

		coll = client.getDB("testdb").getCollection("testcoll");
		// creates the database and collection in memory and inserts the object
	}
	
	@After
	public void finish(){
		client.close();
		server.shutdownNow();
	}
	
	@Test
	public void insert(){
		coll.insert(new BasicDBObject("key", "value"));

		assertEquals(1, coll.count());
		assertEquals("value", coll.findOne().get("key"));

		
	}
}
