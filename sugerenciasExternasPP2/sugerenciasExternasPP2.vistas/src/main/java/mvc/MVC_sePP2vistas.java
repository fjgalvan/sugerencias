package mvc;

import java.lang.reflect.InvocationTargetException;  

import configurables.MyConstantsDAO;
import dao.mongoDB.ActualizacionPromos;
import mvc_modelo_observable.Modelo;
import twitter4j.TwitterException;


public class MVC_sePP2vistas {

	public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TwitterException{
        //Cargamos modelo
        Modelo m = new Modelo("recomendaciones","usuario", "email", "horarioActualizacion");
        
        //Cargamos vista
        Vista2 v2 = new Vista2(m);
        m.addObserver(v2);
        
        //Cargamos el temporizador
        ActualizacionPromos actualizacion= new ActualizacionPromos(m);
        actualizacion.actualizarPromos(MyConstantsDAO.tiempoDosMinuto, v2);
        actualizacion.runTimer();
        //actualizacion.stopTimer();
       
    }
}