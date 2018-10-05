package promosEnJson;

import com.fasterxml.jackson.core.JsonProcessingException;  

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import interfaces.InterfacePromo;

import java.util.HashMap;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;





import conecciones.ExcelParserJSON;
import dao.TaggearComidas;

@SuppressWarnings({ "unused", "deprecation" })
public class PromoExcel implements InterfacePromo{
	DBCollection collection;
	
    public PromoExcel(){
    	
    }
    
	public void leerPromo() {
    	parsearExcelBson();
	}

    @SuppressWarnings("static-access")
	public void parsearExcelBson(){
    	String stringJSON;
    	ExcelParserJSON eJSON= new ExcelParserJSON();
    	stringJSON= eJSON.ExcelJSONformat();
    	parsearBSON(stringJSON);
    }
    
	public DBCollection parsearBSON(String s){
		HashMap<String,Double> l= new HashMap<String,Double>();
		//DBCollection collection = null;
		try {

			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("yourdb100");
			collection = db.getCollection("excelPromoJc100SON");
			
			// convert JSON to DBObject directly
			DBObject dbObject = (DBObject) JSON
					.parse(s);

			collection.insert(dbObject);
			
			//TAGGEO PROMOS DE COMIDAS
			TaggearComidas tc= new TaggearComidas(collection);
			tc.taggearComidas();
			
			//ELIMINO PROMOS DE COMIDAS INCORRECTOS
			tc.eliminarComidasSinTaggear();
			
			//IMPRIMO BSON
			DBCursor cursorDoc3 = tc.getCollection().find();//collection.find();
			while (cursorDoc3.hasNext()) {
				System.out.println(cursorDoc3.next());
			}
		} catch (MongoException e) {
			e.printStackTrace();
		}		
		return collection;
	}
 

    public DBCollection getCollection() {
		return collection;
	}

	@Override
	public DBCollection getPromo() {
		// TODO Auto-generated method stub
		return getCollection();
	}

	
}
