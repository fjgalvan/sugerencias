package dao.mongoDB;


import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import dao.Interfaz.InterfaceMongoAccess;


public class MongoConcrete implements InterfaceMongoAccess {
	
	MongoClient myClient = null;
	private static DB db; 
	private static DBCollection promos;

	//si la coneccion es distininto de null hay que conectarse, esto es un singleton
	public MongoConcrete(){
		getDB();
	}

	public DB getDB(){
		if(db == null){
			conectarseMongoDB();
		}
		return db;
	}
	
	public void agregarNuevosDocumentos(DBCollection nuevasPromos){
		DBCursor cursor = nuevasPromos.find();
		try {
			while (cursor.hasNext()) {BasicDBObject doc= (BasicDBObject) cursor.next();promos.insert(doc);
			}
		} finally {cursor.close();}
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void conectarseMongoDB() {
		try {myClient = MongoUtils.getMongoClient();
		} catch (UnknownHostException e) { e.printStackTrace();}
		db = myClient.getDB(MyConstants.DB_NAME);
		promos= db.getCollection(MyConstants.DB_NAME);
	}
	@SuppressWarnings("unused")
	@Override
	public DBCollection leerColeccion(){
		DBCursor cursor= promos.find();
		return promos;
	}
	@Override
	public void agregarDocumentoEnColeccion(BasicDBObject doc1){
//	       doc1.append("_id", 1);
//	       doc1.append("local", "mcDonal");
	       promos.insert(doc1);
	}
	@Override
	public void borrarUnDocumentoDeUnaColeccion(BasicDBObject searchQuery){
		promos.remove(searchQuery);
	}
	
	public long cantidadColeccion(){
		long rowCount = promos.count();
		Set<String> collections = db.getCollectionNames(); 
	    for(String coll: collections)  {
	    }
	    return rowCount;
	}
	
	public void buscarYmostrarTodosLosDocumentos(){
		DBCursor cursor = promos.find();
		try {
			int i = 1;
			while (cursor.hasNext()) {System.out.println("Document: " + i);System.out.println(cursor.next());i++;
			}
		} finally {cursor.close();}
	}
	
	
	
	public void modificarTodoElContenidoDeUnDocumento(String clave, String valorViejo, String valorNuevo){
		 BasicDBObjectBuilder whereBuilder = BasicDBObjectBuilder.start();
		 whereBuilder.append(clave, valorViejo);
		 DBObject where = whereBuilder.get();
		 DBObject update= new BasicDBObject();
		 update.put(clave, valorNuevo);
		 promos.update(where, update); 
	}
	
	public void modificarSoloUnValorDeUnDocumento(String clave, String valorViejo, String valorNuevo){
		BasicDBObjectBuilder whereBuilder2 = BasicDBObjectBuilder.start();
		whereBuilder2.append(clave, valorViejo);
		DBObject where2 = whereBuilder2.get();
		BasicDBObject update2= new BasicDBObject();
		update2.append("$set", new BasicDBObject().append(clave, valorNuevo));
		promos.update(where2, update2);; 		
	}
	
	public void agregarUnNuevoParClaveValorAunDocumento(String claveAbuscar, String valorAbuscar, String claveAagregar, String valorAagregar){
		BasicDBObjectBuilder whereBuilder3 = BasicDBObjectBuilder.start();
		// Use the append method (similar to the use of add method)
		whereBuilder3.append(claveAbuscar, valorAbuscar);
		DBObject where3 = whereBuilder3.get();
		BasicDBObject update3= new BasicDBObject();
		update3.append("$set", new BasicDBObject().append(claveAagregar, valorAagregar));
		promos.update(where3, update3);		
	}
	
	public void eliminarTodaLaColeccion(){
		promos.drop();
	}
	public static DBCollection getPromos() {
		return promos;
	}

	public static void setPromos(DBCollection promos) {
		MongoConcrete.promos = promos;
	}
	
}
