package dao;

import java.lang.reflect.InvocationTargetException;

import mvc_modelo_observable.Modelo;

import org.junit.Test;

import twitter4j.TwitterException;

import com.mongodb.BasicDBObject;

import dao.mongoDB.MongoConcrete;

public class MongoConcreteTest {
	@Test
    public void mongoRealTest() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TwitterException{
		MongoConcrete mongoReal= new MongoConcrete();
		mongoReal.eliminarTodaLaColeccion();
		mongoReal.agregarNuevosDocumentos(mongoReal.getPromos());
		Modelo modelo= new Modelo("a","e","i","0");
		modelo.ConectarMongoDBStub();
		modelo.cargarRecomendacionesGenerales(modelo.getMapRecomendaciones());
		mongoReal.setPromos(modelo.cargarTodasLasPromos());
		mongoReal.leerColeccion();
		mongoReal.cantidadColeccion();
		mongoReal.modificarTodoElContenidoDeUnDocumento("claveNuevo", "valorViejo", "valorNuevo");
		mongoReal.modificarSoloUnValorDeUnDocumento("claveNuevo", "valorViejo", "valorNuevo");
		mongoReal.agregarUnNuevoParClaveValorAunDocumento("claveAbuscar", "valorAbuscar", "claveAagregar", "valorAagregar");
		System.out.println("count coll: "+mongoReal.getPromos().count());
		BasicDBObject doc= new BasicDBObject();
		doc.append("clave", "valor");
		mongoReal.agregarDocumentoEnColeccion(doc);
		BasicDBObject update3= new BasicDBObject();
		mongoReal.borrarUnDocumentoDeUnaColeccion(update3);
		mongoReal.getPromos();
		mongoReal.setPromos(mongoReal.getPromos());
		mongoReal.eliminarTodaLaColeccion();
	}
    

}
