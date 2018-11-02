package mvc_modelo_observable;

import dao.mongoDB.MongoConcrete; 
import dao.mongoDB.MongoConcreteStub;
//import dao.mongoDB.MongoConcreteStub;
import mvc_modelo_observable.Modelo;

public class MVC_sePP2vistas {

    @SuppressWarnings("unused")
	public static void main(String args[]){
    	//MongoConcrete i= new MongoConcrete();
    	MongoConcreteStub i= new MongoConcreteStub();//error en m.getCollection() del modelo proveniente del MongoConcrete()
    	//i.conectarseMongoDB();
    	//i.eliminarTodaLaColeccion();
    	
        //Cargamos modelo
        Modelo m = new Modelo("recomendaciones","usuario", "email");   
        //Cargamos vista
        Vista2 v2 = new Vista2();        
        //Cargamos controlador y le asignamos qué modelo controlar
        Controlador c = new Controlador(m, v2);
    }
}