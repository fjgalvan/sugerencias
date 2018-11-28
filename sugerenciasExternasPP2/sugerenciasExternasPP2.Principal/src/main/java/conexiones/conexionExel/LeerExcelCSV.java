package conexiones.conexionExel;

import java.io.FileReader;  
import java.io.IOException;
import java.util.Arrays;

import properties.Constants;
import properties.PropertiesPrincipal;
import twitter4j.TwitterException;

import com.mongodb.DBCollection;
import com.opencsv.CSVReader;

import conexiones.Interfaz.InterfaceConectores;
import dao.mongoDB.MyConstants;

@SuppressWarnings("unused")
public class LeerExcelCSV implements InterfaceConectores{
	public static final char SEPARATOR=';';
	public static final char QUOTE='"';
	static String[] nextLine=null;
	
	public LeerExcelCSV(){
		
	}
	
	@Override
	public void conectarse() {
		leerExcelCSV();
	}
	
	public static void leerExcelCSV(){
		PropertiesPrincipal pp= new PropertiesPrincipal(Constants.ROUTE_EXCEL_CSV_PROPERTIES);
		
	   CSVReader reader = null;
	   try {
	         reader = new CSVReader(new FileReader(pp.leerValorDeUnaClave(MyConstants.excelRuta)),SEPARATOR,QUOTE);
	         while ((nextLine = reader.readNext()) != null) {
	        	 Arrays.toString(nextLine);
	         }
	      } catch (Exception e) {System.out.println("no se pudo abrir el excel.csv");
	      } finally {
	         if (null != reader) {
	            try {reader.close();
				} catch (IOException e) {e.printStackTrace();}
	         } 
	     }
	}

	public String[] getNextLine() {
		return nextLine;
	}

	@Override
	public DBCollection getPromo(DBCollection coll) {
		return null;
	}
	
	 
}
