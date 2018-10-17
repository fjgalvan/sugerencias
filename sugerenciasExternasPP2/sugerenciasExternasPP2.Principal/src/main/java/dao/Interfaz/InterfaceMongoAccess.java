package dao.Interfaz;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public interface InterfaceMongoAccess {

	public DBCollection leerColeccion();
	
	public void agregarDocumentoEnColeccion(BasicDBObject doc1);
	
	public void borrarUnDocumentoDeUnaColeccion(BasicDBObject searchQuery);
}