package dao;

import static org.junit.Assert.assertEquals;

import java.net.InetSocketAddress;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;

import dao.mongoDB.MyConstants;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;

/*
 * Servidor Java MongoDB
 * Implementación de stub del servidor central MongoDB en Java. 
 * El Protocolo de MongoDB Wire se implementa con Netty . 
 * Diferentes backends son posibles y se pueden extender fácilmente.
 * 
 * El backend en memoria es el predeterminado, de modo que mongo-java-server 
 * se puede usar como código auxiliar en las pruebas unitarias. 
 * Es compatible con las operaciones básicas de CRUD. Sin embargo, 
 * no todas las funciones están implementadas, como la búsqueda 
 * de texto completo o el mapa / reducir.
 */

public class SimpleTest {

    //private MongoCollection<Document> collection;
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
    }

    @After
    public void tearDown() {
        client.close();
        server.shutdown();
    }

    @Test
    public void testSimpleInsertQuery() throws Exception {
        assertEquals(0, collection.count());

        // creates the database and collection in memory and insert the object
        BasicDBObject doc1 = new BasicDBObject(); 
        doc1.append("_id", 1);
        doc1.append("key", "value");
    
        collection.insert(doc1);

        assertEquals(1, collection.count());
        assertEquals(doc1, collection.findOne());
    }

}