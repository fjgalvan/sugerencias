package mvc;

import java.lang.reflect.InvocationTargetException;   

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import bo.CustomersBo;
import configurables.MyConstantsDAO;
import dao.mongoDB.MongoConcrete;
import listeners.ActualizacionPromos;
import modelo.Customer;
import modelo.Recomendacion;
//import dao.mongoDB.ActualizacionPromos;
import mvc_modelo_observable.Modelo;
import twitter4j.TwitterException;


public class MVC_sePP2vistas {

	public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TwitterException{
        //Cargamos modelo
        Modelo m = new Modelo("recomendaciones","usuario", "email", "horarioActualizacion");
        m.ConectarMongoDBreal("promosActual");
        
//        //Reviso la base de MongoDB
//        DBCollection collUsuarios=m.getMongo().db.getCollection("UsuarioDB");
//        //m.getMongo().db.getCollection("UsuarioDB").drop();
//        System.out.println("collUsuarios.count().count(): "+collUsuarios.count());
//        
//        DBCollection collRecomendacion=m.getMongo().db.getCollection("RecomendacionesDB");
//        //m.getMongo().db.getCollection("RecomendacionesDB").drop();
//        System.out.println("collRecomendacion.count(): "+collRecomendacion.count());      
//        
//        DBCollection collPromos=m.getMongo().db.getCollection("promosActual");
//        //m.getMongo().db.getCollection("RecomendacionesDB").drop();
//        System.out.println("collPromos.count(): "+collPromos.count()); 
        
        //Cargamos vista
        Vista2 v2 = new Vista2(m);
        m.addObserver(v2);
        
        //Cargamos el temporizador
        ActualizacionPromos actualizacion= new ActualizacionPromos(m);
        actualizacion.actualizarPromos(MyConstantsDAO.tiempoUnMinuto, v2);
        actualizacion.runTimer();
        //actualizacion.stopTimer();
       
    }
}