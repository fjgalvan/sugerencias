package mvc_modelo_observable;

import dao.mongoDB.MongoConcrete;
import mvc_modelo_observable.Modelo;
/**
 * Ejemplo patrones de diseño MVC y Observer
 * Realizado por Alfonso Soria Muñoz
 * Para el canal de experimentando y aprendiendo.
 */
/**
 * Clase Main, desde aqui cargaremos el modelo, la vista y el controlador, y añadiremos observadores sobre el modelo.
 * @author fon
 */
public class MVC_sePP2vistas {

    @SuppressWarnings("unused")
	public static void main(String args[]){
    	MongoConcrete i= new MongoConcrete();
    	i.conectarseMongoDB();
    	i.eliminarTodaLaColeccion();
    	
        //Cargamos modelo
        Modelo m = new Modelo("recomendaciones","usuario", "email");   
        //Cargamos vista
        Vista2 v2 = new Vista2();        
        //Cargamos controlador y le asignamos qué modelo controlar
        Controlador c = new Controlador(m, v2);
    }
}