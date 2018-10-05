package dao;

import java.net.UnknownHostException; 
import com.mongodb.MongoClient;

public class MongoUtils {
	   private static final String HOST = "localhost";
	   private static final int PORT = 27017;
	  
	   private static MongoClient getMongoClient_1() throws UnknownHostException {
	       MongoClient mongoClient = new MongoClient(HOST, PORT);
	       return mongoClient;
	   }
	   public static MongoClient getMongoClient() throws UnknownHostException {       
	       return getMongoClient_1();
	   }

}