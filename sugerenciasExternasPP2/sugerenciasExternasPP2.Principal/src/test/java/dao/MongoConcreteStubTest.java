package dao;

import java.net.UnknownHostException;

import org.junit.Test;

import com.mongodb.BasicDBObject;

import dao.mongoDB.MongoConcreteStub;
import dao.mongoDB.MongoUtils;
import dao.mongoDB.MyConstants;

public class MongoConcreteStubTest {
	@Test
    public void mongoStubTest() throws UnknownHostException{
		MongoConcreteStub mongoStub= new MongoConcreteStub();
		mongoStub.agregarNuevosDocumentos(mongoStub.getPromos());
		BasicDBObject doc= new BasicDBObject();
		doc.append("clave", "valor");
		mongoStub.agregarDocumentoEnColeccion(doc);
		mongoStub.borrarUnDocumentoDeUnaColeccion(doc);
		mongoStub.setColl(mongoStub.getPromos());
		mongoStub.eliminarTodaLaColeccion();
		mongoStub.finish();
		MongoConcreteStub mongoStubParametro= new MongoConcreteStub("nuevaBase");
		mongoStubParametro.finish();
		MongoUtils.getMongoClient();
		String a=MyConstants.DB_NAME;
		String b=MyConstants.DB_PROMO_COMIDA;
		String c=MyConstants.DB_FOOD;
		String d=MyConstants.DB_FOOD2;
		String e=MyConstants.input_excel;
		String f=MyConstants.input_excel_invalido;
		String g=MyConstants.ConsumerKey;
		String h=MyConstants.ConsumerSecret;
		String i=MyConstants.AccessToken;
		String j=MyConstants.AccessTokenSecret;
		String k=MyConstants.excelRuta;
		String l=MyConstants.HOST;
		int m=MyConstants.PORT;
	}

}
