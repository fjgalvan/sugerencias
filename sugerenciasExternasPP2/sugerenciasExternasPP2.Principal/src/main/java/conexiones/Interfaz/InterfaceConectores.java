package conexiones.Interfaz;

import twitter4j.TwitterException;

import com.mongodb.DBCollection;

public interface InterfaceConectores {

	public void conectarse();
	
	public DBCollection getPromo(DBCollection promosViejas);
	
}
