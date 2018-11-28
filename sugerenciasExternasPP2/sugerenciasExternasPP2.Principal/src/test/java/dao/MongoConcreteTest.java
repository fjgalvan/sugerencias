package dao;

import org.junit.Test;

import com.mongodb.BasicDBObject;

import dao.mongoDB.MongoConcrete;

public class MongoConcreteTest {
	@Test
    public void mongoRealTest(){
		MongoConcrete mongoReal= new MongoConcrete();
		mongoReal.eliminarTodaLaColeccion();
		mongoReal.agregarNuevosDocumentos(mongoReal.getPromos());
		mongoReal.leerColeccion();
		BasicDBObject doc= new BasicDBObject();
		doc.append("clave", "valor");
		mongoReal.agregarDocumentoEnColeccion(doc);
		BasicDBObject update3= new BasicDBObject();
		mongoReal.borrarUnDocumentoDeUnaColeccion(update3);
		mongoReal.getPromos();
		mongoReal.setPromos(mongoReal.getPromos());
	}
    

}
