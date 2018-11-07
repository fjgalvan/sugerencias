package dao.mongoDB;

import java.net.UnknownHostException; 
import com.mongodb.MongoClient;

public class MongoUtils {
	   private static final String HOST = MyConstants.HOST;
	   private static final int PORT = MyConstants.PORT;
	  
	   private static MongoClient getMongoClient_1() throws UnknownHostException {
	       MongoClient mongoClient = new MongoClient(HOST, PORT);
	       return mongoClient;
	   }
	   public static MongoClient getMongoClient() throws UnknownHostException {       
	       return getMongoClient_1();
	   }

}